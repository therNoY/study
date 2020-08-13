
package jvm_test.JvmMemTest;

/**
 *java -Xmx2m -XX:PermSize=2M -XX:MaxPermSize=3M StaticBaseVarTest
 *
 *
 * 运行在jdk 1.6上 java.lang.OutOfMemoryError: PermGen space 永久代
 *
 * 运行在jdk 1.7上 同上
 *
 * 运行在jdk 1.8 上
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at StaticBaseVar.<clinit>(StaticBaseVar.java:2)
 *
 */
public class StaticBaseVarTest {

    static int[] as = new int[100000];

    static {
        for (int i = 0; i < 100000; i++) {
            as[i] = i * i * i;
        }
    }

    public static void main(String[] args) {
        System.out.println("?");
    }
}
