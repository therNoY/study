package concurrent.juc_test.lock;

import util.log.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 比如调用 demo 方法获得了当前的对象锁，然后在这个方法中再去调用
 * demo2，demo2 中的存在同一个实例锁，这个时候当前线程会因为无法获得
 * demo2 的对象锁而阻塞，就会产生死锁。重入锁的设计目的是避免线程的死锁。
 *
 * ReentrantLock 是实现lock 的形式 实现是可重入锁
 *
 * ReentrantLock 的独占锁实现是使用 是控制状态status 是 0或者1 来表示的
 * 如果是0 表示没有占有 如果是 1表示已经占用
 */
public class ReentrantLockTest {

    static ReentrantLock lock = new ReentrantLock(true);
    static MyReentrantLock myReentrantLock = new MyReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        myReentrantLock.lock();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                Logger.info("准备获取锁");
                myReentrantLock.lock();
                myReentrantLock.unlock();
            }, "thread-" + i).start();
        }



//        test1();
    }

    private static void test1() throws InterruptedException {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                Logger.info("尝试获取锁");
                lock.lock();
                Logger.info("获取锁成功");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logger.info("释放锁");
                lock.unlock();
            });
            threads[i] = thread;
        }


        for (Thread thread : threads) {
            thread.start();
        }

        Thread.sleep(Integer.MAX_VALUE);
    }
}

