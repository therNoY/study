package java_base.list;

import java.util.ArrayList;
import java.util.Vector;

public class MyArrayList {

    public static void main(String[] args) {
        ArrayList list = new ArrayList(4);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("0");
        list.remove(1);
        Vector vector = new Vector();
        vector.add("1");
    }

}
