package leetCode.tags.dynamic_programming;

/**
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * <p>
 * -1 : 我的数字比较小
 * 1 : 我的数字比较大
 * 0 : 恭喜！你猜对了！
 *  
 * <p>
 * 示例 :
 * <p>
 * 输入: n = 10, pick = 6
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GuessNumberHigherOrLower {

    public static void main(String[] args) {
        System.out.println(new GuessNumberHigherOrLower().getMoneyAmount2(10));
    }

    int guess(int num) {
        return -1;
    }

    /**
     * 我们正在玩一个猜数游戏，游戏规则如下：
     * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
     * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
     * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
     * 示例:
     * n = 10, 我选择了8.
     * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
     * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
     * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
     * 游戏结束。8 就是我选的数字。
     * 你最终要支付 5 + 7 + 9 = 21 块钱。
     * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        // i = y j = x i > j
        for (int l = 1; l < n; l++) {
            for (int s = l; s >= 1; s--) {
                if (l == s) {
                    dp[s][s] = 0;
                } else if (l - s == 1) {
                    dp[s][l] = s;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = s; k <= l; k++) {
                        min = Math.min(Math.max(dp[s][k - 1], dp[k + 1][l]) + k, min);
                    }
                    dp[s][l] = min;
                }
            }
        }

        return dp[1][n];


    }

    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n; i > 0; i--) {
            for(int j = i; j <= n; ++j) {
                if (i == j) {
                    dp[i][i] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k <= j; k++) {
                        min = Math.min(Math.max(dp[i][k - 1], dp[k + 1][j]) + k, min);
                    }
                    dp[i][j] = min;
                }

            }
        }
        return dp[1][n];
    }

}
