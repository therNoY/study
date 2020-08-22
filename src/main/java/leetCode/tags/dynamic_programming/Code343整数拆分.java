package leetCode.tags.dynamic_programming;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * <p>
 * 通过次数36,578提交次数64,035
 */
public class Code343整数拆分 {

    public static void main(String[] args) {
        System.out.println(new Code343整数拆分().integerBreak(20));
    }

    /**
     * 这个可以用动态规划来搞
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        // dp[i] 表示乘积最大化的数
        int[] dp = new int[n + 1];
        // 初始化
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // 最大数是所有可能的求和最大乘机
                dp[i] = Math.max(Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]), dp[i]);
            }
        }
        return dp[n];
    }

}
