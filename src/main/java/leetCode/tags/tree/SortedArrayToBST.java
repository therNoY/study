package leetCode.tags.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合->二叉搜索树
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(-10), head = listNode;
        listNode.next = new ListNode(-3);
        listNode = listNode.next;
        listNode.next = new ListNode(0);
        listNode = listNode.next;
        listNode.next = new ListNode(5);
        listNode = listNode.next;
        listNode.next = new ListNode(9);
        TreeNode node = new SortedArrayToBST().sortedListToBST(head);
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * 示例:
     * 给定有序数组: [-10,-3,0,5,9],
     * <p>
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     */
    // 分割左右对称节点
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, start, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, end);
        return node;
    }


    /**
     * 109. 有序链表转换二叉搜索树
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * 示例:
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     */
    // 1. 使用类似上题的方法 不过找中间节点使用快慢指针
    // 2. 先将链表转成数组
    public TreeNode sortedListToBST(ListNode head) {
        ListNode mid = findMid(head);
        if (mid == null) return null;
        TreeNode node = new TreeNode(mid.val);
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }

    public ListNode findMid(ListNode node) {
        if (node == null) return null;
        ListNode slow = node;
        ListNode fast = node;
        ListNode lastSlow = null;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            lastSlow = slow;
            slow = slow.next;
        }

        if (lastSlow != null) lastSlow.next = null;
        else slow.next = null;
        return slow;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
