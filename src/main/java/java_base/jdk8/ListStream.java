package java_base.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListStream {

    static List<String> stringList = new ArrayList<>();

    static {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        stringList.add("7");
    }

    public static void main(String[] args) {

        List<ParaObj> paraObjs = stringList.stream().map(s -> {
            if (Integer.valueOf(s) % 2 == 0) {
                return new ParaObj(s);
            } else {
                return null;
            }
        }).filter(paraObj -> paraObj != null)
                .collect(Collectors.toList());
    }

}
