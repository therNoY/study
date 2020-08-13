package leetCode.tags.dynamic_programming;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）
 * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class PerfectSquares {

    public static void main(String[] args) {

//        for (int i = 1; i < 20; i++) {
//            System.out.println(new PerfectSquares().numSquares(i));
//        }
        System.out.println(new PerfectSquares().numSquares(99));

    }

    public int numSquares(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        int min, t;
        double sqrt = Math.sqrt(n);
        if (sqrt - Math.floor(sqrt) == 0) return 1;
        for (int i = 2; i <= n; i++) {
            sqrt = Math.sqrt(i);
            min = Integer.MAX_VALUE;
            if (sqrt - (t = (int) Math.floor(sqrt)) == 0) {
                dp[i] = 1;
            } else {
                for (int j = t; j > 0; j--) {
                    min = Math.min(dp[i - j * j] + 1, min);
                }
                dp[i] = min;
            }
        }

        return dp[n];
    }


}
