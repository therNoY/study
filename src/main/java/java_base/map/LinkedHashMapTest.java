package java_base.map;


import design_patterns.creater_type.prototype.Dog;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * LinkedHashMap 继承了 HashMap 实现了迭代有序
 *
 * 内部维护了一个新的Node 这个node继承了HashMap的Node 实际使用的是LinkedHashMap的Node
 * 重写的newnode()
 *
 * 这个在原来的基础上新增加了before 和 after 来保证顺序
 *
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        System.out.println(
                Math.ceil(10.1/3)
        );
        LinkedHashMap<String, Dog> map = new LinkedHashMap<>();
        for (int i = 0; i < 20; i++) {
            Dog dog = new Dog("大黄" + i, 1, i);
            map.put(dog.getName(), dog);
            map.put(dog.getName(), dog);
        }


        Iterator<Map.Entry<String, Dog>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Dog> next = entryIterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }

    }

}
