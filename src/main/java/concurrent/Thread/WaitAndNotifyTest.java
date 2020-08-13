package concurrent.Thread;

import util.log.Logger;

public class WaitAndNotifyTest {

    public static final Object threadObj = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            int i =0;
            while (true) {
                synchronized (threadObj) {
                    if (i ++ == 10){
                        try {
                            Logger.info("我准备等待了");
                            threadObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Logger.info("我在干活");

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }, "员工1").start();


        new Thread(()->{
            int i =0;
            while (true) {
                synchronized (threadObj) {
                    if (i ++ == 10){
                        try {
                            Logger.info("我准备等待了");
                            threadObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Logger.info("我在干活");

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }, "员工2").start();


        new Thread(()->{
            int i =0;
            while (true) {
                synchronized (threadObj) {
                    if (i ++ == 10){
                        try {
                            Logger.info("我准备等待了");
                            threadObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Logger.info("我在干活");

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }, "员工3").start();


        new Thread(()->{
            int i =0;
            while (true) {
                synchronized (threadObj) {
                    if (i ++ == 12){
                        Logger.info("叫醒其他人干活");
                        threadObj.notifyAll();
                    }

                    Logger.info(" 我在干活");

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }, "经理").start();


        Thread.sleep(Integer.MAX_VALUE);

    }
}
