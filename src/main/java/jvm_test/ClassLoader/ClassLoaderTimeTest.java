package jvm_test.ClassLoader;


/**
 *
 * Q:为什么直接引用一个类中的静态常量不会初始化这个类呢
 * A:因为类加载过程分为 加载->验证->准备->解析->初始化->使用->卸载
 * 类的静态属性是在准备阶段分配在内存中的1.8之后是在堆中，之前是方法区中跟随类对象，此时会初始化类的类属性，但不是
 * 开发者定义的值而是系统定的，为类的类属性赋定义的值是在初始化阶段定义的，所以如果引用一个类的类对象，会初始化改类，但是如果
 * 引用一个类的常量类对象则不会，因为类的常量类对象是在类加载的准备阶段就在内存中分配好地址的。
 */
public class ClassLoaderTimeTest {


    public static void main(String[] args) {
        /**
         * 2. 这种情况没有一个类会被加载 因为使用的是final 字符串 实际引用的是
         * 在Java源码中引用了 ConstClass类中的常量HELLOWORLD，
         * 但其实在编译阶段通过常量传播优化，
         * 已经将此常量 的值“hello world”存储到了NotInitialization类的常量池中，
         * 以后NotInitialization对常量 ConstClass.HELLOWORLD的引用实际都被转化为
         * NotInitialization类对自身常量池的引用了
         */
//         System.out.println(SubClass.finalString);
//         System.out.println(SuperClass.finalString);


        /**
         * 1. 这里是使用父类的staticString 因此也不会加载子类的class 只会加载父类的
         */
//         System.out.println(SubClass.staticString);

        /**
         * 3. 这里使用的是子类的静态对象（使用静态方法也是一样） 会加载子类的class 对象
         * 但是子类又继承了父类 所以 又要先加载父类的class 对象
         */
//         System.out.println(SubClass.subStaticString);


        /**
         * 4. 这里不会触发任何class 对象的加载 触发的是Lorg.fenixsoft.classloading.SuperClass
         * 虚拟机自己生成的一个对象 一个数组对象
         */
//        SubClass[] subClasses = new SubClass[10];

//        System.out.println(SubInterface.subInterfaceString);
    }
}



class SuperClass{

    public static String staticString = "staticString";
    public static final String finalString ="finalString";

    static {
        System.out.println("<< SuperClass init >>");
    }
}

class SubClass extends SuperClass implements SubInterface{

    public static String subStaticString = "staticString";
    public static final String subFinalString ="finalString";

    static {
        System.out.println("<< SubClass init >>");
    }
}

interface SuperInterface{
    String superInterfaceString = "finalString1";
}

interface SubInterface extends SuperInterface{
    String subInterfaceString = "finalString2";
}