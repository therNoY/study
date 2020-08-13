package concurrent.juc_test.collections.concurrent_date;


/**
 * 理解currentHashMap 需要先理解HashMap
 * jdk 1.7 的HashMap 使用的是数组加链表的结构，但是存在key的hash值相同会落在同一个链表上的情况
 * 所以存在便利慢 所以jdk1.8做了优化 具体是增加了红黑树这个数据结构 当链表的长度大于一定值的时候
 * 会将链表准成红黑树；
 *
 * 而currentHashMap 在jdk1.7的时候使用的分段锁来实现线程安全 在一段hashMap加锁，但是还是会存在效率慢的问题
 * 所以1.8 改用了synchronized 和Cas 来实现，并且加锁的力度在一个桶上来实现线程安全
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        MyConcurrentHashMap<String, String> concurrentHashMap = new MyConcurrentHashMap<>();
        concurrentHashMap.put("k1", "v1");
        concurrentHashMap.get("k1");
        concurrentHashMap.remove("k1");
        concurrentHashMap.size();
    }
}
