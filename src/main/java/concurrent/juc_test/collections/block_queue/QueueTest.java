package concurrent.juc_test.collections.block_queue;

import util.log.Log;
import util.log.Logger;

import java.util.Comparator;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.*;

public class QueueTest {


    static Log log = Log.timeLog();

    public static void main(String[] args) throws InterruptedException {

        arrayBlockQueueTest();
        linkBlockQueueTest();
//        TestSpeed();
        priorityBlockQueueTest();
        delayQueueTest();
        synchronousQueuesTest();
        linkedTransferQueueTest();
        LinkedBlockDequeTest();
    }

    /**
     * 使用链表组成的双端队列 增加默认last 移除默认first 创建的时候有大小 默认不限
     * 只是使用了一把锁 count 实质上和LinkedBlockingQueue类似
     * put 加入已满等待 offer 加不进去返回  add加不进去报错
     * poll 取不到返回 take 取不到等待
     *
     * @throws InterruptedException
     */
    private static void LinkedBlockDequeTest() throws InterruptedException {
        MyLinkedBlockingDeque linkedBlockingDeque = new MyLinkedBlockingDeque();
        linkedBlockingDeque.put("1");
        linkedBlockingDeque.offer("2");
        linkedBlockingDeque.add("3");
        linkedBlockingDeque.putFirst("4");
        linkedBlockingDeque.addFirst("5");
        linkedBlockingDeque.offerFirst("6");


        linkedBlockingDeque.take();
        linkedBlockingDeque.takeLast();
        linkedBlockingDeque.poll();
        linkedBlockingDeque.pollFirst();
    }

    /*
     *
     * LinkedTransferQueue采用一种预占模式。意思就是消费者线程取元素时，
     * 如果队列不为空，则直接取走数据，若队列为空，那就生成一个节点（节点元素为null）入队，
     * 然后消费者线程被等待在这个节点上，后面生产者线程入队时发现有一个元素为null的节点，
     * 生产者线程就不入队了，直接就将元素填充到该节点，并唤醒该节点等待的线程，
     * 被唤醒的消费者线程取走元素，从调用的方法返回。我们称这种节点操作为“匹配”方式。
     * LinkedTransferQueue是ConcurrentLinkedQueue、 SynchronousQueue（公平模式下转交元素）、 LinkedBlockingQueue（阻塞Queue的基本方法）的超集。
     * 而且LinkedTransferQueue更好用，因为它不仅仅综合了这几个类的功能，同时也提供了更高效的实现。
     *
     * 有这个集中模式 now time async sync
     *
     * now 是尝试一次不成功直接返回 tryTransfer() poll()（返回null）
     * async 是异步会尝试cas直接放进去然后返回 put offer add 如果这个操作有get操作的话？
     * sync 是同步操作 取不到 或者放不进去 就阻塞 Transfer() take()
     *
     * LinkedTransferQueue是 SynchronousQueue 和 LinkedBlockingQueue 的合体，
     * 性能比 LinkedBlockingQueue 更高（没有锁操作），比 SynchronousQueue能存储更多的元素。
     * 当 put() add() offer() 时，如果有等待的线程，就直接将元素 “交给” 等待者， 否则直接将数据进入队列返回。
     * transfer() 是阻塞等待消费者拿到数据才返回。transfer方法和 SynchronousQueue的 put 方法类似。
     * tryTransfer() 会尝试一次直接交给消费者 交不到就返回 不会将数据放进队列中 也不会进入阻塞队列中
     *
     *
     *
     * */
    private static void linkedTransferQueueTest() throws InterruptedException {
        MyLinkedTransferQueue<String> queue = new MyLinkedTransferQueue<>();
//        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        System.out.println("输入t取值  输入其他加入");
        Scanner scanner = new Scanner(System.in);
        String nextString = null;
        while ((nextString = scanner.nextLine()) != null) {
            if (!nextString.startsWith("t")) {
                String finalNextString = nextString;
                new Thread(() -> {
                    try {
                        Logger.info("ready put ");
//                        queue.put(finalNextString + " _" + UUID.randomUUID().toString());
                        queue.transfer(finalNextString + " _" + UUID.randomUUID().toString());
//                        queue.tryTransfer(finalNextString + " _" + UUID.randomUUID().toString());
//                        queue.offer(finalNextString + " _" + UUID.randomUUID().toString());
                        Logger.info("put ok");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, "PutThread-" + nextString).start();
            } else {
                String finalNextString = nextString;
                new Thread(() -> {
                    try {
//                        String s = queue.takeAsync(new CallBack());
                        String s = queue.poll();
                        Logger.info("取出值： " + s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, "TakeThread-" + finalNextString).start();
            }
        }

    }


    private static class CallBack extends MyLinkedTransferQueue.GetDateCallBack<String>{

        @Override
        public void callBack() {
            String res = getE();
            Logger.info("成功设置回调" + res);
        }
    }

    /**
     * 该阻塞队列有公平和非公平两种模式 非公平是使用栈的结构，一定是保证先进后出
     * 即有多个线程进入栈中等待匹配结果唤醒头结点 头结点一定是最后一个进栈的线程
     * 而公平使用的是队列的结构，会保证先进先出
     *
     * 非公平 该阻塞队列 实际上只有一个点 维护了一个队列无论是取和存对应节点的不同模式
     * 当一个节点存放的时候都放到同一个栈中，当有相同模式的线程进来，就会重新设置栈头，当有不同的
     * 模式进来就会匹配走新的栈头，并且唤醒之前等待在栈头的线程
     * take 执行是一定会放进去 放不进去会等待，offer 会尝试放进去 放不进去直接返回
     * add 实际调用的offer() 不成功会报错（和ArrayBlockQueue LinkedBlock类似 都是有大小的）
     * offer() 和 poll() 不能同时用 必须有等待的线程才行
     * cpu数量大于1
     * 每次休眠之前尝试的次数是 32 （有定时）或者 （ 32 * 16)次(没有定时)
     *
     * cpu数量等于1
     * 直接修改 不进行休眠
     * https://www.jianshu.com/p/d5e2e3513ba3
     */
    private static void synchronousQueuesTest() throws InterruptedException {
        MySynchronousQueue<String> synchronousQueue = new MySynchronousQueue<>();

        System.out.println("输入t取值  输入其他加入");
        Scanner scanner = new Scanner(System.in);
        String nextString = null;
        while ((nextString = scanner.nextLine()) != null) {
            if (!nextString.startsWith("t")) {
                String finalNextString = nextString;
                new Thread(() -> {
                    try {
                        Logger.info("ready put ");
                        synchronousQueue.put(finalNextString + " _" + UUID.randomUUID().toString());
//                        synchronousQueue.offer(finalNextString + " _" + UUID.randomUUID().toString());
                        Logger.info("put ok");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, "Thread-" + nextString).start();
            } else {
                String finalNextString = nextString;
                new Thread(() -> {
                    try {
                        String s = synchronousQueue.take();
//                        String s = synchronousQueue.poll();
                        Logger.info("取出值： " + s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, "Thread-" + finalNextString).start();

            }
        }
    }


    /**
     * 无界队列
     * 延时队列使用的是一把锁 锁住线程不安全的PriorityQueue 并且维护了一个当前正在阻塞的线程
     * 入队列的对象必须实现 Delayed 接口 take会阻塞获取线程 poll 不会阻塞当前获取的线程
     * take 操作首先获取是否有节点 没有直接await等待signal 如果有但是第一个等待的线程还存在也是等待
     * 如果是第一个的线程就定时等待
     */
    private static void delayQueueTest() throws InterruptedException {
        DelayQueue<EnQueueObject> delayQueue = new DelayQueue<>();
        delayQueue.offer(new EnQueueObject("小明", 13));

        delayQueue.put(new EnQueueObject("小明", 5));
        delayQueue.put(new EnQueueObject("小红", 12));
        delayQueue.put(new EnQueueObject("小红", 1));
        delayQueue.put(new EnQueueObject("小路", 15));
        delayQueue.put(new EnQueueObject("小路", 3));
        delayQueue.put(new EnQueueObject("小加", 11));
        delayQueue.put(new EnQueueObject("小加", 2));

        EnQueueObject enQueueObject = null;
        for (; ; ) {
            while ((enQueueObject = delayQueue.take()) != null) {
                log.info(enQueueObject.name + " " + enQueueObject.age);
            }
        }
    }

    /**
     * 无界队列 虽然是数组但是到达最大值会自动扩容
     * 维护一个优先级队列 内部使用数组来维护 数组使用小顶堆来存放数据 使得插入和删除的时候
     * 都不会移动数据很多 每次插入和删除都需要获取锁，会自动扩容
     * 默认大小是11 可以传比较器 如果没有比较器 就是用对象自身的比较方法
     * <p>
     * 还有个 PriorityQueue 是没有同步方法的 线程不安全 使用的同样是小顶堆来实现的
     */
    private static void priorityBlockQueueTest() {

        MyPriorityBlockingQueue<EnQueueObject> priorityBlockingQueue =
                new MyPriorityBlockingQueue<>(11,
                        Comparator.comparingInt(o -> Integer.valueOf(o.name)));

        priorityBlockingQueue.offer(new EnQueueObject("4", 5));
        priorityBlockingQueue.offer(new EnQueueObject("5", 1));
        priorityBlockingQueue.offer(new EnQueueObject("3", 2));
        priorityBlockingQueue.offer(new EnQueueObject("0", 3));
        priorityBlockingQueue.offer(new EnQueueObject("2", 4));

        System.out.println(priorityBlockingQueue.myPoll());
        System.out.println(priorityBlockingQueue.myPoll());
        System.out.println(priorityBlockingQueue.myPoll());
        System.out.println(priorityBlockingQueue.myPoll());
        System.out.println(priorityBlockingQueue.myPoll());

    }


    /**
     * 测试Array和link的速度
     */
    private static void TestSpeed() {

        final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10000);
//        final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10000);
        final Long start = System.currentTimeMillis();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 999999; i++) {
                    try {
                        queue.put(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        for (int j = 0; j < 10; j++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            int i = queue.take();
                            if (i > 999997) {
                                System.out.println(System.currentTimeMillis() - start);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }


    /**
     * 有数组实现的有界阻塞队列 只有一个锁公平或者不公平 有两个状态标志位 putIndex takeIndex
     * 入队列和出队列都需要现获取锁offer 会直接放队列中如果满了就直接返回false put 会await()在进入
     * condition的同步队列中，等待take一个元素后唤醒;
     * poll 也是直接取 取不到返回空 取到了唤醒notfull 上的等待队列
     * enqueue 进队列是通过操作下标 如果一个数组全部占用了 下表挪到0
     * dequeue 出队列 操作出队列的下表
     *
     * @throws InterruptedException
     */
    private static void arrayBlockQueueTest() throws InterruptedException {
//        ArrayBlockingQueue<String> fairQueue = new ArrayBlockingQueue(2,true);
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue(300);
        // 放不进去返回false
        System.out.println(queue.offer("1"));
        System.out.println(queue.offer("2"));
        System.out.println(queue.offer("3", 100, TimeUnit.SECONDS));
        // 放不进去就阻塞
        queue.put("2");
        queue.add("2");
        queue.size();
        queue.poll();
        queue.take();
    }


    /**
     * 由链表实现的有界阻塞队列 有界是因为达到最大值会不添加 但是默认是最大值
     * 有两个锁 put 和 remove 都是非公平锁
     * 这个实现是使用两把锁两个同步等待队列，相对于ArrayBlockQueue效率更高（减少锁竞争）
     * 但是如果put和take同时操作的少的话ArrayBlokc效率会高 因为计数器count使用的是count
     * 而linked因为两把锁，对count的跟新要使用原子操作
     *
     * @throws InterruptedException
     */
    private static void linkBlockQueueTest() throws InterruptedException {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

        /**
         * put 操作时获取put队列的重入锁 获取到之后如果队列已经满了 就调用重入锁的condition 等待
         * offer 判断如果队列满了 就不会添加了
         * add 操作实际还是调用offer 只不过如果满了抛异常
         */
        linkedBlockingQueue.offer("1");
        linkedBlockingQueue.put("1");
        linkedBlockingQueue.add("1");
        linkedBlockingQueue.poll();
        linkedBlockingQueue.take();
        linkedBlockingQueue.remove();
        linkedBlockingQueue.remove("2");
        System.out.println("end");
    }
}
