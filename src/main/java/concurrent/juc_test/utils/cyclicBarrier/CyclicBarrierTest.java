package concurrent.juc_test.utils.cyclicBarrier;


import util.log.Logger;

import java.util.concurrent.CyclicBarrier;


/**
 * CountDownLatch 是一次性的，CyclicBarrier 是可循环利用的
 * CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。CyclicBarrier 参与的线程职责是一样的。
 *
 * 实现原理 每次await都会使用一把锁 判断index 是否为0 不是就直接await 是就重新设置index 并唤醒所有的await上的线程
 *
 *
 */
public class CyclicBarrierTest {

    static class TaskThread extends Thread {

        MyCyclicBarrier2 barrier;
//        CyclicBarrier barrier;

        public TaskThread(MyCyclicBarrier2 barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                Logger.info(" 到达栅栏 A");
                barrier.await();
                Logger.info(" 冲破栅栏 A");

                Thread.sleep(2000);
                Logger.info(" 到达栅栏B");
                barrier.await();
                Logger.info(" 冲破栅栏B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        MyCyclicBarrier2 barrier = new MyCyclicBarrier2(threadNum, () -> Logger.info(" 完成最后任务"));
        for(int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }

}