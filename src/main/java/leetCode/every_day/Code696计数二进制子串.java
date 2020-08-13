package leetCode.every_day;

/**
 * 696. 计数二进制子串
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * <p>
 * 重复出现的子串要计算它们出现的次数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * <p>
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * <p>
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * <p>
 * 输入: "10101"
 * 1100
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 */
public class Code696计数二进制子串 {


    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("100111001"));
    }

    public static int countBinarySubstrings(String s) {
        if (s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != s.charAt(i - 1)) {
                dp[i] = dp[i - 1] + 1;
            } else {
                int cNum = 1, index = i - 1;
                while (index >= 1 && s.charAt(index) == c && dp[index] > dp[index - 1]) {
                    cNum++;
                    index--;
                }
                index = i - cNum * 2 + 1;
                if (cNum > 1 && index >= 0 && s.charAt(index) != c) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[s.length() - 1];
    }

}
