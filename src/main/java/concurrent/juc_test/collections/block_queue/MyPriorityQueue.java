package concurrent.juc_test.collections.block_queue;

import java.util.Comparator;

/**
 * 自己实现的优先级队列{@link java.util.PriorityQueue}
 */
public class MyPriorityQueue<E> {


    private Object[] queue;

    private int length;

    private Comparator<E> comparator;

    public MyPriorityQueue(int size) {
        queue = new Object[size];
    }

    public MyPriorityQueue(int size, Comparator<E> comparator) {
        queue = new Object[size];
        this.comparator = comparator;
    }


    public void put(E e) {

        if (e instanceof Comparable) {

            int i = 0;
            while (i < length) {

            }


        }else {

        }

    }

    public E take() {
        return takeTail();
    }

    public E takeTail(){
        return null;
    }

    public E takeHead(){
        return null;
    }

    public void show(){


    }
}
