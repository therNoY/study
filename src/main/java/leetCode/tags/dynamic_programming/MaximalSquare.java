package leetCode.tags.dynamic_programming;

/**
 * 221. 最大正方形
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 */
public class MaximalSquare {

    public static void main(String[] args) {
//        System.out.println(new MaximalSquare().maximalSquare(new char[][]{
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'},
//                {'1', '0', '0', '1', '0'}
//        }));
//
//        System.out.println(new MaximalSquare().maximalSquare(new char[][]{
//                {'1', '1'},
//                {'1', '1'}
//        }));

        System.out.println(new MaximalSquare().maximalSquare(new char[][]{
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'0', '1', '1', '1'}
        }));
    }

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        if (col == 0) return 0;

        /**
         * dp[i][j] 表示以dp[i][j]为终点的正方向 是否是正方行 并保存其边长
         */
        int[][] dp = new int[row][col];
        int maxArea = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - 48;
                    maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
                } else {
                    if (dp[i - 1][j - 1] != 0 && matrix[i][j] == '1') {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j] ,dp[i-1][j-1]),dp[i][j-1]) + 1;
                    } else {
                        dp[i][j] = matrix[i][j] - 48;
                    }
                    maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
                }
            }
        }

        return maxArea;
    }
}
