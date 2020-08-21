package leetCode.tags.dynamic_programming;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class Code416分割等和子集 {

    public static void main(String[] args) {
        System.out.println(new Code416分割等和子集().canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(new Code416分割等和子集().canPartition(new int[]{1, 2, 3, 5}));
    }


    public boolean canPartition(int[] nums) {
        // 先求出总质量 如果总质量不是偶数 直接返回false
        int sum = 0, sum2;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) return false;
        sum2 = sum / 2;


        // dp[i][j] 表示前i个物品 装进容量是j的背包里面 能否装满？
        boolean[][] dp = new boolean[nums.length + 1][sum2 + 1];

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= sum2; j++) {
                if (j != 0) {
                    if (j >= nums[i - 1]) dp[i][j] = dp[i - 1][j] || dp[i][j - nums[i - 1]];
                    else dp[i][j] = dp[i - 1][j];
                } else {
                    // 放背包容量是1的时候就是可以算是装满的
                    dp[i][j] = true;
                }

            }
        }

        return dp[nums.length][sum2];
    }
}
