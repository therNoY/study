package leetCode.tags.tree;



import java.util.Deque;
import java.util.LinkedList;

public class IsBalanced {

    public static void main(String[] args) {
        System.out.println(new IsBalanced().isBalanced(TreeNode.getTreeByArray(
                1, 2, 2, 3, null, null, 3, 4, null, null, null, null, null, null, 4
        )));
    }

    /**
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     * 示例 1:
     * 给定二叉树 [3,9,20,null,null,15,7]
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回 true 。
     * 示例 2:
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     * 1
     * / \
     * 2   2
     * / \
     * 3   3
     * / \
     * 4   4
     */
    // 自顶向下 这样可以解决 但是经常会重复计算
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) < 2) {
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // 子弟向下 使用一个值记录
    public boolean isBalanced2(TreeNode root) {
        return helper(root) != -1;
    }

    /**
     * 获取数的高度
     *
     * @param node
     * @return
     */
    private int helper(TreeNode node) {
        if (node == null) return 0;
        int left, right;
        if ((left = helper(node.left)) == -1) return -1;
        if ((right = helper(node.right)) == -1) return -1;
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }




}
