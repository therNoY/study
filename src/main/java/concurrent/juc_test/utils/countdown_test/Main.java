package concurrent.juc_test.utils.countdown_test;

import util.log.Logger;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * 维护一个同步阻塞队列 创建的时候设置一个参数 相当于设置了一个获取了共享锁获取了n次的锁，
 * 当执行countDown的时 释放一个共享锁
 * await 会尝试获取共享锁 但是只有当前状态是1 的时候才会获得共享锁 获取不到会加入等待队列
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
//        test1();


        CountDownLatch countDownLatch = new CountDownLatch(3);

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        while (!s.equals("-1")) {

            if (s.startsWith("c")) {
                new Thread(()-> countDownLatch.countDown(), s).start();
            }else if (s.startsWith("a")){
                new Thread(()-> {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, s).start();
            }
            s = scanner.nextLine();
        }

    }

    private static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);


        for (int i = 0; i < 3; i++) {

            final int fi = i;
            new Thread(()->{

                try {
                    Thread.sleep(1000 * fi);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logger.info("线程结束");

                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();

        Logger.info("over");
    }
}
