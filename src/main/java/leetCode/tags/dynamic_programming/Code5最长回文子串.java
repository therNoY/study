package leetCode.tags.dynamic_programming;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code5最长回文子串 {

    public static void main(String[] args) {
//        System.out.println(new LongestPalindrome().longestPalindrome("babad"));
//        System.out.println(new LongestPalindrome().longestPalindrome("cbbd"));
//        System.out.println(new LongestPalindrome().longestPalindrome("aaaa"));
//        System.out.println(new LongestPalindrome().longestPalindrome("abcbe"));
//        System.out.println(new LongestPalindrome().longestPalindrome("abacab"));
//        System.out.println(new LongestPalindrome().longestPalindrome("bananas"));
        System.out.println(new Code5最长回文子串().longestPalindrome("bb"));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        if (s.length() == 1) return s;
        int[][] dp = new int[s.length()][s.length()];
        int maxLength = 0, temp, start = 0, end = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = 0; j < s.length(); j++) {
                if (i < j) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (dp[i + 1][j - 1] == 1 || j - i == 1) {
                            if (maxLength < (temp = (j - i + 1))) {
                                maxLength = temp;
                                start = i;
                                end = j;
                            }
                            dp[i][j] = 1;
                        }
                    }
                } else if (i == j) {
                    dp[i][i] = 1;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
