package leetCode.tags.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreePaths {

    public static void main(String[] args) {
        System.out.println(new BinaryTreePaths().binaryTreePaths2(
                TreeNode.getTreeByArray(1, 2, 3, null, 5)
        ));
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> strings = new ArrayList<>();
        preOrder(root, strings, new LinkedList<>());
        return strings;
    }

    private void preOrder(TreeNode root, List<String> res, LinkedList<Integer> history) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(newPath(history, root.val));
            return;
        }
        history.addLast(root.val);
        preOrder(root.left, res, history);
        preOrder(root.right, res, history);
        history.pollLast();
    }


    public List<String> binaryTreePathsByStack(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        LinkedList<Integer> history = new LinkedList<>();
        List<String> strings = new ArrayList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode node = deque.pollLast();
            if (node != null) {
                if (node.right != null) deque.add(node.right);
                if (node.left != null) deque.add(node.left);
                deque.addLast(node);
                deque.add(null);
            }else {
                TreeNode cNode = deque.pollLast();
                if (cNode.right == null && cNode.left == null) {
                    strings.add(newPath(history, cNode.val));
                }
                history.pollLast();
            }
        }
        return strings;
    }

    private String newPath(LinkedList<Integer> history, int val) {
        StringBuffer stringBuffer = new StringBuffer();
        history.forEach(h->{
            stringBuffer.append(h).append("->");
        });
        stringBuffer.append(val);
        return stringBuffer.toString();
    }
}
