package concurrent.juc_test.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 没有发现可以直接获取对象地址的方法，Unsafe中操作地址相关的方法都要求提供一个Object类型的参数，用来获取对象的初始地址。
 *
 * 修改和读取数组中的值
 */
public class ChangeArrayValueTest {


    public static void main(String[] args) throws Exception {
        Unsafe u = getUnsafeInstance();

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        /**
         * 返回当前数组第一个元素地址相对于数组起始地址的偏移值
         */
        int b = u.arrayBaseOffset(int[].class);

        /**
         * 回当前数组一个元素占用的字节数
         */
        int s = u.arrayIndexScale(int[].class);
        /*  获取数组对象obj的起始地址，加上偏移值，得到对应元素的地址，将 value 写入内存。 */
        u.putInt(arr, (long) b + s * 9, 0);

        for (int i = 0; i < 10; i++) {

//            int v = u.getInt(arr, (long) b + s * i);
            int v = arr[i];

            System.out.print(v + " ");

        }
    }


    public static Unsafe getUnsafeInstance() throws Exception {
        Field unsafeStaticField =
                Unsafe.class.getDeclaredField("theUnsafe");
        unsafeStaticField.setAccessible(true);
        return (Unsafe) unsafeStaticField.get(Unsafe.class);
    }
}
