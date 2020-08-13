package concurrent.juc_test.thread_pool.fork_join;

import util.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 基础 RecursiveAction
 */
public class ForkJoinPoolTest {

    public static void main(String[] args) throws Exception {
//        testPrintTask();
        Integer maxNum = 1000;
        List<Integer> integerList = new ArrayList<>(maxNum);
        for (int i = 0; i < maxNum; i++) {
            integerList.add(i);
        }

        ForkJoinPool executorService = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ForkJoinPool executorService2 = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2 + 1);


        long start = System.currentTimeMillis();

        long res = executorService.invoke(new PrintTask(0, maxNum, integerList));

        Logger.info(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();

        long res2 = executorService2.invoke(new PrintTask(0, maxNum, integerList));

        Logger.info(System.currentTimeMillis() - start);

        long sum = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < maxNum; i++) {
            sum = sum + i;
            Thread.sleep(8);
        }
        Logger.info(System.currentTimeMillis() - start);

        System.out.println(res == sum);

    }

    private static void testPrintTask() {
        PrintAction task = new PrintAction(0, 300);
        //创建实例，并执行分割任务
        ForkJoinPool executorService = new ForkJoinPool(3);

        for (int i = 0; i < 100; i++) {
            executorService.submit(task);
        }
    }

    /**
     * 这个是没有 返回值的
     */
    static class PrintAction extends RecursiveAction {

        private static final int THRESHOLD = 50; //最多只能打印50个数
        private int start;
        private int end;

        public PrintAction(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }


        @Override
        protected void compute() {

            if (end - start < THRESHOLD) {
                for (int i = start; i < end; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Logger.info("打印的" + i);
                }
            } else {
                int middle = (start + end) / 2;
                PrintAction left = new PrintAction(start, middle);
                PrintAction right = new PrintAction(middle, end);
                //并行执行两个“小任务”
                left.fork();
                right.fork();
            }

        }

    }

    static class PrintTask extends RecursiveTask<Long> {

        private static final int THRESHOLD = 10; //最多只能打印50个数
        private int start;
        private int end;
        private List<Integer> integerList;

        public PrintTask(int start, int end, List<Integer> integerList) {
            super();
            this.start = start;
            this.end = end;
            this.integerList = integerList;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    sum += i;
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                int middle = (start + end) / 2;
                PrintTask left = new PrintTask(start, middle, integerList);
                PrintTask right = new PrintTask(middle, end, integerList);
                //切分大任务
                left.fork();
                right.fork();
                //合并小任务结果
                sum += left.join();
                sum += right.join();
            }
            return sum;
        }

    }

    /**
     * 这个是又返回的
     */

    static class MyRunnable implements Runnable {

        int i;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 执行第" + i + "个任务开始");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行第" + i + "个任务结束");
        }
    }
}



