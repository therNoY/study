package leetCode.tags.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 100. 相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 示例 1:
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 * 示例 2:
 *
 * 输入:      1          1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * 输出: false
 * 示例 3:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * 输出: false
 */
public class IsSameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            if (p == null && q == null) return true;
            return false;
        }
        Deque<TreeNode> deque1 = new LinkedList<>();
        Deque<TreeNode> deque2 = new LinkedList<>();

        deque1.addLast(p);
        deque2.addLast(q);
        while ((deque1.size() == deque2.size()) && deque1.size() != 0 ) {
            TreeNode treeNode1 =  deque1.poll();
            TreeNode treeNode2 =  deque2.poll();
            if (treeNode1.val != treeNode2.val) return false;

            if (treeNode1.left != null || treeNode2.left != null) {
                if (treeNode1.left == null || treeNode2.left == null) {
                    return false;
                }else if (treeNode1.left.val == treeNode2.left.val) {
                    deque1.addLast(treeNode1.left);
                    deque2.addLast(treeNode2.left);
                }else {
                    return false;
                }
            }
            if (treeNode1.right != null || treeNode2.right != null) {
                if (treeNode1.right == null || treeNode2.right == null) {
                    return false;
                }else if (treeNode1.right.val == treeNode2.right.val) {
                    deque1.addLast(treeNode1.right);
                    deque2.addLast(treeNode2.right);
                }else {
                    return false;
                }
            }
        }
        return deque1.size() == deque2.size();
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
