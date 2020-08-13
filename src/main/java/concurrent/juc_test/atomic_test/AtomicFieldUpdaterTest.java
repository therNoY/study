package concurrent.juc_test.atomic_test;


import design_patterns.creater_type.prototype.Dog;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Atomic**FieldUpdater 用来修改对象内的字段
 */
public class AtomicFieldUpdaterTest {

    public static void main(String[] args) {

        Test test = new Test();

        AtomicIntegerFieldUpdater<Test> aUp = AtomicIntegerFieldUpdater.newUpdater(Test.class, "a");
        AtomicLongFieldUpdater<Test> bUp = AtomicLongFieldUpdater.newUpdater(Test.class, "b");
        AtomicReferenceFieldUpdater<Test, Dog> dogUp = AtomicReferenceFieldUpdater.newUpdater(Test.class, Dog.class, "dog");

        System.out.println(aUp.get(test));
        aUp.getAndIncrement(test);
        System.out.println(aUp.get(test));

        System.out.println(bUp.get(test));
        bUp.getAndIncrement(test);
        System.out.println(bUp.get(test));


        System.out.println(dogUp.get(test));
        dogUp.compareAndSet(test, test.dog, new Dog("2", 2, 1));
        System.out.println(dogUp.get(test));
    }

    static class Test{
        public volatile int a = 1;
        public volatile long b = 2l;
        public volatile Dog dog = new Dog("1", 1, 2);
    }

}
