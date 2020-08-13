package leetCode.tags.data_structure;

/**
 * 206. 反转链表
 * 92. 反转链表 II
 */
public class ReverseList {

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        System.out.println(new ReverseList().reverseList(node));
//        System.out.println(new ReverseList().reverseList2(node));
        ListNode nod = new ReverseList().reverseBetween(node, 2, 4);
    }

    /**
     * 反转一个单链表。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode reverseList(ListNode head) {
        ListNode curNode = head, preNode = null, tempNode;
        while (curNode != null) {
            tempNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = tempNode;
        }
        return preNode;
    }

    /**
     * 92. 反转链表 II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * <p>
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            ListNode lastNode = new ListNode(-1);
            return reverseN(head, n, lastNode);
        } else {
            head.next = reverseBetween(head.next, m - 1, n - 1);
        }
        return head;
    }

    ListNode reverseN(ListNode head, int n, ListNode lastNode) {
        if (n == 1) {
            lastNode.next = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1, lastNode);
        head.next.next = head;
        head.next = lastNode.next;
        return newHead;
    }

    /**
     * 使用递归算法
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head.next == null) return head;
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
