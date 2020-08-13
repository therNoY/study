package design_patterns.active_type.strategy.JavaImp;

import java.util.Arrays;

public class MainTest {

    public static void main(String[] args) {

        String[] s = {"1","11","111","1111"};
        Arrays.sort(s, new MyComparator());

        Arrays.asList(s).forEach(ss-> System.out.println(ss));

    }
}
