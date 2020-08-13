package concurrent.juc_test.lock;

import util.log.Logger;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


/**
 * LockSupport 的 park() 和 unpark(Thread) 的使用
 * 执行LockSupport.park()会使线程进入wait状态，
 * 执行LockSupport.unpark()会使线程从wait状态移除
 *
 * 相当于wait() 和 notify();
 *
 * 区别：
 *     wait(); LockSupport.park() 会使线程进入wait状态， 都可以相应中断
 *     但是wait() 需要获取对象锁, 执行wait()会释放对象锁; 而 LockSupport.park()是使用unsafe中的native方法
 *
 *     notify()和unpark(Thread) 都会是线程进入runnable状态 notify 不能指定哪个线程醒来，而unpark(Thread)可以执行
 *     但是LockSupport不支持全部通知notifyAll()
 *
 *
 */
public class LockSupportDemo {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {

            if (true) {
                int i = 0;
                for (;;) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i ++;
                    Logger.info("执行任务");

                    if (i > 5) {
                        Logger.info("自我中断");
                        LockSupport.park();

                        Logger.info("中断结束");

                        if (Thread.currentThread().isInterrupted()) {
                            Logger.info("被中断");
                            break;
                        }
                    }
                }
            }

            Logger.info("执行完毕");
        }
    }

    public static void main(String[] args) throws InterruptedException {


        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));

        test();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("开始测试？");
//        scanner.nextLine();
//        t1.start();
//        t2.start();
//
//        scanner.nextLine();
//
//        System.out.println("准备打断t1");
//        t1.interrupt();
//
//        scanner.nextLine();
//        System.out.println("对t2放行");
//        LockSupport.unpark(t2);
//
//        Thread.sleep(Integer.MAX_VALUE);
    }



    static void test(){

        try {
            System.out.println("准备park");
            LockSupport.park();
        } finally {
            System.out.println("finaly");
        }

    }
}