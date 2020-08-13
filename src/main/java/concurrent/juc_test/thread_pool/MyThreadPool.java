package concurrent.juc_test.thread_pool;

import util.log.Log;
import util.log.Logger;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * 自己模拟的线程池的工作流程
 * 和标准线程池比起来 标准线程池时一个一个创建工作线程或者扩展线程的 这个是一次全创建的
 * 没有将死去的线程移除
 * 活着的线程都是最开始的那一批，不太好
 */
public class MyThreadPool {

    //  工作线程数组
    final Worker[] workers;
    final int corePoolSize;
    final int maximumPoolSize;
    long keepAliveTime;
    TimeUnit unit;
    final BlockingQueue<Runnable> workQueue;
    ThreadFactory threadFactory;
    RejectedExecutionHandler handler;

    public MyThreadPool(int corePoolSize,
                        int maximumPoolSize,
                        long keepAliveTime,
                        TimeUnit unit,
                        BlockingQueue<Runnable> workQueue,
                        ThreadFactory threadFactory,
                        RejectedExecutionHandler handler) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
        this.threadFactory = threadFactory;
        this.handler = handler;
        this.workers = new Worker[maximumPoolSize];
    }



    public void execute(Runnable runnable) {
        // 判断是否需要初始化线程池
        if (workers[0] == null)
            initWorkPool();

        // 先从核心线程中找
        for (int i = 0; i < corePoolSize; i++) {
            if (workers[i].isFree) {
                workers[i].addJob(runnable);
                return;
            }
        }

        // 找不到添加阻塞队列
        boolean addOk = workQueue.offer(runnable);
        if (!addOk) {
            // 说明现在队列满了 需要新建线程
            if (workers[corePoolSize] == null || !workers[corePoolSize].isAlive)
                initExtentWorkPool();
            for (int j = corePoolSize; j < maximumPoolSize; j++) {

                if (!workers[j].isAlive) {
                    workers[j] = new Worker(threadFactory, workQueue, keepAliveTime, unit, true);
                }

                if (workers[j].isFree) {
                    workers[j].addJob(runnable);
                    return;
                }
            }
            handler.rejectedExecution(runnable, null);
        }

    }

    private void initExtentWorkPool() {
        for (int i = corePoolSize; i < maximumPoolSize; i++) {
            if (workers[i] == null || !workers[i].isAlive) {
                workers[i] = new Worker(threadFactory, workQueue, keepAliveTime, unit, true);
            }
        }
    }

    /**
     * 这里直接创建一个线程组， 而实际是慢慢创建的
     */
    private void initWorkPool() {
        for (int i = 0; i < corePoolSize; i++) {
            workers[i] = new Worker(threadFactory, workQueue, keepAliveTime, unit);
        }
    }

    public Integer getActiveCount() {
        int count = 0;
        for (int i = corePoolSize; i < maximumPoolSize; i++) {
            if (workers[i] != null && !workers[i].isFree) {
                count ++;
            }
        }
        return count;
    }

    public BlockingQueue getQueue() {
        return workQueue;
    }

    static class Worker implements Runnable{
        ThreadFactory threadFactory;
        BlockingQueue<Runnable> workQueue;
        Runnable task;
        // 任务是否完成
        volatile boolean taskIsOk = false;
        final boolean isExtend;
        Thread thread = null;
        // 该线程是否空闲
        volatile boolean isFree = true;
        long keepAliveTime;
        TimeUnit unit;
        volatile boolean isAlive;


        public Worker(ThreadFactory threadFactory, BlockingQueue<Runnable> workQueue, long keepAliveTime, TimeUnit unit) {
            this(threadFactory, workQueue, keepAliveTime, unit, false);
        }

        public Worker(ThreadFactory threadFactory, BlockingQueue<Runnable> workQueue, long keepAliveTime, TimeUnit unit, boolean isExtend) {
            this.threadFactory = threadFactory;
            this.workQueue = workQueue;
            this.isExtend = isExtend;
            this.keepAliveTime = keepAliveTime;
            this.unit = unit;
            this.isAlive = true;
        }

        void addJob(Runnable runnable){
            this.task = runnable;
            this.taskIsOk = false;
            if (thread == null) {
                thread = this.threadFactory.newThread(this);
                thread.start();
            }else {
                if (isFree) {
                    LockSupport.unpark(thread);
                }else {
                    throw new RuntimeException("ERROR FREE STATE");
                }
            }

        }

        @Override
        public void run() {
            for (;;) {
                isFree = false;
                if (!taskIsOk) {
                    // 线程任务没有完成 完成线程任务
                    task.run();
                    taskIsOk = true;
                }else {
                    // 线程任务完成 就完成队列任务
                    while ((task = workQueue.poll()) != null) {
                        task.run();
                    }
                    isFree = true;
                    // 都完成的话 准备休息
                    if (isExtend) {
                        long nanosTimeout = unit.toNanos(keepAliveTime);
                        LockSupport.parkNanos(this, nanosTimeout);
                        if (taskIsOk) {
                            // 线程已经完成任务 准备死地
                            Logger.info("准备死了");
                            this.isAlive = false;
                            break;
                        }
                    }
                    LockSupport.park();
                }
            }
        }
    }
}
