package leetCode.tags.tree;

/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 */
public class Code114二叉树展开为链表 {

    public static void main(String[] args) {
        flatten(TreeNode.getTreeByArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    public static void flatten(TreeNode root) {
        root.print();
        helper(root);
        root.print();
    }

    /**
     * 将节按链表返回 并返回最后的节点
     * @param node
     * @return
     */
    public static TreeNode helper(TreeNode node) {
        if (node == null) return null;
        if (node.left == null && node.right == null) return node;
        // 先记录右节点
        TreeNode rightNode = node.right;
        TreeNode rightLastNode = helper(node.right);
        TreeNode leftLastNode = helper(node.left);
        // 如果左节点不为空就接上
        if (leftLastNode != null) {
            leftLastNode.right = rightNode;
            node.right = node.left;
        }else {
            node.right = rightNode;
        }
        // 左节点改成Null
        node.left = null;
        return rightLastNode == null ? leftLastNode : rightLastNode;
    }

}
