package concurrent.Thread.thread_method;


import util.log.ThreadLog;

/**
 * Java中join()方法的理解
 * thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
 * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B
 */
public class JoinTest {

    //main方法如下
    public static void main(String[] args) throws InterruptedException {

        ThreadLog log = new ThreadLog();

        Thread threadA = new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(i + "");
            }
        });

        Thread threadB = new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 10) {
                    try {
                        threadA.join(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info(i + "");
            }
        });

        threadB.start();
        threadA.start();
        threadB.join();

        System.out.println("OVER");

    }


}

