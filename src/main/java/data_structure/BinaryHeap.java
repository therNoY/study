package data_structure;

import java.util.*;

/**
 * 实现的小顶堆
 * 对于每一个节点 他的值总比他的子节点小
 * @param <E>
 */
public class BinaryHeap<E> extends AbstractQueue<E> implements java.io.Serializable {

    int size;
    int capacity = 7;
    Object[] queue;

    Comparator<E> comparator = null;


    public BinaryHeap() {
        init();
    }

    public BinaryHeap(int capacity) {
        this.capacity = capacity;
    }

    public BinaryHeap(int capacity, Comparator<E> comparator) {
        this.capacity = capacity;
        this.comparator = comparator;
    }

    private void init() {
        queue = new Object[capacity];
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();

        for (int i = 0; i < 5; i++) {
            binaryHeap.offer(new Random(i).nextInt(9));
        }

        while (binaryHeap.peek() != null) {
            System.out.println(binaryHeap.poll());
        }

    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(E e) {
        if (size == 0) {
            queue[0] = e;
        } else {
            if (size >= capacity) {
                resize();
            }
            int index = size, pi = index;
            if (comparator != null) {
                E insert = e;
                while (pi > 0) {
                    E compareE = (E) queue[pi = (index - 1) >>> 1];
                    if (comparator.compare(insert, compareE) > 0) {
                        break;
                    }
                    queue[index] = queue[pi];
                    index = pi;
                }
                queue[index] = e;
            } else if (e instanceof Comparable) {
                Comparable insert = (Comparable) e;
                while (pi > 0) {
                    Comparable compareE = (Comparable) queue[pi = (index - 1) >>> 1];
                    if (insert.compareTo(compareE) > 0) {
                        break;
                    }
                    queue[index] = queue[pi];
                    index = pi;
                }
                queue[index] = e;
            }else {
                throw new RuntimeException("无法比较");
            }
        }
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) return null;
        Object res = queue[0];
        int index = 0, li, ri;
        if (comparator != null) {
            while (index < size) {
                li = index * 2 + 1;
                ri = li + 1;
                if (comparator.compare((E) queue[li], (E) queue[ri]) < 0) {
                    queue[index] = queue[li];
                    index = li;
                } else {
                    queue[index] = queue[ri];
                    index = ri;
                }
            }
        } else if (queue[0] instanceof Comparable) {
            while (index < size) {
                li = index * 2 + 1;
                ri = li + 1;

                if (li >= size) {
                    queue[index] = queue[size - 1];
                    break;
                } else if (ri >= size) {
                    queue[index] = queue[li];
                    queue[li] = queue[size - 1];
                    break;
                } else {
                    Comparable le = (Comparable) queue[li];
                    Comparable re = (Comparable) queue[ri];
                    if (le.compareTo(re) < 0) {
                        queue[index] = queue[li];
                        index = li;
                    } else {
                        queue[index] = queue[ri];
                        index = ri;
                    }
                }
            }
        }
        queue[--size] = null;
        return (E) res;
    }

    @Override
    public E peek() {
        if (size > 0) {
            return (E) queue[0];
        }
        return null;
    }

    public void resize() {
        Object[] newQueue = new Object[(capacity = (int) (capacity * 1.5))];
        System.arraycopy(queue, 0, newQueue, 0, size);
        queue = newQueue;
    }
}
