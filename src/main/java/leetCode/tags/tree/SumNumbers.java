package leetCode.tags.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 129. 求根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * 计算从根到叶子节点生成的所有数字之和。
 * 说明: 叶子节点是指没有子节点的节点。
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * 输入: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 * / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class SumNumbers {

    public static void main(String[] args) {
        System.out.println(new SumNumbers().sumNumbers(TreeNode.getTreeByArray(1, 2, 3)));
    }


    public int sumNumbers(TreeNode root) {
        int sum = 0;
        List<Integer> list = new LinkedList<>();
        sumNumbers(root, list, new LinkedList<>());
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    public void sumNumbers(TreeNode root, List<Integer> res, LinkedList<Integer> path) {
        if (root == null) return;
        path.addLast(root.val);
        if (root.left == null && root.right == null) {
            res.add(getValueFromPath(path));
        }
        if (root.left != null) sumNumbers(root.left, res, path);
        if (root.right != null) sumNumbers(root.right, res, path);
        path.removeLast();
    }

    private Integer getValueFromPath(LinkedList<Integer> path) {
        int res = 0;
        int size = path.size();
        for (int i = size - 1; i >= 0; i--) {
            res = (int) (res + path.get(size - i - 1) * Math.pow(10, i));
        }
        return res;
    }
}
