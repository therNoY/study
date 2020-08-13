package java_base.static_test;

public class Test {

    static class Parent {

        static {
            if (true) {
                System.out.println(Thread.currentThread().getName() + "在初始化");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public static int A = 1;


    }

    static class Sub extends Parent {
        public static int B = A;
    }


    public static void main(String[] args) {

        Runnable r = () -> {
            System.out.println(Sub.B);
        };

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

    }
}


