package java_base;


import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        HashMap map = new HashMap(5);
        map.put("1", "2");
        String[] strings = new String[]{"1","2"};
        List<String> stringList = Arrays.asList(strings);
        stringList.toArray();
        stringList.add("3");
        for (String s : stringList) {
            System.out.println(s);
        }
//        File file = new File("D:\\Program\\Tomcat");
//        File[] files = file.listFiles();
//
//        for (int i = 0; i < files.length; i++) {
//            System.out.println(files[i].getName().split("\\.")[0]);
//        }


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
