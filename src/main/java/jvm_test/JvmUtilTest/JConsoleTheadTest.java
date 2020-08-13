package jvm_test.JvmUtilTest;

import java.util.Scanner;

public class JConsoleTheadTest {


    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("create work ?");
        scanner.nextLine();

        createWorkThread();
        System.out.println("create Wait ?");
        String a = scanner.nextLine();

        createWaitThread(a);

        System.out.println("let go ?");
        scanner.nextLine();
        synchronized (a) {
            a.notify();
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    static void createWorkThread(){
        new Thread(()->{
            while (true)
                ;
        }, "work").start();
    }

    static void createWaitThread(final Object o){
        new Thread(()->{
            synchronized (o) {
                try {
                    o.wait();
                    while (true)
                        ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("执行结束");
            }

        }, "Wait").start();
    }
}
