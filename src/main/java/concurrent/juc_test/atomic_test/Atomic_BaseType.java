package concurrent.juc_test.atomic_test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * 基本数据类型
 */
public class Atomic_BaseType {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();
        atomicInteger.compareAndSet(1, 2);
        System.out.println(atomicInteger.get());


        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.set(false);

        AtomicLong atomicLong = new AtomicLong();
        atomicLong.set(10);
        atomicLong.incrementAndGet();
    }
}
