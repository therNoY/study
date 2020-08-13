package leetCode.hot_code;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class Code2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
//        ListNode t1= l1;
//        t1 = t1.next = new ListNode(4);
//        t1 = t1.next = new ListNode(3);

        ListNode l2 = new ListNode(9);
        ListNode t2 = l2;
        t2 = t2.next = new ListNode(9);
//        t2 = t2.next = new ListNode(4);

        ListNode res = addTwoNumbers(l1, l2);

    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode sumNode = new ListNode(0);
        ListNode t1;
        ListNode t2;
        ListNode res;
        int s = 0;
        int ls = 0;
        for (t1 = l1, t2 = l2, res = sumNode; t1 != null && t2 != null; ls = 0) {
            ls = s;
            int sum = t1.val + t2.val;
            if (sum >= 10) {
                sum = sum - 10;
                s = 1;
            } else
                s = 0;
            res.val = res.val + sum + ls;
            if (res.val >= 10) {
                res.val = res.val - 10;
                s++;
            }

            if (t1.next == null && t2.next == null) {
                if (s != 0) {
                    res.next = new ListNode(s);
                    res = res.next;
                }
                t1 = t2 = null;
                continue;
            } else {
                if (t1.next != null)
                    t1 = t1.next;
                else
                    t1.val = 0;

                if (t2.next != null)
                    t2 = t2.next;
                else
                    t2.val = 0;

                res.next = new ListNode(0);
                res = res.next;
            }
        }
        return sumNode;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;

    }


    static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

