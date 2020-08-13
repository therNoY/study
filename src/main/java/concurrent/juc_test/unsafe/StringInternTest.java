package concurrent.juc_test.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static concurrent.juc_test.unsafe.ChangeArrayValueTest.getUnsafeInstance;

public class StringInternTest {

    public static void main(String[] args) throws Exception {

        Unsafe u = getUnsafeInstance();

        /**
         * 创建两个对象 常量池上"ab" 和 堆上ab
         * 但是这连个String 对象存的实际值是一个
         */

        String abc = new String("ab") + new String("c");
        String abc2 = "abc";
        abc.intern();

        char[] chars = getStringValue(u, abc);
        char[] chars1 = getStringValue(u, abc2);


    }



    static void testChangeStringValue(Unsafe u) throws NoSuchFieldException {
        String s = "abc";
        //保存s的引用
        s.intern();
        //此时s1==s，地址相同
        String s1 = "abc";
        //获取s的实例变量value
        Field valueInString = String.class.getDeclaredField("value");
        //获取value的变量偏移值
        long offset = u.objectFieldOffset(valueInString);
        //value本身是一个char[],要修改它元素的值，仍要获取baseOffset和indexScale
        long base = u.arrayBaseOffset(char[].class);
        long scale = u.arrayIndexScale(char[].class);
        //获取value
        char[] values = (char[]) u.getObject(s, offset);

        //为value赋值
        u.putChar(values, base + scale, 'c');
        System.out.println("s:" + s + " s1:" + s1);
        //将s的值改为 abc
        s = "abc";
        System.out.println(s.equals("abc"));
        String s2 = "abc";
        String s3 = "abc";
        System.out.println("s:" + s + " s1:" + s1);
    }

    /**
     * 获取String 对象的char[]
     * @param u
     * @param s
     * @return
     * @throws NoSuchFieldException
     */
    static char[] getStringValue(Unsafe u,String s) throws NoSuchFieldException {
        //获取s的实例变量value
        Field valueInString = String.class.getDeclaredField("value");
        //获取value的变量偏移值
        long offset = u.objectFieldOffset(valueInString);
        //获取value
        char[] values = (char[]) u.getObject(s, offset);
        return values;
    }




}
