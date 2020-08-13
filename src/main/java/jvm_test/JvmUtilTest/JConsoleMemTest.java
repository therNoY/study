package jvm_test.JvmUtilTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 运行时通过工具可以查看Yong Gen中Eden Space 和 Survivor Space的使用情况
 * 和 Old Gen 使用情况
 */
public class JConsoleMemTest {

    private static final int _1 = 1024;

    private static List list = new ArrayList();

    private static byte[] bytes = null;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        while (i < 1000) {

            if (i == 500) {
                System.gc();
            }

            list.add(new byte[_1]);
            bytes = new byte[_1];

            Thread.sleep(50);
        }

        System.gc();

        System.out.println("over...");


    }
}
