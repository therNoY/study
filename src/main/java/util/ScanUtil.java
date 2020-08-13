package util;

import util.log.Logger;

import java.util.Scanner;

public class ScanUtil {

    public static void main(String[] args) {
        ScanUtil.runInThread(s -> {
            if (s.startsWith("w")) {
                synchronized (ScanUtil.class) {
                    Logger.info("wait");
                    try {
                        ScanUtil.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                synchronized (ScanUtil.class) {
                    ScanUtil.class.notifyAll();
                }
            }
            Logger.info("done");
        });
    }


    public static void run(MyRun runnable) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数据....");

        String s = scanner.nextLine();
        while (!s.equals("-1")) {
            runnable.run(s);
            System.out.println("请输入数据....");
            s = scanner.nextLine();
        }
    }

    public static void runInThread(MyRun runnable) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数据....");

        String s = scanner.nextLine();
        while (!s.equals("-1")) {
            if (s.length() > 0) {
                String finalS = s;
                new Thread(() -> runnable.run(finalS), "Thread_" + finalS).start();
                System.out.println("请输入数据....");
            }
            s = scanner.nextLine();
        }
    }

    public interface MyRun {
        void run(String s);
    }

}




