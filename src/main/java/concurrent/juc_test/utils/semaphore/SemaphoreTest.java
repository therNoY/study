package concurrent.juc_test.utils.semaphore;

import util.log.Logger;

import java.util.concurrent.Semaphore;


/**
 * 信号量控制 可以控制限制访问有点类似 TwinsLock一个简单的共享锁定的实现
 */
public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {


        for (int i = 0; i < 30; i++) {
            new Thread(()->{

                try {
                    semaphore.acquire();
                    Logger.info("do work....");
                    Thread.sleep(1000);

                    semaphore.release();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }).start();
        }

    }
}
