package jvm_test.JvmMemTest;

import java.util.ArrayList;
import java.util.List;


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

public class FinalVarTest {

    static String  base = "string";

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        for (int i=0;i< Integer.MAX_VALUE;i++){

            String str = base + base;

            base = str;

            list.add(str.intern());

        }

    }

}