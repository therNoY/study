package concurrent.Thread.thread_method;

import java.util.Scanner;


/**
 * 执行了 一个对象的wait() 方法必须获取该对象的锁，然后执行后会释放该对象的锁，
 * 之后该对象会进入wait 并放弃该对象锁，其他线程才可以获取该对象锁，此时线程进入线程等待状态{Thread.State.WAITING} 进入该对象的等待队列中
 * 其他线程执行该对象的notify 或者 notifyAll 犯法(其他线程执行这两个方法同样 同样需要获取锁)，
 * 会通知等待这个对象的冲从等待队列中移除 进入同步队列中 准备重新获取该对象锁，然后执行方法
 *
 */
public class WaitTest {
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
