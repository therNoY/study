package data_structure.tree;

import sun.reflect.generics.tree.Tree;

import java.util.Random;

/**
 * BST 二叉查找树
 */
public class BinarySearchTree<E> extends AbstractTree<E> {

    public static void main(String[] args) {
        AbstractTree<Integer> tree = new BinarySearchTree();

        for (int i = 0; i < 10; i++) {
            tree.insert(new Random(i).nextInt(9));
        }

    }


    @Override
    Node getInsertNode(Node node, E e) {
        if (node == null) {
            node = new Node(e);
            return node;
        }
        if (compareNode(node, e) > 0) {
            node.left = getInsertNode(node.left, e);
        } else {
            node.right = getInsertNode(node.right, e);
        }
        return node;
    }
}
