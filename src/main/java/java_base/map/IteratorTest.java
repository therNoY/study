package java_base.map;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IteratorTest {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();


        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);


        Iterator<Integer> integerIterator = set.iterator();

        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }

        Iterator<Integer> integerIterator2 = set.iterator();

        while (integerIterator2.hasNext()) {
            if (integerIterator2.next() % 2 == 0) {
                integerIterator2.remove();
            }
        }

        Iterator<Integer> integerIterator3 = set.iterator();

        while (integerIterator3.hasNext()) {
            System.out.println(integerIterator3.next());
        }


    }

}
