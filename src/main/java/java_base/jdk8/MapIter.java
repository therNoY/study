package java_base.jdk8;

import java.util.HashMap;
import java.util.Map;

public class MapIter {

    static Map<String, String> transformedBeanNameCache = new HashMap<>();

    public static void main(String[] args) {
        transformedBeanNameCache.put("1", "2");
        transformedBeanNameCache.put("2", "4");
        transformedBeanNameCache.put("3", "5");


        /**
         * 根据key比较 符合提交的value
         */
        String a = transformedBeanNameCache.computeIfAbsent("6", beanName -> {
            System.out.println(beanName);
            return beanName;
        });

        System.out.println(a);

    }

}
