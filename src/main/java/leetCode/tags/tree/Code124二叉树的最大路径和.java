package leetCode.tags.tree;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * 输出: 6
 * 示例 2:
 * <p>
 * 输入: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 输出: 42
 */
public class Code124二叉树的最大路径和 {

    public static void main(String[] args) {

    }

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        return Math.max(getMaxDept(root), max);
    }

    private int getMaxDept(TreeNode node) {
        if (node == null) return 0;
        int left  = Math.max(getMaxDept(node.left), 0);
        int right  = Math.max(getMaxDept(node.right), 0);
        max = Math.max(left + right + node.val, max);
        return Math.max(left, right) + node.val;
    }

}
