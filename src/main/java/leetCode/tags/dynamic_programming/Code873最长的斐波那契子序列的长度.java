package leetCode.tags.dynamic_programming;

/**
 * 873. 最长的斐波那契子序列的长度
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * <p>
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 * （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 * 示例 1：
 * 输入: [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释:
 * 最长的斐波那契式子序列为：[1,2,3,5,8] 。
 * 示例 2：
 * <p>
 * 输入: [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释:
 * 最长的斐波那契式子序列有：
 * [1,11,12]，[3,11,14] 以及 [7,11,18] 。
 */
public class Code873最长的斐波那契子序列的长度 {

    public static void main(String[] args) {
        System.out.println(new Code873最长的斐波那契子序列的长度().lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    private int lenLongestFibSubseq(int[] ints) {
        return -1;
    }

    /**
     * 这个方法是求子串的
     * @param A
     * @return
     */
    public int lenLongestFibSubseq2(int[] A) {
        if (A.length < 2) return A.length;
        boolean[][] dp = new boolean[A.length][A.length];
        int maxLength = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = i; j < A.length; j++) {
                if (j - i < 2) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = (dp[i + 1][j] && A[i] + A[i + 1] == A[i + 2])
                            || (dp[i][j - 1] && A[j - 1] + A[j - 2] == A[j]);
                }
                maxLength = dp[i][j] ? Math.max(maxLength, j - i + 1) : maxLength;
            }
        }
        return maxLength;


    }
}
