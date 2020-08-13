package leetCode.tags.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
public class HasPathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSum(root.left, sum, root.val) || hasPathSum(root.right, sum, root.val);
    }

    public boolean hasPathSum(TreeNode node, int sum, int currentSum) {
        if (node == null) return false;
        currentSum = currentSum + node.val;
        if (node.right == null && node.left == null) {
            if (currentSum == sum) return true;
        }
        return hasPathSum(node.left, sum, currentSum) || hasPathSum(node.right, sum, currentSum);
    }


    public static void main(String[] args) {
        System.out.println(new HasPathSum().pathSum(
                TreeNode.getTreeByArray(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1),
                22));
    }

    /**
     * 113. 路径总和 II
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * 返回:
     * <p>
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        getPathSum(root, sum, new LinkedList<>(), lists);
        return lists;
    }

    public void getPathSum(TreeNode node, int sum, LinkedList<Integer> history, List<List<Integer>> list) {
        if (node == null) return;
        history.addLast(node.val);
        if (node.right == null && node.left == null) {
            if (node.val == sum) list.add((List) history.clone());
        }
        getPathSum(node.left, sum - node.val, history, list);
        getPathSum(node.right, sum - node.val, history, list);
        history.removeLast();
    }

}
