package leetCode.tags.tree;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class MaxDepth {

    public static void main(String[] args) {
        System.out.println(new MaxDepth().maxDepth2(
                TreeNode.getTreeByArray(3,9,20,null,null,15,7)));
    }

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Deque<Pair<TreeNode, Integer>> deque = new LinkedList<>();
        deque.addLast(new Pair<>(root, 1));
        int maxDept = -1;
        while (deque.size() > 0) {
            Pair<TreeNode, Integer> pair = deque.pollLast();
            int current = pair.getValue();
            TreeNode node = pair.getKey();
            if (node.right != null) {
                deque.addLast(new Pair<>(node.right, current + 1));
            }
            if (node.left != null) {
                deque.addLast(new Pair<>(node.left, current + 1));
            }
            if (node.right == null && node.left == null) {
                maxDept = Math.max(maxDept, current);
            }
        }

        return maxDept;



    }


}
