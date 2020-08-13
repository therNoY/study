package design_patterns.creater_type.singletone;

public class MyTest {



    public static void main(String[] args) throws InterruptedException, IllegalAccessException, InstantiationException {
        System.out.println("hahaha");
        Thread.sleep(1000);
        System.out.println(TestInnerClass.class.getSimpleName());
        System.out.println(TestInnerClass.class.newInstance());
    }
}
