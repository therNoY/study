package java_base.java_lang;

public class RunTimeTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{


            System.out.println("啊 我要dead了");
        });


        synchronized (RunTimeTest.class) {
            RunTimeTest.class.wait();
        }

        Runtime.getRuntime().addShutdownHook(thread);



    }

}
