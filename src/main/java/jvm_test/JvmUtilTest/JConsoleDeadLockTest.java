package jvm_test.JvmUtilTest;

import util.log.Logger;

import java.util.Scanner;

public class JConsoleDeadLockTest {


    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("ready ?");
        scanner.nextLine();

        for (int i = 0; i < 100; i++) {
            new Thread(new DeadLock(1, 2)).start();
            new Thread(new DeadLock(2, 1)).start();
        }


        Thread.sleep(Integer.MAX_VALUE);
    }


    private static class DeadLock implements Runnable {

        int a;
        int b;

        public DeadLock(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    Logger.info(a + b);
                }
            }
        }
    }
}
