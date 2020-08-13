package jvm_test.ClassLoader;

public class Hello {

    public static void main(String[] args) {
        System.out.println("boot:" + System.getProperty("sun.boot.class.path"));
        System.out.println("ext:" + System.getProperty("java.ext.dirs"));
        System.out.println("app:" + System.getProperty("java.class.path"));
    }
}
