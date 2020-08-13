package leetCode.tags.dynamic_programming;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * 说明:
 * <p>
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * <p>
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {

    public static void main(String[] args) {
        System.out.println(new BurstBalloons().maxCoins(new int[]{3, 1, 5, 8}));
    }

    public int maxCoins(int[] nums) {

        int[] newNums = new int[nums.length + 2];
        System.arraycopy(nums, 0, newNums, 1, nums.length);
        newNums[0] = 1;
        newNums[nums.length + 1] = 1;

        /**
         * 这个含义是选择戳破ij之间不包括ij的所有气球获取的最大money
         */
        int[][] dp = new int[newNums.length][newNums.length];
        int temp;
        for (int i = newNums.length - 1; i >= 0; i--) {
            for (int j = i; j < newNums.length; j++) {
                if (j - i == 2) {
                    temp = (j + i) / 2;
                    dp[i][j] = newNums[temp] * newNums[temp - 1] * newNums[temp + 1];
                } else {
                    for (int k = i + 1; k < j; k++) {
                        temp = dp[i][k] + dp[k][j] + newNums[k] * newNums[i] * newNums[j];
                        dp[i][j] = Math.max(dp[i][j], temp);
                    }
                }
            }
        }
        return dp[0][newNums.length - 1];
    }


}
