package leetCode.tags.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 */
public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
//        triangle.add(Arrays.asList(1));
//        triangle.add(Arrays.asList(2, 3));
        System.out.println(new Triangle().minimumTotal(triangle));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        // dp[i][j] 表示从第0行第0个 到第i行第j列的路径最大值
        int[] lastDp = new int[row];
        int[] dp = new int[row];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < rows.size(); j++) {
                if (i == 0) {
                    lastDp[j] = rows.get(0);
                } else if (j == 0) {
                    dp[j] = lastDp[j] + rows.get(j);
                } else if (j == rows.size() - 1) {
                    dp[j] = lastDp[j - 1] + rows.get(j);
                } else {
                    dp[j] = Math.min(lastDp[j], lastDp[j - 1]) + rows.get(j);
                }
                if (i == row - 1) min = Math.min(dp[j], min);
                System.arraycopy(dp, 0, lastDp, 0, row);
            }
        }
        return min;
    }


    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);
        int s = triangle.size();
        // dp[i][j] 表示第i行第j列的到定点距离的大小
        int[][] dp = new int[s][s];
        int min = Integer.MAX_VALUE;
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < s; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
                } else if (i == j) {
                    dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
                if (i == s - 1) {
                    min = Math.min(min, dp[i][j]);
                }
            }
        }
        return min;
    }
}
