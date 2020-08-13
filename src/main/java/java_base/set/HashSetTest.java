package java_base.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class HashSetTest {

    public static void main(String[] args) {


//        testHashTest();
//        testLinkHashSet();



        testTreeSet();

    }

    /**
     * treeSet 同理 可以设置排序
     */
    private static void testTreeSet() {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("1");
    }


    /**
     * 底层维护的同样是一个linkedHashMap
     * 同时保证对修改有效维护没有重复的顺序
     */
    private static void testLinkHashSet() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("1");
    }

    /**
     * 底层实现竟然是自定一个一个hashMap 来保证无法重复 因为hashMap的key 无法重复
     */
    private static void testHashTest() {
        HashSet<String> stringSet = new HashSet<>();
        stringSet.add("1");
    }

}
