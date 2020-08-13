package concurrent.Thread.thread_method;

import util.log.Logger;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitAndNotify {

    LinkedBlockingQueue<String> list = new LinkedBlockingQueue<>();

    AtomicInteger workerNum = new AtomicInteger();

    AtomicInteger waitNum = new AtomicInteger();

    Runnable worker = ()->{

        workerNum.getAndIncrement();

        while (true){
            if (list.size() > 0) {
                String s = list.poll();
                Logger.info("消费" + s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                synchronized (list){
                    try {
                        waitNum.getAndIncrement();
                        Logger.info("准备休眠");
                        list.wait();
                        waitNum.getAndDecrement();
                        Logger.info("休眠结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    };


    public static void main(String[] args) {
        WaitAndNotify waitAndNotify = new WaitAndNotify();
        waitAndNotify.list.add("A");
        waitAndNotify.list.add("B");
        waitAndNotify.list.add("c");
        waitAndNotify.list.add("d");
        waitAndNotify.list.add("r");


        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(waitAndNotify.worker);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入");
            String s = scanner.nextLine();

            String[] ss = s.split("");

            for (int i = 0; i < ss.length; i++) {
                waitAndNotify.list.offer(ss[i]);
            }

            Logger.info("判断waitNum" + waitAndNotify.waitNum.get() + "总数量" + waitAndNotify.workerNum.get());
            if (waitAndNotify.waitNum.get() > 0) {
                synchronized (waitAndNotify.list) {
                    waitAndNotify.list.notifyAll();
                }
            }
        }


    }

}
