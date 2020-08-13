package jvm_test.JvmGcTest;


/**
 * 测试对象分配
 *
 * java -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails ObjectMakeTest
 *
 * 测试表明 对象优先分配在 new generation（Eden）区 如果 new generation内存不足会触发以及GC
 * 如果对象还活着就直接移到老年态(分配担保 不是通过计算对象的时间) 而大的对象直接分配在老年态
 */
public class ObjectMakeTest {

    private static final int _1mb = 1024 *1024;

    public static void main(String[] args) {

        byte[] a1, a2,a3, a4;

        System.out.println("开始分配");

        a1 = new byte[2 * _1mb];

        System.out.println("分配到第2个");

        a2 = new byte[2 * _1mb];

        System.out.println("分配到第3个");

        a3 = new byte[2 * _1mb];

        System.out.println("分配到第4个");

        a4 = new byte[4 * _1mb];

        System.out.println("结束");
    }
}
