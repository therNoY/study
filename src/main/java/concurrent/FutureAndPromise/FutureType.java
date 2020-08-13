package concurrent.FutureAndPromise;

import util.log.Log;

import java.util.concurrent.*;

/**
 * 简单的future 的用法
 * 这是异步操作
 */
public class FutureType {

    static Log log = Log.threadLog();
    static CountDownLatch mainLatch = new CountDownLatch(1);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
            log.info("执行耗时操作...");
            timeConsumingOperation();
            return 100;
        });//<1>
        //其他耗时操作..<3>
        log.info("主线程运算耗时:" + (System.currentTimeMillis() - l) + " ms");
        mainLatch.await();
    }
    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
            log.info("执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}