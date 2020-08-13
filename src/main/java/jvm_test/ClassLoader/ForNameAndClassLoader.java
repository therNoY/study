package jvm_test.ClassLoader;

public class ForNameAndClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("[}]@".replaceAll("-2", ""));
        ForNameAndClassLoader.class.getClassLoader().loadClass("jvm_test.ClassLoader.MyTest");
        Class.forName("jvm_test.ClassLoader.MyTest");
    }

}


class MyTest{
    static {
        System.out.println("<C:init>");
    }
    public MyTest() {
        System.out.println("<O:init>");
    }
}
