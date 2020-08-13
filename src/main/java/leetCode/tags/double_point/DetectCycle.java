package leetCode.tags.double_point;

import java.util.List;

/**
 * 给出一个链表判断是否有环 如果有就返回环的位置
 */
public class DetectCycle {

    public static void main(String[] args) {

    }

    /**
     *
     * 返回环的位置
     * @param node
     * @return
     */
    public int detectCycle(ListNode node) {
        ListNode head = node;
        ListNode slow = node, fast = node;
        boolean isHasCycle = false;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                isHasCycle = true;
                break;
            }
        }

        if (isHasCycle) {
            slow = head;
            int index = 0;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
                index++;
            }
            return index;
        }

        return -1;

    }


    class ListNode<T> {
        T value;
        ListNode next;
    }
}
