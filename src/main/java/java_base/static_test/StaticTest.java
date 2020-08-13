package java_base.static_test;

public class StaticTest {

    static Dog dog = new Dog("1");

    Dog dog2 = new Dog("2");

    public StaticTest() {
        System.out.println("构造函数");
    }

    {
        System.out.println("构造代码块1");
    }

    static {
        System.out.println("static 代码块1");
    }

    {
        System.out.println("构造代码块2");
    }

    static Dog dog3 = new Dog("3");

    static {
        System.out.println("static 代码块2");
    }

    static void sayHello(){
        System.out.println("hello");
    }

}


class Dog {

    static Dog dog = new Dog("静态");


    public Dog(String name) {
        System.out.println("新建dog" + name);
    }

}
