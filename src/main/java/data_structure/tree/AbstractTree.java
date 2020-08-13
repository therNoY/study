package data_structure.tree;

import java.util.Comparator;

/**
 * 简单的
 *
 * @param <T>
 */
public abstract class AbstractTree<T> implements Tree<T, T> {

    Node root = null;

    int size = 0;

    Comparator<T> comparator = null;

    @Override
    public void insert(T t) {
        if (size == 0) {
            root = new Node(t);
        } else {
            getInsertNode(root, t);
        }
        size ++;
    }

    abstract Node getInsertNode(Node node, T t);

    public int compareNode(Node node, Node node2) {
        return compareNode(node.value, node2.value);
    }

    public int compareNode(Node node, T t2) {
        return compareNode(node.value, t2);
    }

    public int compareNode(T t1, T t2) {
        if (comparator != null) {
            return comparator.compare(t1, t2);
        } else if (t1 instanceof Comparable) {
            Comparable c1 = (Comparable) t1;
            Comparable c2 = (Comparable) t2;
            return c1.compareTo(c2);
        }
        throw new RuntimeException("不能比较");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(T t) {

    }

    @Override
    public void show() {
        printNode(root);
    }

    public void printNode(Node node){
        System.out.print(root.value);
        printNode(root.left);
        printNode(root.right);
    }

    @Override
    public T search(T t) {
        return null;
    }

    class Node {
        T value;
        Node left = null;
        Node right = null;

        public Node(T value) {
            this.value = value;
        }
    }


    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
}
