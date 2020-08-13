package leetCode.tags.data_structure;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * 单调栈
 *
 *给你一个数组，返回一个等长的数组，对应索引存储着下一个更大元素，如果没有更大的元素，就存 -1。
 * 不好用语言解释清楚，直接上一个例子：
 *
 * 给你一个数组 [2,1,2,4,3]，你返回数组 [4,2,4,-1,-1]。
 *
 * 解释：第一个 2 后面比 2 大的数是 4; 1 后面比 1 大的数是 2；第二个 2 后面比 2 大的数是 4;
 * 4 后面没有比 4 大的数，填 -1；3 后面没有比 3 大的数，填 -1。
 */
public class NextGreaterNumber {

    public static void main(String[] args) {
        System.out.println(new NextGreaterNumber().nextGreaterNumber(new int[]{2,1,2,4,3}));
    }

    public int[] nextGreaterNumber(int[] nums) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length];
        // 倒着进栈
        for (int i = nums.length - 1; i >= 0; i--) {
            // 比当前值小的都出栈
            while (!deque.isEmpty() && deque.getLast() <= nums[i]) {
                deque.removeLast();
            }
            // 找到第一个必当前值大的
            if (deque.isEmpty()) {
                res[i] = -1;
            }else {
                // 加入结果集
                res[i] = deque.getLast();
            }
            // 当前值进栈
            deque.addLast(nums[i]);
        }
        return res;
    }

}
