package jvm_test.JvmMemTest;

/**
 * 如果jdk 1.6 这些都是false
 * String.intern()是将string对象放到stringpool中 本来有返回池上的引用 没有返回对象的引用
 * <p>
 * <p>
 * 自己总结;
 * 1. 首先理解有三个常量池
 * class 文件常量池 运行时常量池 全局字符串常量池
 * class 常量池是在.class 文件的字节码中可以看到,
 * 运行时常量池是在每个类或者接口加载到方法区之后为每个类或者接口根据class文件常量池生成的
 * 全局字符串常量池 可以理解为String类加载到方法区之后生成的运行时常量池,当出现"" 和String.intern()方法时会将其放进池中 (＾－＾)V
 * 2. 其次理解String.intern 方法
 * jdk 1.8之后 是这样的
 * 调用intern方法时，如果池中已经包含一个等于此{@code String}对象的字符串，取决于{@link #equals（Object）} ，
 * 则返回池中的对象引用。 否则，此{@code String}对象将添加到池，并返回对此{@code String}对象的引用。
 * jdk 1.6中 返回的都是池上的引用
 * <p>
 * 3. 在运行时遇到引导包含的字符串的时候 会进行放置到池中
 * <p>
 * 4. String.intern() 并没有改变当前对象的地址
 * <p>
 * 5.，由于String是一个不可变的类，对字符串的连接操作总是通过生成新的 String对象来进行的，
 * 因此Javac编译器会对String连接做自动优化。
 * 在JDK 1.5之前，会转化 为StringBuffer对象的连续append（）操作，
 * 在JDK 1.5及以后的版本中，会转化为 StringBuilder对象的连续append（）操作，
 * <p>
 * 6. 字符串常量池是存的引用
 */
public class StringConstantPoolTest {


    public static void main(String[] args) {


        /**
         * 该结果返回true
         * 1. new String("") 创建两个对象 一个常量池上的"11" 一个堆上的Object
         * 2. s.intern() 返回常量池上的"11"的地址 与 "11" 相同返回true
         * 第二个返回false
         */
//        String s = new String("11");
//        String s1 = null;
//        System.out.println(s.intern() == (s1 = "11"));
//        System.out.println(s == (s1 = "11"));

        /**
         * 结果返回false
         * 1. new String("") 创建两个对象 一个常量池上的"11" 一个堆上的Object
         * 2. str1.intern()同样返回常量池上的地址"11" 与 队中的不同
         */
//        String str1 = new String("11");
//        System.out.println(str1.intern() == str1);


        /**
         *
         * 1. 创建6个对象堆上11 1 常量池上11 1 和通过 StringBuilder 建立的StringBuilder 和String对象（toString得到）
         * 2. 堆上没有 但是jdk 1.7 常量池中不需要再存储一份对象了，可以直接存储堆中的引用。这份引用直接指向 s3 引用的对象，
         * 也就是说s3.intern() ==s3会返回true
         */
//        String str5 = new String("11") + new String("1");
//        System.out.println(str5.intern()==str5);


        /*
         * 1. 创建6个对象堆上11 1 常量池上11 1 和通过 StringBuilder 建立的StringBuilder 和String对象（toString得到）
         * 2  将堆上对象引用放到常量池中
         * 3. str6 作为池上的引用
         * 4. 池上的引用 与对象引用指向的是同一地址 true
         */
//        String str5 = new String("11") + new String("1");
//        str5.intern();
//        String str6 ="111";
//        System.out.println(str5==str6);


        /**
         *
         * 这里与前面相同 但是这里先创建一个池上的对象 是 "111"
         * 这个时候池中已经有对象了，所以str5。intern() 相当于没执行
         * 池中的对象和堆中对象不是统一对象 false
         */
//        String str5 = new String("11") + new String("1");
//        String str6 = "111";
//        str5.intern();
//        System.out.println(str5 == str6);


    }
}
