package concurrent.juc_test.thread_pool;

import util.ScanUtil;

import java.util.concurrent.*;

/**
 * 1）如果当前运行的线程少于corePoolSize，则创建新线程来执行任务（注意，执行这一步骤 需要获取全局锁）。
 * 2）如果运行的线程等于或多于corePoolSize，则将任务加入BlockingQueue。
 * 3）如果无法将任务加入BlockingQueue（队列已满），则创建新的线程来处理任务（注意，执行这一步骤需要获取全局锁）。
 * 4）如果创建新线程将使当前运行的线程超出maximumPoolSize，任务将被拒绝，并调用 RejectedExecutionHandler.rejectedExecution()方法。
 */
public class ThreadPoolIntro {


    public static void main(String[] args) throws InterruptedException {
        baseTest();
    }

    /**
     * 线程池最全参数的构造函数
     *
     * ctl 说明 ctl 控制线程的数量 后29位 和线程池当前的状态 前三为
     *
     * 执行任务的时候首先会创建core线程 再次执行任务同样会先创建线程 及时之前的线程已经执行完了
     * 也不会重复执行 因为线程在等待从阻塞队列中获取 会一直处于wait 状态
     * 意味着核心线程池会随着任务的创建逐渐增加
     *
     * 然后才会用到阻塞队列 之后的任务创建会直接放到阻塞队列里面等待核心线程池take出来
     *
     * 当阻塞队列满了之后 会创建非核心线程 这些非核心线程完成自己的任务之后也会从阻塞队列里面获取
     * 但是这种创建核心和非核心是平等的 因为当活动线程数超过核心线程池时
     * 线程会使用超时获取阻塞队列内容的方式 如果一定时间获取不到 就会结束这个线程了
     * 结束线程首先从workersHashSet 中移除 有锁操早 然后正常终止
     *
     */
    private static void baseTest() {
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,// 核心线程数 线程池维持的基本线程数
//        MyThreadPoolExecutor pool = new MyThreadPoolExecutor(3,
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,
//        MyThreadPool pool = new MyThreadPool(2,
                5,// 最大线程数 当任务过多阻塞队列里面已经没法存下的时候会创建新的线程完成任务
                5,// 超过核心但是没到过最大线程数的线程存活时间
                TimeUnit.SECONDS, // 存活时间的单位
                new ArrayBlockingQueue<>(5),// 存放任务的阻塞队列执行不了任务会放到 阻塞队列里面
                Executors.defaultThreadFactory(),// 线程创建工厂
                ThreadPoolIntro::rejectedExecution);// 当任务无法执行的时候怎么办

        addJob(pool);
        ScanUtil.run(s -> {
            addJob(pool);
        });
    }

    private static void addJob(MyThreadPool pool) {
        for (int i = 0; i < 20; i++) {
//            pool.submit(new MyRunnable(i + 1));
            pool.execute(new MyRunnable(i + 1));
            System.out.println("增加第" + (i + 1) + "个任务时正在运行的worker大小： " + pool.getActiveCount() + "储存队列的大小" + pool.getQueue().size());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addJob(MyThreadPoolExecutor pool) {
        for (int i = 0; i < 20; i++) {
//            pool.submit(new MyRunnable(i + 1));
            pool.execute(new MyRunnable(i + 1));
            System.out.println("增加第" + (i + 1) + "个任务时正在运行的worker大小： " + pool.getActiveCount() + "储存队列的大小" + pool.getQueue().size());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addJob(ThreadPoolExecutor pool) {
        for (int i = 0; i < 20; i++) {
            pool.submit(new MyRunnable(i + 1));
            pool.execute(new MyRunnable(i + 1));
            System.out.println("增加第" + (i + 1) + "个任务时正在运行的worker大小： " + pool.getActiveCount() + "储存队列的大小" + pool.getQueue().size());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void myThink() {
        /**
         * 理解这个之后 再看线程池创建几种方法
         */
        /**
         * 1. newCachedThreadPool 可缓存线程池 源码：
         * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                                       60L, TimeUnit.SECONDS,
         *                                       new SynchronousQueue<Runnable>());
         * 说明 他的核心工作线程池大小是0 阻塞队列使用SynchronousQueue队列 是不存储任务的队列
         * 每创建一个线程 就new一个线程执行任务 执行完毕 在60s后销毁线程
         * 有任务如果此时没有空闲线程就会创建新的线程
         */
        Executors.newCachedThreadPool();
        /**
         * 2. newFixedThreadPool 可缓存线程池 源码：
         * return new ThreadPoolExecutor(nThreads, nThreads,
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>());
         * 该方法创建的是一个 固定大小的线程池 工作线程池和最大线程池是一样的 但是他的阻塞队列是创建的一个最大
         * 2^31 -1 的lined阻塞队列 多余的任务都会进入到阻塞队列里面
         */
        Executors.newFixedThreadPool(1);

        /**
         * 3. 创建一个定长线程池，支持定时及周期性任务执行 源码：
         * 创建爱你一个工作线程大小自定 最大线程2^31 -1 多的放到一个延迟队列里面
         *
         * super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
         *               new DelayedWorkQueue());
         */
        Executors.newScheduledThreadPool(1);
        /**
         * 4. 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务
         * return new FinalizableDelegatedExecutorService
         *             (new ThreadPoolExecutor(1, 1,
         *                                     0L, TimeUnit.MILLISECONDS,
         *                                     new LinkedBlockingQueue<Runnable>()));
         * 创建一个线程是是1 最大线程是1 多余的线程放到阻塞队列里面 最大值 Integer.MAX_VALUE
         */
        Executors.newSingleThreadExecutor();

        /**
         * 5. 创建一个有工作窃取性质的线程池
         * return new ForkJoinPool
         *             (Runtime.getRuntime().availableProcessors(),
         *              ForkJoinPool.defaultForkJoinWorkerThreadFactory,
         *              null, true);
         */
        Executors.newWorkStealingPool();
    }


    private static void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        throw new RuntimeException("Task " + r.toString() + " rejected from " + e);
    }

    private static void rejectedExecution(Runnable r, MyThreadPoolExecutor e) {
        throw new RuntimeException("Task " + r.toString() + " rejected from " + e);
    }

    static class MyRunnable implements Runnable {

        int i;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 执行第" + i + "个任务开始");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行第" + i + "个任务结束");
        }
    }
}



