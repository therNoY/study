package leetCode.tags.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 * 此类问题都可以是层次遍历求解
 */
public class LevelOrderBottom {

    public static void main(String[] args) {
//        System.out.println(new LevelOrderBottom().levelOrderBottom(
//                TreeNode.getTreeByArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
//        ));
        System.out.println(new LevelOrderBottom().rightSideView(
                TreeNode.getTreeByArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        ));
    }

    /**
     * 107. 二叉树的层次遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其自底向上的层次遍历为：
     * <p>
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     * 通过次数70,670提交次数106,7
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        int size;
        while ((size = deque.size()) > 0) {
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                list.add(node.val);
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }
            res.addFirst(list);
        }
        return res;
    }

    /**
     * 103. 二叉树的锯齿形层次遍历
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回锯齿形层次遍历如下：
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        int size;
        boolean left = true;
        while ((size = deque.size()) > 0) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (left) list.addLast(node.val);
                else list.addFirst(node.val);
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }
            left = !left;
            res.addLast(list);
        }
        return res;
    }

    /**
     * 199. 二叉树的右视图
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     * <p>
     * 1            <---
     * /   \
     * 2     3         <---
     * \     \
     * 5     4       <---
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        int size;
        while ((size = deque.size()) > 0) {
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (i == size - 1) res.add(node.val);
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }

        }
        return res;
    }

    public Node connect(Node root) {
        if (root == null) return null;
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);

        int size;
        while ((size = deque.size()) > 0) {
            Node preNode = null;
            for (int i = 0; i < size; i++) {
                Node node = deque.pollFirst();
                if (node.left!=null) deque.addLast(node.left);
                if (node.right!=null) deque.addLast(node.right);
                if (preNode!= null) {
                    preNode.next = node;
                }
                preNode = node;
            }
        }
        return root;
    }

    class Node extends TreeNode{
        public int val;
        public Node left;
        public Node right;
        public Node next;


        Node(int x) {
            super(x);
        }
    }
}
