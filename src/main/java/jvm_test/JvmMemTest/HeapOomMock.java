package jvm_test.JvmMemTest;

/**
 * OutOfMemoryError 堆内存溢出 一直申请对象 不释放
 * 运行 java -Xmx 16m -Xms 16m -Xmn 8m
 * 说明 XMx 最大内存16M xms 初始内存8M xmn 年轻态内存8M
 */
import java.util.ArrayList;
import java.util.List;


public class HeapOomMock {

    public static void main(String[] args) {

        List<byte[]> list = new ArrayList<>();
        int i = 0;
        boolean flag = true;
        while (flag) {
            try {
                i++;
                list.add(new byte[1024 * 1024]);//每次增加一个1M大小的数组对象
            } catch (Throwable e) {
                e.printStackTrace();
                flag = false;
                System.out.println("count=" + i);//记录运行的次数
            }
        }
    }
}