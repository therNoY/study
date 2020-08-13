package jvm_test.ClassLoader;


/**
 * 这里说明 在类加载使用loadClass 并没有执行类加载的动作,
 * 类加载的时机有
 * 1）遇到new、getstatic、putstatic或invokestatic这4条字节码指令时，
 * 如果类没有进行过初 始化，则需要先触发其初始化。
 * 生成这4条指令的最常见的Java代码场景是：
 *      使用new关键字
 *      实例化对象的时候、
 *      读取或设置一个类的静态字段（被final修饰、已在编译期把结果放入常 量池的静态字段除外）的时候，
 *      以及调用一个类的静态方法的时候。
 * 2）使用java.lang.reflect包的方法对类进行反射调用的时候，如果类没有进行过初始化， 则需要先触发其初始化。
 * 3）当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父 类的初始化。
 * 4）当虚拟机启动时，用户需要指定一个要执行的主类（包含main（）方法的那个 类），虚拟机会先初始化这个主类。
 * 5）当使用JDK 1.7的动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后
 * 的解析结果REF_getStatic、REF_putStatic、REF_invokeStatic的方法句柄，
 * 并且这个方法句柄 所对应的类没有进行过初始化，则需要先触发其初始化。
 */
public class ClassLoaderTime {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoaderTime stringPollTest = new ClassLoaderTime();
        Class<MyString> c = (Class<MyString>) stringPollTest.getClass().getClassLoader().loadClass("jvm_test.ClassLoader.MyString");
//        c.newInstance();
        String s3 = new StringBuffer("My").append("String").toString();
        System.out.println(s3.intern() == s3);
    }

}

class MyString {

    static String a = "FECourse";

    static String s = "MyString";

    static {
        System.out.println("?");
    }

    static void sayHello(){
        System.out.println("hello");
    }
}
