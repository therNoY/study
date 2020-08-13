package java_base.jdk8;

import java.util.function.Function;

public class FunctionTest {
    public static void main(String[] args) {
        Function<String, String> function = p -> {
            System.out.println(p);
            return p + p;
        };

        execFunction(function);

        execFunction(FunctionTest::myFunction);
    }

    public static String myFunction(String s) {
        System.out.println(s);
        return s + s + s;
    }

    public static void execFunction(Function<String, String> function) {
        function.apply("1");
    }
}
