package leetCode.tags.array;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class JumpGame {

    public static void main(String[] args) {
//        int[] a = {2,3,1,1,4};
//        int[] a = {3,2,1,0,4};
        int[] a = {1};
        boolean res = new JumpGame().canJump(a);
    }

    /**
     * 递归超时
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        if (nums[0] == 0){
            if (nums.length == 1) return true;
            else return false;
        }
        return isCanJump(nums, 0);

    }

    /**
     * 重要是要判断什么时候能够到达
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int maxLength = 0, temp;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxLength) {
                if ((temp = nums[i] + i) > maxLength) maxLength = temp;
                if (maxLength >= nums.length) return true;
            }

        }
        return false;

    }

    public boolean isCanJump(int[] nums, int s) {
        for (int i = 1; i <= nums[s]; i++) {
            if ((s == nums.length - 1) || nums[s + i] + s+i + 1 >= nums.length)
                return true;
            if (isCanJump(nums, s + i))
                return true;
        }
        return false;
    }


    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 示例:
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 说明:
     *
     * 假设你总是可以到达数组的最后一个位置。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
//        return getMinJump(nums, 0);
    }


    public int getMinJump(int[] nums, int c) {
        int minJump = Integer.MIN_VALUE, temp;
        if (c + nums[c] >= nums.length) return 1;
        if (nums[c] == 0) return -1;
        for (int i = 1; i <= nums[c]; i++) {
            if ((temp = getMinJump(nums, c + i)) != -1) {
                if (temp < minJump) minJump = temp;
            }

        }
        return minJump;
    }



}
