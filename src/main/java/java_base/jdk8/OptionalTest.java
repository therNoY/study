package java_base.jdk8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {

        String s = null;

        Map<String, String> map = new HashMap<>();

        // 如果不为空就执行里面的方法 为空返回222
        System.out.println(Optional.ofNullable(s).map((s1 -> {
            System.out.println(s1);
            return s1 + "111";
        })).orElse("222"));

        s = "00";

        // 不为空执行里面的方法
        System.out.println(Optional.ofNullable(s).map((s1 -> {
            System.out.println(s1);
            return s1 + "111";
        })).orElse("222"));

        s = null;

        System.out.println(Optional.ofNullable(s).flatMap((s1 -> {
            System.out.println(s1);
            return Optional.ofNullable(map.get(s1));
        })).orElse("null"));


        s = "111";

        System.out.println(Optional.ofNullable(s).flatMap((s1 -> {
            System.out.println(s1);
            return Optional.ofNullable(map.get(s1));
        })).orElse("null"));

        map.put(s, "aa");

        System.out.println(Optional.ofNullable(s).flatMap((s1 -> {
            System.out.println(s1);
            return Optional.ofNullable(map.get(s1));
        })).orElse("null"));
    }

}
