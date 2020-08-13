package leetCode.tags.dynamic_programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 0-1背包问题
 */
public class _01bags {

    public static void main(String[] args) {
//        int[] w = new int[]{35, 30, 60, 50, 40, 10, 25};
//        int[] p = new int[]{10, 40, 30, 50, 35, 40, 30};
//        int c = 150;
//
        int[] w = new int[]{2, 2, 6, 5, 4};
        int[] p = new int[]{6, 3, 5, 4, 6};
        int c = 10;

//        int[] w = new int[]{0,2,2,6,5,4};
//        int[] p = new int[]{0,6,3,5,4,6};
//        int c = 10;

        System.out.println(getMaxP(w, p, c));
        System.out.println(getMaxP2(w, p, c));
    }

    /**
     * 自己实现的 根据硬币找零实现
     *
     * @param w
     * @param p
     * @param c
     * @return
     */
    static int getMaxP(int[] w, int[] p, int c) {
        if (c < 1) return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        // dp[i] 表示背包容量是 i的时候能装的最大值
        int[] dp = new int[c];
        dp[0] = 0;
        int maxPrice, tempI, maxJ = 0;
        List<Integer> list, tempList = new ArrayList<>();
        for (int i = 1; i < c; i++) {
            maxPrice = Integer.MIN_VALUE;
            for (int j = 0; j < w.length; j++) {
                if ((tempI = i - w[j]) > 0
                        && ((list = map.get(tempI)) == null || !list.contains(Integer.valueOf(j)))
                        && maxPrice < (tempI = dp[tempI] + p[j])) {
                    maxPrice = tempI;
                    maxJ = j;
                    tempList = list;
                }
            }
            dp[i] = maxPrice > 0 ? maxPrice : 0;
            if (dp[i] > 0) {
                List addList = new ArrayList<>();
                addList.add(Integer.valueOf(maxJ));
                if (tempList != null) addList.addAll(tempList);
                map.put(Integer.valueOf(i), addList);
            }
        }

        return dp[c - 1];

    }


    /**
     * 这个也可以压缩成两个一维数组
     *
     * @param w
     * @param p
     * @param c
     * @return
     */
    static int getMaxP2(int[] w, int[] p, int c) {
        /**
         * 定义dp[i][j] 是前i个物品 j个重量的时候最大价值
         */
        int[][] dp = new int[w.length + 1][c + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= c; j++) {
                // 如果装得下
                if (j >= w[i - 1]) {
                    // 选择装或者不装 不装和之前一样 装的话选择
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + p[i - 1]);
                } else {
                    // 只能不装
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[w.length][c];
    }

    /**
     * 01背包
     *
     * @param w
     * @param p
     * @param c
     * @return
     */
    public int _01Bages(int[] w, int[] p, int c) {
        int size = w.length;
        /**
         * 前i个物品装进背包容量是j的最大值
         */
        int[][] dp = new int[size + 1][c + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= c; j++) {
                if (j - w[i] > 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + p[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[size][c];
    }


}
