package jvm_test.JvmGcTest;


/**
 * 判断对象经过一定次数的GC 如果还活着 进入年老太
 *
 * java -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:MaxTenuringThreshold=2 -XX:+PrintTenuringDistribution GCObjectAgeTest
 *
 */
public class GCObjectAgeTest {
    private static final int _1mb = 1024 * 1024;

    public static void main(String[] args) {

        byte[] a1, a2, a3, a4;

        System.out.println("开始分配");

        a1 = new byte[_1mb];

        System.out.println("GC...");

        System.gc();

        System.out.println("分配到第2个");

        a2 = new byte[_1mb];

        System.out.println("GC...");

        System.gc();

        System.out.println("分配到第3个");

        a3 = new byte[_1mb];

        System.out.println("GC...");

        System.gc();

        System.out.println("分配到第4个");

        a4 = new byte[_1mb];

        System.out.println("GC...");

        System.gc();
    }
}
