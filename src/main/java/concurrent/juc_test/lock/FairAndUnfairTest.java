package concurrent.juc_test.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁 与 非公平 重入锁实例 属于独占锁
 *
 * 公平锁与非公平锁的区别 在于tryAcquire 非公平锁 在释放锁后可以直接通过tryAcquire 通过自旋再次获取锁
 * 且概率较大 此时如果有等待队列就会是等待队列的其他线程等待，
 * 而公平锁在cas之前会首先判断当前是否有等待队列 有的话直接进入等待对垒
 *
 */
public class FairAndUnfairTest {


    public static void main(String[] args) {
        ReentrantLock2 lock = new ReentrantLock2(true);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {

                for (int j = 0; j < 3; j++) {

                    try {
                        lock.lock();
                        Thread.sleep(10);

                        System.out.print("当前: " + Thread.currentThread().getName() + "; 队列： ");

                        lock.getQueuedThreads().forEach(thread1 -> {
                            System.out.print(thread1.getName() + " ");
                        });

                        System.out.println();
                        Thread.sleep(20);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            });

            threads[i] = thread;
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {


        }
    }


    private static class ReentrantLock2 extends ReentrantLock {

        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
