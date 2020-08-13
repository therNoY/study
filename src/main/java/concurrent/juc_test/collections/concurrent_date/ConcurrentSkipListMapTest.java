package concurrent.juc_test.collections.concurrent_date;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 初步理解
 * 跳表适用于插入删除比较多的情况 所以需要用链表来实现
 * 但是链表不适合使用二分查找 所以使用另一种空间换取时间的数据关系 跳表
 *
 * 以空间换取时间
 *
 */
public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {
        ConcurrentSkipListMap<String, DogMap> map = new ConcurrentSkipListMap<>();
        map.put("5", new DogMap("大黄5"));
        map.put("3", new DogMap("大黄3"));
        map.put("2", new DogMap("大黄2"));
        map.put("1", new DogMap("大黄1"));
        map.put("0", new DogMap("大黄0"));

    }


    static class DogMap{
        private String name;

        public DogMap(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
