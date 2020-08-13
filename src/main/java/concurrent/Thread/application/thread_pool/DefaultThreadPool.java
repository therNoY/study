package concurrent.Thread.application.thread_pool;

import util.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final Integer maxThreadSize = 10;
    private static final Integer defaultThreadSize = 5;
    private static final Integer minThreadSize = 1;

    private BlockingQueue<Runnable> jobQueue = new LinkedBlockingDeque<>();

    private Map<Worker, Thread> workerMap = new HashMap<>();

    private int workerNum;

    private AtomicInteger atomicInteger = new AtomicInteger(0);


    public DefaultThreadPool() {
        init(defaultThreadSize);
    }

    public DefaultThreadPool(int threadSize) {
        if (threadSize >= minThreadSize && threadSize <= maxThreadSize) {
            init(threadSize);
        } else {
            throw new RuntimeException("线程池大小设置错误");
        }

    }

    void init(int num) {
        this.workerNum = num;
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            Thread thread = new Thread(worker, this.getClass().getSimpleName() + "-" + atomicInteger.incrementAndGet());
            workerMap.put(worker, thread);
            thread.start();
        }

    }

    @Override
    public void execute(Runnable job) {
        synchronized (jobQueue) {
            jobQueue.add(job);
            jobQueue.notify();
        }

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void addWorkers(int num) throws RuntimeException{
        if (workerNum + num < maxThreadSize) {
            for (int i = 0; i < num; i++) {
                Worker worker = new Worker();
                Thread thread = new Thread(worker, this.getClass().getSimpleName() + "-" + atomicInteger.incrementAndGet());
                workerMap.put(worker, thread);
                thread.start();
                workerNum ++;
            }
        }else {
            throw new RuntimeException("too much worker");
        }
    }

    @Override
    public void removeWorker (int num) throws RuntimeException {
        if (num > 0 && num < workerNum) {

            int stopNum = 0;
            List<Worker> workerList = new ArrayList<>();
            for (Map.Entry<Worker, Thread> entry : workerMap.entrySet()) {
                if (stopNum < num) {
                    entry.getValue().interrupt();
                    entry.getKey().shutdown();
                    workerList.add(entry.getKey());
                    stopNum ++;
                }
            }


            if (workerList.size() > 0){
                workerList.stream().forEach(worker -> workerMap.remove(worker));
            }

        }else {
            throw new RuntimeException("error num");
        }
    }

    @Override
    public int getJobSize() {
        return workerMap.size();
    }

    class Worker implements Runnable {

        private volatile boolean isRunning = true;

        @Override
        public void run() {

            while (isRunning) {
                synchronized (jobQueue) {
                    while (jobQueue.size() <= 0) {
                        try {
                            jobQueue.wait();
                        } catch (InterruptedException e) {
                            Logger.info("被打断了");
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
                if (jobQueue.size() > 0) {
                    Runnable runnable = jobQueue.poll();
                    if (runnable != null) {
                        try {
                            runnable.run();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            Logger.info("失去价值 准备结束生命");
        }

        public void shutdown(){
            this.isRunning = false;
        }
    }
}
