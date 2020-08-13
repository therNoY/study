package concurrent.juc_test.lock;


import concurrent.juc_test.lock.aqs.MyAbstractQueuedSynchronizer;
import util.log.Logger;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Condition 中实现的await 执行了这个就会直接进入同步队列 并且pack 自己 当有condition执行sign的时候会将
 * 同步队列的线程的第一个线程 unpack 重新进入等待队列
 * 而await(time, TimeUtil)是调用底层的LockSupport.parkNanos()实现的
 */
public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {

        conditionTest();
    }


    public static void conditionTest() {
        System.out.println("输入任意字符开始。。。");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println("开始" + LocalDateTime.now());

        MyReentrantLock lock = new MyReentrantLock();
        MyAbstractQueuedSynchronizer.ConditionObject condition = (MyAbstractQueuedSynchronizer.ConditionObject) lock.newCondition();
        MyAbstractQueuedSynchronizer.ConditionObject condition2 = (MyAbstractQueuedSynchronizer.ConditionObject) lock.newCondition();

        new Thread(() -> {
            lock.lock();
            Logger.info("获取锁");
            Logger.info("准备await");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Logger.info("释放锁:" + LocalDateTime.now());

            lock.unlock();

        }, "condition1-1").start();


        new Thread(() -> {
            lock.lock();
            Logger.info("获取锁");

            Logger.info("准备await");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Logger.info("释放锁:" + LocalDateTime.now());

            lock.unlock();

        }, "condition1-2").start();

        new Thread(() -> {
            lock.lock();
            Logger.info("获取锁");

            Logger.info("准备await");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Logger.info("释放锁:" + LocalDateTime.now());

            lock.unlock();

        }, "condition1-3").start();

        new Thread(() -> {
            lock.lock();
            Logger.info("获取锁");

            Logger.info("准备await");
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Logger.info("释放锁:" + LocalDateTime.now());

            lock.unlock();

        }, "condition2-1").start();

        new Thread(() -> {
            lock.lock();
            Logger.info("获取锁");
            Logger.info("准备await");
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Logger.info("释放锁:" + LocalDateTime.now());

            lock.unlock();

        }, "condition2-2").start();

        new Thread(() -> {
            lock.lock();
            Logger.info("获取锁");

            Logger.info("准备await");
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Logger.info("释放锁:" + LocalDateTime.now());

            lock.unlock();

        }, "condition2-3").start();

        new Thread(() -> {
            lock.lock();
            Logger.info("获取锁");

            Logger.info("准备await");
            try {
                condition2.await(100, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Logger.info("释放锁:" + LocalDateTime.now());

            lock.unlock();

        }, "condition2-4").start();

        String s = scanner.nextLine();
        while (!s.equals("0")) {
            lock.lock();
            if (s.equals("1")) {
                condition.signal();
                Logger.info("准备释放一个condition");
            }else {
                condition2.signalAll();
                Logger.info("准备释放全部的4condition2");
            }
            lock.unlock();
            s = scanner.nextLine();
        }

    }
}