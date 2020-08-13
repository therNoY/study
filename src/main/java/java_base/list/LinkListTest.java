package java_base.list;

import java.util.LinkedList;

/**
 *
 */
public class LinkListTest {

    static LinkedList<String> linkedList = new LinkedList<String>();

    static {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
    }

    public static void main(String[] args) {
        printLink(linkedList);
        tranOfLinked(linkedList);
        printLink(linkedList);
    }

    /**
     * 实现链表翻转
     */
    static void tranOfLinked(LinkedList linkedList) {
        int start = 0;
        int end = linkedList.size() - 1;
        for (int i = 0; i < end/2; i++) {
            if (i < end - i) {
                Object temp = null;
                temp = linkedList.get(i);
                linkedList.set(i, linkedList.get(end - i));
                linkedList.set(end - i, temp);
            }
        }
    }




    static void printLink(LinkedList<String> linkedList) {
        linkedList.forEach(s->{
            System.out.print(s + " ");
        });
        System.out.println();
    }

}
