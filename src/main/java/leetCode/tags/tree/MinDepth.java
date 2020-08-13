package leetCode.tags.tree;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 */
public class MinDepth {

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Integer current = 1;
        return preOrder(root, current);
    }

    private int preOrder(TreeNode root, Integer current) {
        if (root == null) return current;
        if (root.left == null && root.right == null) {
            return current + 1;
        }
        return Math.min(preOrder(root.left, current + 1), preOrder(root.right, current + 1));
    }
}
