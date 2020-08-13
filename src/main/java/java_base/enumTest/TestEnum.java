package java_base.enumTest;

public class TestEnum {
    public static void main(String[] args) {
        MyEnum instance = MyEnum.instance;
        instance.say();
    }
}

interface MyInterface {

    void say();
}


enum MyEnum implements MyInterface{
    instance;

    @Override
    public void say() {
        System.out.println("hello");
    }
}