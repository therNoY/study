package jvm_test.ClassLoader;


public class ClassLoaderAndForName {
    static {
        System.out.println("Tester类的静态初始化块");
    }
}

/**
 * 类加载器只是加载 并不会初始化
 */
class ClassLoaderTest2 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        //下面语句仅仅是加载Tester类
        classLoader.loadClass("jvm_test.ClassLoader.ClassLoaderAndForName");
        System.out.println("系统加载Tester类");
        //下面语句才会初始化Tester类
        Class.forName("jvm_test.ClassLoader.ClassLoaderAndForName");
    }
}
//输出结果
// 系统加载Tester类
// Tester类的静态初始化块