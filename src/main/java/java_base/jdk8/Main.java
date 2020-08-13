package java_base.jdk8;

public class Main {
    public static void main(String[] args) {
        MyImplant myImplant = new MyImplant();
        myImplant.handle(new ParaObj("AAA"));
        MyImplant myImplant1 = new MyImplant(){
            @Override
            public void handle(ParaObj obj) {
                System.out.println("修改的" + obj.getName());
            }
        };
        myImplant1.handle(new ParaObj("BBB"));
    }

    public static void method1() {
        Main main = new Main();
        ParaObj paraObj = new ParaObj();
        paraObj.setName("测试");
        MyInterface myInterface = Main::contentHandle;
        test(myInterface, paraObj);
        test(obj -> {
        },paraObj);
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }

    public static void contentHandle(ParaObj obj) {
        System.out.println(obj.getName());
    }

    public static void test (MyInterface myInterface, ParaObj obj) {
        myInterface.handle(obj);
    }
}

class MyImplant implements MyInterface{

    @Override
    public void handle(ParaObj obj) {
        System.out.println("原始的" + obj.getName());
    }
}
