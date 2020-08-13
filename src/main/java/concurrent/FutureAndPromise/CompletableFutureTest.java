package concurrent.FutureAndPromise;

import sun.nio.ch.ThreadPool;
import util.log.Log;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * 这个类是学习 CompletableFuture的使用的一个类
 */
public class CompletableFutureTest {

    static Log log = Log.threadLog();

    static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);



    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

//        testFutureLambda();

        testSupplyAsync();

//        testThenAccept();

//        testThenApply();

//        testThenApply2();

//        testThenCompose();

//        testThenCombine();

//        testAnyOf();

        log.info("主线程 over::");
        countDownLatch.await();

    }



    /**
     * allOf 和 anyOf 从字面意思理解
     */
    private static void testAnyOf() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("future准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future阻塞结束");
            return "hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("future2准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future2阻塞结束");
            return " world";
        });
//
//        CompletableFuture.allOf(future, future2).thenAccept(FECourse->{
//            log.info("两个任务都结束了");
//        });
        CompletableFuture.anyOf(future, future2).thenAccept(a -> {
            log.info("有一个结束了");
        });
    }

    /**
     * thenCombine 也是将两个future 结合 但是thenCompose 和 thenCombine区别是
     * thenCompose 是一个等待另一个完成  thenCombine是同时进行都得到结果时进行下一个
     */
    private static void testThenCombine() {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("future准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future阻塞结束");
            return "hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("future2准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future2阻塞结束");
            return " world";
        });
        CompletableFuture<String> future3 = future.thenCombine(future2, (s, s2) -> {
            log.info("future3处理");
            return s + s2;
        });

        future3.thenAccept(s -> {
            log.info(s);
        });
    }

    private static void testThenApply2() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            log.info("====future=====");
            return 2;
        });
        future.thenApply(res -> {
            log.info("====future=====");
            return res + 1;
        }).thenApply(res -> {
            log.info("====future=====");
            return res + 1;
        }).thenAccept(res -> System.out.println(res));

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            log.info("====future2=====");
            return 2;
        });
        future2.thenApplyAsync(res -> {
            log.info("====future2=====");
            return res + 1;
        }).thenApplyAsync(res -> {
            log.info("====future2=====");
            return res + 1;
        }).thenAccept(res -> System.out.println(res));
    }

    /**
     * thenApply 和 thenCompose 都是对一个异步任务执行的结果的处理
     * thenApply（）转换的是泛型中的类型，是同一个CompletableFuture；
     * thenCompose（）用来连接两个CompletableFuture，是生成一个新的CompletableFuture。
     * <p>
     * thenApply 就是单纯的结果处理 thenCompose主要是连接两个future 一个 future等待另一个future执行完毕后执行
     */
    private static void testThenCompose() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("阻塞结束");
            return "hello";
        });

        CompletableFuture<StringBuffer> future2 = future.thenComposeAsync(res -> CompletableFuture.supplyAsync(() -> {
            try {
                log.info("thenCompose 准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(" thenCompose阻塞结束");
            return new StringBuffer(res).append("world");
        }));

        future2.thenAccept(res -> {
            log.info("结果转换的结果" + res.toString());
        });
    }

    /**
     * thenApply 对结果进行转换 可以改变参数的类型 是对异步执行结果的第二次操作
     */
    private static void testThenApply() {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("阻塞结束");
            return "hello";
        });

        CompletableFuture<StringBuilder> futureBuf = future.thenApplyAsync(s -> {
            try {
                log.info("thenApply 准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("thenApply 阻塞结束");
            return new StringBuilder(s + "world");
        });

        futureBuf.thenAccept(stringBuilder -> {
            log.info("结果转换的结果" + stringBuilder.toString());
        });

    }

    /**
     * supplyAsync 是提交一个任务交给新的线程（ForkJoinPool）去异步执行 还会有返回结果
     * 接受的是一个生产者 Supplier 相当于callable
     * <p>
     * runAsync 是简单提交一个任务当任务完成后会有通知但是没有结果Void
     * 接受的是一个 Runnable
     */
    public static void testSupplyAsync() {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String a = null;
            a.length();
            log.info("阻塞结束");
            return "hello";
        });

        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            try {
                log.info("准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("阻塞结束");
        });

        future.whenComplete((result, throwable) -> {
            log.info("supplyAsync 执行结果" + result);
        });

        future.exceptionally(throwable -> {
            log.warn(throwable.getMessage());
            return throwable.getMessage();
        });

        runAsync.whenComplete((result, throwable) -> {
            log.info("runAsync 执行结果" + result);
        });
    }


    /**
     * thenAccept 和 whenComplete 都是对结果的处理 后面加上async 都代表异步，否则只是代表非阻塞
     * <p>
     * 区别是前者只接受一个参数 不处理异常 后者接受结果和异常
     * <p>
     * 返回值也不一样
     */
    public static void testThenAccept() {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            String FECourse = null;
//            FECourse.length();
            log.info("阻塞结束");
            return "hello";
        });


        CompletableFuture<Void> accept = supplyAsync.thenAccept(result -> {
            log.info("thenAccept :" + result);
        });

        CompletableFuture<String> complete = supplyAsync.whenComplete((res, err) -> {
            log.info("whenComplete :" + res);
        });
    }

    /**
     * 测试高级complete 用法
     */
    private static void testFutureComplete() {
        CompletableFuture<String> future = new CompletableFuture<>();
        Thread thread = new Thread(new MyThread(future));
        thread.setName("IO线程");
        thread.start();
        // 自动执行的非阻塞操作
        future.whenComplete((s, throwable) -> {
            log.info("whenComplete 执行结果" + s);
        });
    }


    /**
     * 测试基础complete用法 当一个线程执行完的时候执行complete 方法
     */
    public static void testFutureLambda() {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            try {
                log.info("准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("阻塞结束");
            future.complete("hello");
        }).start();

        future.whenComplete((s, throwable) -> {
            log.info("异步执行结果" + s);
        });
    }

    private static class MyThread implements Runnable {

        CompletableFuture f;

        public MyThread(CompletableFuture<String> future) {
            this.f = future;
        }

        @Override
        public void run() {
            try {
                log.info("准备睡眠");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("阻塞结束");
            f.complete("hello");
        }
    }
}
