package leetCode.tags.dynamic_programming;

import java.util.Arrays;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * ['1','0','1','0','0'],
 * ['1','0','1','1','1'],
 * ['1','1','1','1','1'],
 * ['1','0','0','1','0']
 * ]
 * <p>
 * [['0','0','0','0','1','1','1','0','1'],['0','0','1','1','1','1','1','0','1'],
 * ['0','0','0','1','1','1','1','1','0']]
 */
public class MaximalRectangle {

    public static void main(String[] args) {
//        System.out.println(new MaximalRectangle().maximalRectangle(new char[][]{
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '1'}
//
//        }));

        System.out.println(new MaximalRectangle().maximalRectangle(new char[][]{
                {'0', '0', '0', '0', '1', '1', '1', '0', '1'},
                {'0', '0', '1', '1', '1', '1', '1', '0', '1'},
                {'0', '0', '0', '1', '1', '1', '1', '1', '0'}

        }));
    }

    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        if (col == 0) return 0;

        /**
         * dp[i][j][0] 表示在以ij为右下端点的矩阵的宽度
         * dp[i][j][1] 表示在以ij为右下端点的矩阵的高度
         */
        int[][][] dp = new int[row][col][2];
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j][0] = matrix[i][j] - 48;
                    dp[i][j][1] = matrix[i][j] - 48;
                } else if (i == 0) {
                    dp[i][j][0] = dp[i][j - 1][0] + 1;
                    dp[i][j][1] = 1;
                } else if (j == 0) {
                    dp[i][j][0] = 1;
                    dp[i][j][1] = dp[i - 1][j][1] + 1;
                } else {
                    // 这里可以形成三个矩阵
                    int h = 0, w = 0;
                    for (int k = i; k >= 0; k--) {
                        if (matrix[k][j] == '1') {
                            h++;
                        } else {
                            break;
                        }
                    }

                    for (int k = j; k >= 0; k--) {
                        if (matrix[i][k] == '1') {
                            w++;
                        } else {
                            break;
                        }
                    }

                    if (w > h) {
                        dp[i][j][1] = 1;
                        dp[i][j][0] = w;
                    } else {
                        dp[i][j][1] = h;
                        dp[i][j][0] = 1;
                    }
                    h = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j][1]) + 1;
                    w = Math.min(dp[i - 1][j - 1][0], dp[i][j - 1][0]) + 1;
                    if (h * w > dp[i][j][1] * dp[i][j][0]) {
                        dp[i][j][1] = h;
                        dp[i][j][0] = w;
                    }
                }
                max = Math.max(max, dp[i][j][0] * dp[i][j][1]);
            }
        }

        return max;
    }

    public int maximalRectangle2(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[] left = new int[n]; // initialize left as the leftmost boundary possible
        int[] right = new int[n];
        int[] height = new int[n];

        Arrays.fill(right, n); // initialize right as the rightmost boundary possible

        int maxarea = 0;
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            // update height
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            // update left
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
                else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            // update right
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            // update area
            for (int j = 0; j < n; j++) {
                maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxarea;
    }
}
