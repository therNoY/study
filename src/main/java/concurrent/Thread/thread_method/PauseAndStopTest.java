package concurrent.Thread.thread_method;

import java.util.Scanner;

/**
 * 这些方法可以控制一个线程暂停终止和恢复，但是现在已经被弃用了，因为执行暂停或者终止时
 * 会使对象放弃执行，但是没有释放资源，容易造成死锁。
 */
public class PauseAndStopTest {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
            }
        }) ;


        thread.start();


        Scanner scanner = new Scanner(System.in);
        System.out.println("suspend ？");
        scanner.next();
        thread.suspend();

        System.out.println("resume ？");
        scanner.next();
        thread.resume();


        System.out.println("stop ？");
        scanner.next();
        thread.stop();

        Thread.sleep(Integer.MAX_VALUE);

    }



}
