package concurrent.juc_test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainTest {

    static final int REQUEST = 0;
    /**
     * Node represents an unfulfilled producer
     */
    /**
     * 表示一个未满足的生产者
     */
    static final int DATA = 1;
    /**
     * Node is fulfilling another unfulfilled DATA or REQUEST
     */
    static final int FULFILLING = 2;

    public static void main(String[] args) {
//        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
//        queue.offer("哈哈哈");
//        queue.offer("123");
//        queue.offer("1111");
//        System.out.println("offer后，队列是否空？" + queue.isEmpty());
//        System.out.println("从队列中poll：" + queue.poll());
//        System.out.println("从队列中poll：" + queue.poll());
//        System.out.println("从队列中poll：" + queue.poll());
//        System.out.println("从队列中poll：" + queue.poll());
//        System.out.println("pool后，队列是否空？" + queue.isEmpty());


//        Date date = Date.from(LocalDateTime.of(2020,4,8,8,59,00,00).toInstant(ZoneOffset.of("+8")));
//        Map<Date, Object> map = new HashMap();
//        map.put(date, date.getTime());


//        long s= (long) map.get(date);

        System.out.println(Arrays.asList("1234567".split("")).subList(0, 5));

        System.out.println(FULFILLING | DATA);
        System.out.println(FULFILLING | REQUEST);
    }
}
