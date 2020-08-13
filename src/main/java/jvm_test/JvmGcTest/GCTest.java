package jvm_test.JvmGcTest;


/**
 * 这个例子说明 判断对象死亡的引用算法 不是JVM 使用的
 * java -Xmx8m -Xms8m -XX:+PrintGCDetails GCTest
 */
public class GCTest {
    private static final int _1mb = 1024 *1024;

    public Object instance;

    private byte[] bigSize = new byte[_1mb];

    public static void main(String[] args) {
        GCTest gcTest1 = new GCTest();
        GCTest gcTest2 = new GCTest();
        gcTest1.instance = gcTest2.instance;
        gcTest2.instance = gcTest1.instance;

        /* 手动GC */
        System.gc();

    }

}
