package leetCode.tags.tree;

import data_structure.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 * 二叉搜索树可以通过中序便利获取有序元素
 */
public class BSTIterator {

    Deque<TreeNode> deque = new LinkedList<>();

    /**
     * 173. 二叉搜索树迭代器
     * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
     * 调用 next() 将返回二叉搜索树中的下一个最小的数。
     * 示例：
     * BSTIterator iterator = new BSTIterator(root);
     * iterator.next();    // 返回 3
     * iterator.next();    // 返回 7
     * iterator.hasNext(); // 返回 true
     * iterator.next();    // 返回 9
     * iterator.hasNext(); // 返回 true
     * iterator.next();    // 返回 15
     * iterator.hasNext(); // 返回 true
     * iterator.next();    // 返回 20
     * iterator.hasNext(); // 返回 false
     * 提示：
     * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
     * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
     */
    public BSTIterator(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (stack.size() > 0) {
            TreeNode treeNode = stack.pollLast();
            if (treeNode == null) {
                deque.add(stack.pollLast());
            } else {
                if (treeNode.right != null) stack.addLast(treeNode.right);
                stack.addLast(treeNode);
                stack.add(null);
                if (treeNode.left != null) stack.addLast(treeNode.left);
            }
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        return deque.pollFirst().val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return deque.size() > 0;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * <p>
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [3,1,4,null,2], k = 1
     * 3
     */
    public int kthSmallest(TreeNode root, int k) {
        int i = 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        TreeNode currentNode = root;
        while (currentNode != null || deque.size() > 0) {
            while (currentNode != null) {
                deque.addLast(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = deque.pollLast();
            if (++i == k) return currentNode.val;
            currentNode = currentNode.right;
        }
        return -1;
    }

    /**
     * 98.给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * 示例 2:
     * 输入:
     * 5
     * / \
     * 1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    // 使用中序遍历严格递增
    public boolean isValidBST(TreeNode root) {
        Integer num = null;
        if (root == null) return true;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode node = deque.pollLast();
            if (node != null) {
                if (node.right != null) deque.addLast(node.right);
                deque.add(node);
                deque.add(null);
                if (node.left != null) deque.addLast(node.left);
            } else {
                node = deque.pollLast();
                if (num == null || num < node.val) {
                    num = node.val;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new BSTIterator(null).recoverTree(TreeNode.getTreeByArray(3,1,4,null,null,2));
    }

    /**
     * 99. 恢复二叉搜索树
     * 二叉搜索树中的两个节点被错误地交换。
     * <p>
     * 请在不改变其结构的情况下，恢复这棵树。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,3,null,null,2]
     * <p>
     * 1
     * /
     * 3
     * \
     * 2
     * <p>
     * 输出: [3,1,null,null,2]
     * <p>
     * 3
     * /
     * 1
     * \
     * 2
     * 示例 2:
     * <p>
     * 输入: [3,1,4,null,null,2]
     * <p>
     * 3
     * / \
     * 1   4
     * /
     * 2
     * <p>
     * 输出: [2,1,4,null,null,3]
     * <p>
     * 2
     * / \
     * 1   4
     * /
     * 3
     */
    public void recoverTree(TreeNode root) {
        TreeNode err0 = null;
        TreeNode err1 = null;
        TreeNode err2 = null;
        TreeNode pre = null;
        TreeNode curr = null;
        if (root == null) return;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0 && err2 == null) {
            TreeNode node = deque.pollLast();
            if (node != null) {
                if (node.right != null) deque.addLast(node.right);
                deque.addLast(node);
                deque.addLast(null);
                if (node.left != null) deque.addLast(node.left);
            } else {
                node = deque.pollLast();
                pre = curr;
                curr = node;
                if (pre != null && curr.val < pre.val) {
                    if (err1 == null) {
                        err1 = pre;
                        err0 = curr;
                    }
                    else err2 = curr;
                }
            }
        }
        if (err2 == null) err2 = err0;
        int temp = err1.val;
        err1.val = err2.val;
        err2.val = temp;
    }
}