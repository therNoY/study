package leetCode.tags.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * 322.	零钱兑换
 * 518. 零钱兑换 II
 */
public class Codes零钱兑换问题 {

    public static void main(String[] args) {
//        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(new Codes零钱兑换问题().change(5, new int[]{1, 2, 5}));
//        System.out.println(coinChange_2(new int[]{2}, 3));
    }

    /**
     * * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * * <p>
     * *  
     * * <p>
     * * 示例 1:
     * * <p>
     * * 输入: coins = [1, 2, 5], amount = 11
     * * 输出: 3
     * * 解释: 11 = 5 + 5 + 1
     * * 示例 2:
     * * <p>
     * * 输入: coins = [2], amount = 3
     * * 输出: -1
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dpTable = new int[amount + 1];
        int temp;
        dpTable[0] = 0;
        for (int i = 1; i < dpTable.length; i++) {
            int min = Integer.MAX_VALUE, tempI;
            for (int j = 0; j < coins.length; j++) {
                if (i == coins[j]) {
                    min = 1;
                    break;
                } else if ((tempI = i - coins[j]) >= 0) {
                    if ((temp = dpTable[tempI]) != Integer.MAX_VALUE) {
                        min = Math.min(min, temp + 1);
                    }
                }
            }
            dpTable[i] = min;
        }
        return dpTable[amount] != Integer.MAX_VALUE ? dpTable[amount] : -1;
    }

    /**
     * 思路：构建递归树 使用备忘录来实现这个问题
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange_2(int[] coins, int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        return findMinChange(map, coins, amount);
    }


    public static int findMinChange(Map<Integer, Integer> map, int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        } else if (amount < 0) {
            return -1;
        } else {
            Integer temp;
            if ((temp = map.get(amount)) != null) return temp;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                if ((temp = findMinChange(map, coins, amount - coins[i])) != -1 && min > temp) min = temp;
            }
            map.put(amount, (temp = min == Integer.MAX_VALUE ? -1 : min + 1));
            return temp;
        }
    }

    /**
     * 518. 零钱兑换 II
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * 示例 1:
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * 示例 2:
     * 输入: amount = 3, coins = [2]
     * 输出: 0
     * 解释: 只用面额2的硬币不能凑成总金额3。
     * 示例 3:
     * 输入: amount = 10, coins = [10]
     * 输出: 1
     * 注意:
     * 你可以假设：
     * 0 <= amount (总金额) <= 5000
     * 1 <= coin (硬币面额) <= 5000
     * 硬币种类不超过 500 种
     * 结果符合 32 位符号整数
     */
    public int change(int amount, int[] coins) {
        // dp[i][j] 表示存放前i个硬币总金额是j的时候 最多的容量
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i <= coins.length; i++)
            dp[i][0] = 1;
        int temp;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if ((temp = j - coins[i - 1]) >= 0) {
                    // 可以存的下 旧村
                    dp[i][j] = dp[i - 1][j] +  dp[i][temp];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }
        return dp[coins.length][amount];
    }

}
