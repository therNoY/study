package concurrent.Thread.application.thread_pool;

import com.sun.deploy.ui.ImageLoader;
import util.log.Logger;

import java.util.Random;
import java.util.Scanner;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new DefaultThreadPool();

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 添加 2 减少 3 执行 4查看 0 退出");
        String s = null;
        while ((s = scanner.nextLine()) != "0") {
            String oper = null;
            String num = null;
            try {
                String[] opes = s.split(" ");
                oper = opes[0];
                num = null;
                if (!oper.equals("4")) {
                    num = opes[1];
                }
            } catch (Exception e) {
                System.out.println("错误输入");
                continue;
            }
            switch (oper) {
                case "1":
                    threadPool.addWorkers(Integer.valueOf(num));
                    break;
                case "2":
                    threadPool.removeWorker(Integer.valueOf(num));
                    break;
                case "3":
                    execJob(threadPool, Integer.valueOf(num));
                    break;
                case "4":
                    System.out.println("当前线程池大小: " + threadPool.getJobSize());
                    break;
                default:
                    System.out.println("错误输入。。。");
            }

            System.out.println("提示：1 添加 2 减少 3 执行 4查看 0 退出");
        }
    }

    static void execJob(ThreadPool threadPool, int num) {
        for (int i = 0; i < num; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                long time = 0;
                try {
                    time = Math.round(Math.random() * 5000) + 1000;
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logger.info("执行任务" + finalI + "结束 耗时：" + time);
            });
        }

    }
}
