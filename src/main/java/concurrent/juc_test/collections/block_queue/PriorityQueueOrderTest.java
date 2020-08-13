package concurrent.juc_test.collections.block_queue;

/**
 * 测试排序
 */
public class PriorityQueueOrderTest {

    private MyComparable[] queue = new MyComparable[12];
    private int size = 0;

    public static void main(String[] args) {
        PriorityQueueOrderTest priorityQueueOrderTest = new PriorityQueueOrderTest();

        priorityQueueOrderTest.add(new MyComparable(3));
        priorityQueueOrderTest.add(new MyComparable(9));
        priorityQueueOrderTest.add(new MyComparable(5));
        priorityQueueOrderTest.add(new MyComparable(2));
        priorityQueueOrderTest.add(new MyComparable(1));
        priorityQueueOrderTest.add(new MyComparable(0));
        priorityQueueOrderTest.add(new MyComparable(10));
        priorityQueueOrderTest.add(new MyComparable(6));
        priorityQueueOrderTest.add(new MyComparable(8));
        priorityQueueOrderTest.add(new MyComparable(4));
        priorityQueueOrderTest.add(new MyComparable(7));



    }



    public void add(MyComparable object){
        siftUpComparable(size ++, object);
    }

    private void siftUpComparable(int size, MyComparable object) {
        while (size > 0) {
            int parent = (size - 1) >>> 1;
            MyComparable e = queue[parent];
            if (object.compareTo(e) >= 0)
                break;
            queue[size] = e;
            size = parent;
        }
        queue[size] = object;
    }


    private static class MyComparable implements Comparable{

        Integer i;

        public MyComparable(Integer i) {
            this.i = i;
        }

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }

        @Override
        public int compareTo(Object o) {
            MyComparable myComparable = (MyComparable) o;
            return i - myComparable.i;
        }
    }
}
