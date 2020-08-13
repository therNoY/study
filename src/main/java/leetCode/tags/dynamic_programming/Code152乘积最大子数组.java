package leetCode.tags.dynamic_programming;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class Code152乘积最大子数组 {

    public static void main(String[] args) {

    }

    /**
     * 穷举动态规划
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    dp[j] = nums[i];
                }else {
                    dp[j] = dp[j-1] * nums[j];
                }
                max = Math.max(max, dp[j]);
            }
        }

        return max;
    }

}
