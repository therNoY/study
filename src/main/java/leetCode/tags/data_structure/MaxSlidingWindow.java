package leetCode.tags.data_structure;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 你能在线性时间复杂度内解决此题吗？
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class MaxSlidingWindow {

    class MonotonicQueue {
        Deque<Integer> deque = new LinkedList<>();
        public void push(Integer integer){
            while (!deque.isEmpty() && deque.getLast() < integer){
                deque.removeLast();
            }
            deque.addLast(integer);
        }

        public int max(){
            if (!deque.isEmpty()){
                return deque.getFirst();
            }

            return -1;
        }

        public void pop(int num){
            if (deque.getFirst() == num){
                deque.removeFirst();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaxSlidingWindow().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
        System.out.println(new MaxSlidingWindow().maxSlidingWindow(new int[]{1,-1}, 1));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        MonotonicQueue queue = new MonotonicQueue();
        for (int i = 0; i < nums.length; i++) {
            if (i<k-1){
                queue.push(nums[i]);
            }else {
                queue.push(nums[i]);
                res[i-k+1] = queue.max();
                queue.pop(nums[i-k+1]);
            }
        }
        return res;
    }

}
