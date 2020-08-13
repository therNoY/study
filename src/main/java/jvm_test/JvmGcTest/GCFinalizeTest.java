package jvm_test.JvmGcTest;


import util.log.Logger;

/**
 * 在一个对象在堆中被回收时 会执行一次优先级很低的finalize 方法
 */
public class GCFinalizeTest {



    static GCFinalizeTest finalizeObject = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        Logger.info("有GC 执行了 我要努力活下去");

        finalizeObject = this;

    }

    public static void main(String[] args) throws InterruptedException {
        finalizeObject = new GCFinalizeTest();

        finalizeObject = null;

        System.gc();

        Thread.sleep(500);

        if (finalizeObject == null) {
            Logger.info("我 dead 了");
        }else {
            Logger.info("我还活着");
        }

        finalizeObject = null;

        System.gc();

        Thread.sleep(500);

        if (finalizeObject == null) {
            Logger.info("我 dead 了");
        }else {
            Logger.info("我还活着");
        }
    }

}
