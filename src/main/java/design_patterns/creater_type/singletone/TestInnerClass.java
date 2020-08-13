package design_patterns.creater_type.singletone;

public class TestInnerClass {

    public static Test test1 = new Test(1);

    /**
     * 类级内部类在类使用的时候没有加载 在第一次被引用的时候加载 区别于类级内部变量
     */
    private static class TestHolder{
        public static Test test2 = new Test(2);

        public TestHolder() {
            System.out.println("构建 TestHolder");
        }
    }


    public TestInnerClass() {
        System.out.println("构建 TestInnerClass");
    }
}


class Test {
    public Test(int i) {
        System.out.println("构建Test" + i);
    }
}