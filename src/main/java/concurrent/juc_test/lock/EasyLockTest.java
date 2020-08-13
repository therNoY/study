package concurrent.juc_test.lock;

import util.log.Logger;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class EasyLockTest {

    public static void main(String[] args) {

        easyTest();
    }

    private static void easyTest() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println("开始" + LocalDateTime.now());

//        Lock easyLock = new ReentrantLock();
        Lock easyLock = new EasyLock();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
//                easyLock.lock();
                synchronized (easyLock) {
                    Logger.info("获取锁");
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Logger.info("释放锁:" + LocalDateTime.now());
                }

//                easyLock.unlock();

            }).start();
        }
    }

}
