package leetCode.tags.dynamic_programming;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class EditDistance {

    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
//        String word1 = "horse", word2 = "ros";
        System.out.println(new EditDistance().minDistance(word1, word2));

    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length() ; i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0) {
                    dp[0][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][0] = i;
                    continue;
                }
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
            }
        }

        return dp[word1.length()][word2.length()];
    }

    private int min(int i, int i1, int i2) {
        int min = i;
        if (i1 < min) min = i1;
        if (i2 < min) min = i2;
        return min;
    }


}
