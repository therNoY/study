package java_base.static_test;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("FECourse");
        System.out.println(StaticTest.class.getName());
        StaticTest.sayHello();
        System.out.println("b");
        StaticTest staticTest = new StaticTest();
    }
}
