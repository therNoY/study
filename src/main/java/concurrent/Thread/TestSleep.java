package concurrent.Thread;

import util.log.Log;

import java.util.Scanner;

public class TestSleep {

    static Log log = Log.threadLog();

    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepThread());
        sleepThread.start();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            log.info("准备打断");
            System.out.println(sleepThread.isInterrupted());
            sleepThread.interrupt();
            System.out.println(sleepThread.isInterrupted());
        }
    }
}


class SleepThread implements Runnable {

    Log log = Log.threadLog();

    @Override
    public void run() {

        while (true) {
            log.info("准备开始睡眠");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.info("InterruptedException");
                e.printStackTrace();
            }
        }


    }
}