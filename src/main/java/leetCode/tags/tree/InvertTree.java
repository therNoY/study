package leetCode.tags.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InvertTree {

    public static void main(String[] args) {
        System.out.println(new InvertTree().invertTree(TreeNode.getTreeByArray(new Integer[]{4, 2, 7, 1, 3, 6, 9})));
    }

    private TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode tempNode;
        tempNode = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tempNode);
        return root;
    }

    public TreeNode invertTreeByDeque(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        TreeNode tempNode;
        while (deque.size() > 0) {
            TreeNode treeNode = deque.pollLast();
            tempNode = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = tempNode;

            if (treeNode.left != null) deque.addLast(treeNode.left);
            if (treeNode.right != null) deque.addLast(treeNode.right);
        }
        return root;
    }
}
