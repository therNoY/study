package design_patterns.active_type.strategy.JavaImp;

import java.util.Comparator;

public class MyComparator implements Comparator<String> {


    @Override
    public int compare(String o1, String o2) {

        return o1.length() < o2.length() ? 1 : -1;

    }
}
