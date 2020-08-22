package java_base.jdk8;

import java.util.function.BiFunction;

public class BiFunctionTest {
    public void main(String[] args) {
        BiFunction<String, String, String> function = (p1,p2) -> {
            System.out.println(p1 + " " + p2);
            return p1 + p2;
        };

        execFunction(function);

        execFunction(this::myFunction);
    }

    public String myFunction(String s, String s2) {
        System.out.println(s + " " + s2);
        return s + s2;
    }

    public static void execFunction(BiFunction<String, String, String> function) {
        function.apply("1", "2");
    }
}
