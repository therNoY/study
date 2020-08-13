package concurrent.SynchronizedTest;

import util.log.Logger;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BaseUsedTest {

    static int go = 0;

    public static void main(String[] args) {


        for (int i = 0; i < 10; i++) {
            new Thread(new MyRunnable()).start();
        }


        Scanner scanner = new Scanner(System.in);

        while (go < 10) {
            System.out.println("释放一个?");


            scanner.next();
            synchronized (BaseUsedTest.class){
                BaseUsedTest.class.notify();

                if (go > 5) {
                    System.out.println("全部释放?");
                    scanner.next();
                    BaseUsedTest.class.notifyAll();
                }
            }
        }



    }


    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            synchronized (BaseUsedTest.class) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Logger.info("释放Class 对象锁");

                try {
                    BaseUsedTest.class.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Logger.info("再次执行");
                go ++;
            }
        }
    }
}
