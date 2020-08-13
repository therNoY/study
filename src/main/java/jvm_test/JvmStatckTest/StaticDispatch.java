package jvm_test.JvmStatckTest;


/**
 * 这里都是父类的
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy！");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman！");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady！");
    }

    public static void main(String[] args) {
        Human woman = new Woman();
        Human man = new Man();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(woman);
        sr.sayHello(man);

    }
}