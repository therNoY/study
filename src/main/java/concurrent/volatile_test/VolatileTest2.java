package concurrent.volatile_test;

import java.util.concurrent.atomic.AtomicBoolean;

public class VolatileTest2 {
    public static AtomicBoolean doWork = new AtomicBoolean(true);
    public static void shutdown () {
        System.out.println("停止");
        doWork.set(false);
        System.out.println("停止2");
    }
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (doWork.get()) {
                    System.out.println(Thread.currentThread().getName()+" do work");
                }
            });
            threads[i].setName("thread"+i);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        shutdown();


    }
}
