package concurrent.juc_test.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static concurrent.juc_test.unsafe.ChangeArrayValueTest.getUnsafeInstance;

/**
 * 获取对象实例 即使构造方法是私有的
 * 修改静态变量和实例变量的值
 */
public class GetPrivateClassInstance {


    public static void main(String[] args) throws Exception {

        Unsafe u = getUnsafeInstance();

//        changeInstanceVar(u);


//        testChangeObjectLong(u);

        compareAndSwapObjectTest(u);
    }

    /**
     * 测试 compareAndSwapObject 的使用
     * @param u
     * @throws NoSuchFieldException
     */
    private static void compareAndSwapObjectTest(Unsafe u) throws NoSuchFieldException {
        Test test = new Test();

        Class<?> k = Test.class;
        long nextOffset = u.objectFieldOffset(k.getDeclaredField("testObject"));

        u.compareAndSwapObject(test, nextOffset, null, new TestObject("11"));

        System.out.println(test.testObject);
    }

    /**
     * 测试改变对象的lang
     * @param u
     * @throws NoSuchFieldException
     */
    private static void testChangeObjectLong(Unsafe u) throws NoSuchFieldException {
        Field staticIntField = Test.class.getDeclaredField("staticIntField");

        /* 获取静态变量所属的类的首地址 返回的对象就是类对象 */
        Object o = u.staticFieldBase(staticIntField);

        System.out.println(o == Test.class);

        /* 获取静态变量地址偏移值 */
        Long b4 = u.staticFieldOffset(staticIntField);
        //因为是静态变量，传入的Object参数应为class对象
        System.out.println(u.getInt(Test.class, b4));
        u.putInt(o, b4, 10);

        System.out.println("staticIntField:" + u.getInt(Test.class, b4));
        System.out.println("staticIntField:" + Test.staticIntField);
    }


    /**
     * 测试改变实例变量
     * @param u
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    static void changeInstanceVar( Unsafe u) throws InstantiationException, NoSuchFieldException {
        /* 这里使用allocateInstance方法获取了一个Test类的实例，并且没有打印“constructor called”，说明构造方法没有调用。 */
        Test t = (Test) u.allocateInstance(Test.class);

        long b1 = u.objectFieldOffset(Test.class.getDeclaredField("intfield"));

        u.putInt(t, b1, 2);

        System.out.println("intfield:" + t.intfield);
    }

}


class Test {

    public int intfield = 1;

    public static int staticIntField = 1;

    public static int[] arr;

    public TestObject testObject = null;

    Test() {
        System.out.println("constructor called");
    }
}


class TestObject {


    private String name;

    public TestObject() {
    }

    public TestObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
