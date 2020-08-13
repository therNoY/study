package java_base.reference_test;

import util.ScanUtil;
import util.log.Logger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试弱引用对gc 的影响
 *
 * 弱引用本身就是一个对象的引用，但是如果这个弱引用里面有定义其他属性，这些个属性就可能不会被回收
 *
 *
 * 测试中Pet 是Dog的一个弱引用 但是Pet内部又有一个Dog 的强引用
 * ThreadLocal 中 Entry是ThreadLocal一个弱引用 在数组上
 *
 * 当yongGc的时候会会回收 弱引用对象 但是这个对象中的dog 不会回收 只有这个线程结束的时候
 * 才会回收
 *
 */
public class WeakReferenceTest {

    static Object lock= new Object();

    /**
     * 这里的list可以理解为ThreadLocalMap中的 Entry[]
     * 这里Entry实际上是ThreadLocal的弱引用
     */
    static List<Pet> petList = new ArrayList<>();

    public static void main(String[] args) {

        ScanUtil.runInThread(s->{
            if (s.equals("gc")) {
                System.gc();
            }else if(s.equals("notify")){
                synchronized (lock) {
                    lock.notifyAll();
                }
            }else if(s.equals("clear")){
                petList = new ArrayList<>();
            }else {
                Pet pet = new Pet(new Dog("狗狗" + s));
                petList.add(pet);
                Logger.info("创建成功 准备等待");
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //如果为空，代表被回收了
                if (pet.get() == null) {
                    Logger.info("弱引用dog被回收了");
                }else {
                    Logger.info("弱引用dog还活着");
                }

                if (pet.dogInfo == null) {
                    Logger.info("狗狗信息没回收了");
                }else {
                    Logger.info("狗狗信息还活着");
                }

                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    /**
     * 这个Pet是Dog的弱引用
     * 实际上是ThreadLocal中的Entry
     */
    static class Pet extends WeakReference<Dog> {

        public Dog dogInfo;

        public Pet(Dog referent) {
            super(referent);
            dogInfo = new Dog(referent.getName() + "_INFO");
        }
    }


    /**
     * 这里的dog对应threadLocal
     */
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
    }
}




