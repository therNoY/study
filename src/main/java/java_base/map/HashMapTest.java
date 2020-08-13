package java_base.map;


import design_patterns.creater_type.prototype.Dog;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * 基于hash表散列实现
 *
 * 迭代无序
 *
 */
public class HashMapTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            System.out.println(spread(i+"".hashCode()) & 10);
        }

        MyHashMap<String, Dog> map = new MyHashMap<>(5);
        int length = 20;
        for (int i = 0; i < length; i++) {
            Dog dog = new Dog("大黄" + i, 1, i);
            map.put(dog.getName(), dog);
        }


        Iterator<Map.Entry<String, Dog>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Dog> next = entryIterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println("key=" + key + " value=" + map.get(key));

        }


        Dog dog1 = map.get("大黄" + 4);

    }
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }
}
