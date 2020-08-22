package leetCode.tags.dynamic_programming;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
 */
public class Code53最大子序和 {

    public static void main(String[] args) {
        System.out.println(new Code53最大子序和().maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(new Code53最大子序和().maxSubArray3(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray(int[] nums) {

        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;

    }

    /**
     * 动态规划 穷举
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        if (nums.length == 0) return 0;
        /**
         * dp[i][j] 长度为i-j的数组和
         */
        int[][] dp = new int[nums.length][nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                } else {
                    dp[i][j] = dp[i][j - 1] + nums[j];
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 使用滑动窗口 只有左边的和有益的时候才计算
     */
    public int maxSubArray3(int[] nums) {
        if (nums.length == 0) return 0;
        int right = 0, sum = 0, max = Integer.MIN_VALUE;
        while (right < nums.length) {
            sum += nums[right++];
            max = Math.max(max, sum);
            if (sum <= 0) sum = 0;
        }
        return max;
    }
}
