package concurrent.juc_test.lock;

import util.log.Logger;

import java.util.concurrent.locks.Lock;

public class ReadWriteLockTest {

    static MyReentrantReadWriteLock rwl = new MyReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    public static void main(String[] args) throws InterruptedException {

        Thread[] readT = new Thread[10];

        Thread[] writeT = new Thread[4];

        for (int i = 0; i < readT.length; i++) {
            readT[i] = new Thread(() -> {
                r.lock();
                Logger.info("准备读数据");
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logger.info("读数据结束");
                r.unlock();
            });
        }


        for (int i = 0; i < writeT.length; i++) {
            writeT[i] = new Thread(() -> {

                w.lock();
                Logger.info("准备写数据");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logger.info("写数据结束");
                w.unlock();

            });
        }


        for (int i = 0; i < readT.length; i++) {
            readT[i].setName("READ_" + i);
            readT[i].start();
        }

        for (int i = 0; i < writeT.length; i++) {
            writeT[i].setName("WRITE_" + i);
            writeT[i].start();
        }


        Thread.sleep(Integer.MAX_VALUE);

    }
}
