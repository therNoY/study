package concurrent.juc_test.collections.concurrent_date;

import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * ConcurrentLinkedQueue 是使用cas操作保证并发的非阻塞队列
 * offer() 一定能成功 不成功会cas 一直转保证成功
 * poll() 一定会取出头 没有返回空，不会阻塞适合主动取的操作，取不到定时下次取
 * 设计上使用延时cas头结点和为节点的设计
 * size()需要便利全部链表
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {

        MyConcurrentLinkedQueue<String> concurrentLinkedQueue = new MyConcurrentLinkedQueue();
        
        concurrentLinkedQueue.offer("1");
        concurrentLinkedQueue.add("2");
        concurrentLinkedQueue.offer("3");
        concurrentLinkedQueue.offer("4");
        concurrentLinkedQueue.offer("5");
        concurrentLinkedQueue.offer("6");
        concurrentLinkedQueue.offer("7");
        concurrentLinkedQueue.offer("8");

        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());

        concurrentLinkedQueue.isEmpty();
    }
}
