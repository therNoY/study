package java_base;


import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        File file = new File("D:\\Program\\Tomcat");
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName().split("\\.")[0]);
        }


//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        CompletableFuture completableFuture = CompletableFuture.runAsync(()->{
//            forkJoinPool.invoke(new MyAction());
//            System.out.println("end2");
//        });
//
//        System.out.println("end");
//
//
//        completableFuture.whenComplete((res,err)->{
//            System.out.println("end3");
//        });
//
//        System.out.println("end4");
//
//        Thread.sleep(Integer.MAX_VALUE);
    }

    static class MyAction extends RecursiveAction{

        @Override
        protected void compute() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("end1");
        }
    }

}
