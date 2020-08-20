package java_base.map;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * TreeMap 是一个有有顺序的map 内部基于红黑树实现
 *
 * 可以根据key 或者 value 或者key和value
 *
 * 取 加 判断是否包含都是log(n)
 */
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<String, MapObject> treeMap = new TreeMap<>();
        treeMap.put("h", new MapObject("大黄"));
    }

    static class MapObject{
        String name;

        public MapObject(String name) {
            this.name = name;
        }
    }

}
