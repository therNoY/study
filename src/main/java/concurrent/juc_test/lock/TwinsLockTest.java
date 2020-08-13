package concurrent.juc_test.lock;

import util.log.Logger;

import java.util.concurrent.locks.Lock;

public class TwinsLockTest {

    static Lock lock = new TwinsLock();

    public static void main(String[] args) {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(() -> {
                    try {
                        lock.lock();
                        Thread.sleep(10);
                        Logger.info("处理事情....");
                        Thread.sleep(20);
                        Logger.info("处理事情END....");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                });

            threads[i] = thread;
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }


    }


}
