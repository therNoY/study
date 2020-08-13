package concurrent.FutureAndPromise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JDK CompletableFuture异步回调测试
 */
public class JdkCompletableFutureTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
//        testThenApply(executor);
//        testThenAccept(executor);
        testExceptionally(executor);
//        testWhenComplete2HaveValue(executor);
//        testWhenComplete2Exception(executor);
    }

    /**
     * 对结果进行转换
     *
     * @param executor
     */
    public static void testThenApply(ExecutorService executor) {
        String result = CompletableFuture.supplyAsync(() -> "hello", executor).
                thenApply(s -> s + " world1").join();
        System.out.println(result);
    }

    /**
     * 结果打印输出
     *
     * @param executor
     */
    public static void testThenAccept(ExecutorService executor) {
        CompletableFuture.supplyAsync(() -> "hello", executor).
                thenAccept(s -> System.out.println(s + " world2"));
    }

    /**
     * 当运行时出现异常，可以通过此方式进行补偿
     *
     * @param executor
     */
    public static void testExceptionally(ExecutorService executor) {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试异常");
            }
            return "go";
        }, executor).exceptionally(e -> {
            System.out.println(e);
            return "hello world3";
        }).join();
        System.out.println(result);
    }

    /**
     * 记录每次运行完成的结果
     * 这里的完成有两种情况，一种是正常执行，有返回值；另外一种是抛出异常
     * 此处是抛出异常的情况
     *
     * @param executor
     */
    public static void testWhenComplete2HaveValue(ExecutorService executor) {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "go";
        }, executor).whenCompleteAsync((r, t) -> {
            System.out.println(r);
            System.out.println(t.getMessage());
        }, executor).exceptionally(e -> {
            System.out.println(e);
            return "hello world4";
        }).join();
        System.out.println(result);
    }

    /**
     * 记录每次运行完成的结果
     * 这里的完成有两种情况，一种是正常执行，有返回值；另外一种是抛出异常
     * 此处是抛出异常的情况
     *
     * @param executor
     */
    public static void testWhenComplete2Exception(ExecutorService executor) {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试异常");
            }
            return "go";
        }, executor).whenCompleteAsync((r, t) -> {
            System.out.println(r);
            System.out.println(t.getMessage());
        }, executor).exceptionally(e -> {
            System.out.println(e);
            return "hello world5";
        }).join();
        System.out.println(result);
    }

}