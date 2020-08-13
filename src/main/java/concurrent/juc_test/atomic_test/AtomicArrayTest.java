package concurrent.juc_test.atomic_test;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayTest {

    public static void main(String[] args) {
        AtomicIntegerArray  atomicIntegerArray = new AtomicIntegerArray(10);

        atomicIntegerArray.set(1, 10);
        atomicIntegerArray.incrementAndGet(1);

    }
}
