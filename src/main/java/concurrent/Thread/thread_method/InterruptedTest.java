package concurrent.Thread.thread_method;

import util.log.Logger;

/**
 * 现成的 interrupt() 方法只是通知线程 告诉线程你被其他线程打断了，
 * 并不会使线程退出，真正的退出应该在发现自己被打断的时候，合理的释放资源退出。
 * 另外在抛出InterruptedException 的时候会重置被打断的状态。
 */
public class InterruptedTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 =new Thread(()->{
            if (true) {
                while (true){
                }
            }
            Logger.info("over");
        });

        Thread thread2 = new Thread(()->{
            if (true) {
                while (true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Logger.info("over");
        });



        thread1.start();
        thread2.start();

        Thread.sleep(2000);
        System.out.println("准备打断");
        thread1.interrupt();
        thread2.interrupt();


        System.out.println(thread1.isInterrupted());
        System.out.println(thread2.isInterrupted());
    }
}
