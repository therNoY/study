package concurrent.juc_test.other;

import java_base.reference_test.WeakReferenceTest;
import util.ScanUtil;
import util.log.Logger;

/**
 * threadLocal 保存每个线程存放的数据只存在一个Thread 的ThreadLocalMap中
 * key 是ThreadLocal value 是set 的值
 *
 * 为什么是可能存在内存泄漏内？
 * 参考{@link WeakReferenceTest}
 * 如果一个线程被回收了 那么就不存在内存泄漏了，但是如果这个线程一直在线程池里面
 * 没有被回收就可能存在
 *
 */
public class ThreadLocalOOMTest {

    static Object lock= new Object();
    static ThreadLocal<Dog> threadLocal = new ThreadLocal();

    public static void main(String[] args) {

        ScanUtil.run(finalS->{

            if (finalS.equals("gc")) {
                System.gc();
            }else if(finalS.equals("notify")){
                synchronized (lock) {
                    lock.notifyAll();
                }
            }else if (finalS.equals("null")){
                threadLocal = null;
            }else {
                new Thread(()->{

                    threadLocal.set(new Dog("class.static." + finalS));

                    for (int i = 0; i < 10; i++) {
                        Logger.info("准备创建" + i +"个栈帧");
                        createAndAddThreadLocl(finalS);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Logger.info("========设置好参数后=======");
                    Logger.info(threadLocal.get());
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Logger.info("========第1次await后=======");
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Logger.info("========第2次await后=======");
                }, "Thread" + finalS).start();
            }
        });




        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Logger.info(threadLocal.get());
    }

    static void createAndAddThreadLocl(String s){
        ThreadLocal stackTheadLock = new ThreadLocal();
        stackTheadLock.set(new Dog("stack." + s));
    }

    static class Dog{
        private String name;

        private byte[] date;

        public Dog(String name) {
            this.name = name;
            // 一直狗大于5M
            this.date = new byte[1024 * 1024 * 10];
            Logger.info("创建对象" + this.name+"成功");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("dog" + name + "死了");
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class MyThreadLocal{
        ThreadLocal<Dog> threadLocal = new ThreadLocal();

        public void set(String name){
            threadLocal.set(new Dog(name));
        }

        public Dog get(){
            return threadLocal.get();
        }

    }
}


