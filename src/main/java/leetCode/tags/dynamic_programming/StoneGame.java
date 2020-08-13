package leetCode.tags.dynamic_programming;

/**
 * 分石头游戏
 * <p>
 * 你和你的朋友⾯前有⼀排⽯头堆，⽤⼀个数组 piles 表⽰，piles[i] 表⽰第 i 堆⽯⼦有多少个。
 * 你们轮流拿⽯头，⼀次拿⼀堆，但是只能拿⾛最左边或者 最右边的⽯头堆。
 * 所有⽯头被拿完后，谁拥有的⽯头多，谁获胜。
 * ⽯头的堆数可以是任意正整数，⽯头的总数也可以是任意正整数，
 * 这样就能 打破先⼿必胜的局⾯了。
 * ⽐如有三堆⽯头 piles = [1, 100, 3] ，
 * 先⼿不管 拿 1 还是 3，能够决定胜负的 100 都会被后⼿拿⾛，后⼿会获胜。
 * 假设两⼈都很聪明，请你设计⼀个算法，返回先⼿和后⼿的最后得分（⽯头 总数）之差
 */
public class StoneGame {

    public static void main(String[] args) {
        int[] piles = new int[]{3, 9, 1, 2};

        /**
         * dp[i][j][0] 表示i~j的石头堆我先手的最大值
         * dp[i][j][1] 表示i~j的石头堆我后手的最大值
         *
         */
        int[][][] dp = new int[piles.length][piles.length][2];

        // base case

        for (int i = 0; i < piles.length; i++) {
            for (int j = 0; j < piles.length; j++) {
                // 判断先手 后手
                boolean choiceLeft = true;
                if (piles[i] + dp[i + 1][j][1] > piles[j] + dp[i][j - 1][1]) {
                    dp[i][j][0] = piles[i] + dp[i + 1][j][1];
                } else {
                    choiceLeft = false;
                    dp[i][j][0] = piles[j] + dp[i][j - 1][1];
                }
                dp[i][j][1] = choiceLeft ? dp[i + 1][j][0] : piles[j] + dp[i][j - 1][0];
            }
        }


    }

}
