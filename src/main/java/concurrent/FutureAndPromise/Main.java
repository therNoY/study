package concurrent.FutureAndPromise;

import util.log.Log;

import java.util.concurrent.*;

public class Main {

    static Log log = Log.threadLog();

    static CountDownLatch mainLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask(()->{
            execThreadBlock();
            return "执行结果";
        });
        new Thread(futureTask).start();
        log.info("主线程执行完毕");
        while (!futureTask.isDone()) {
            String mes = futureTask.get();
            System.out.println(mes);
        }
    }

    private static Future<String> execBIO() {
        CompletableFuture<String> future = new CompletableFuture<String>();
        execThreadBlock();
        log.info("阻塞执行完毕");
        future.complete("mess");
        mainLatch.countDown();
        return future;
    }


    public static void execThreadBlock(){
        log.info("执行耗时操作");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}

interface Handel{
    void handle();
}
