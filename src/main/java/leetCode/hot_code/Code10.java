package leetCode.hot_code;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code10 {


    public static void main(String[] args) {
        System.out.println(isMatch("mississippi", "mis*is*p*."));
        System.out.println(isMatch("abcabc", ".*c"));
//        System.out.println(isMatch("ab", ".*c"));
        System.out.println(isMatch("aa", "a*a"));
//        System.out.println(isMatch("aa", "a"));
//        System.out.println(isMatch("aab", "c*a*b"));
    }

    public static boolean isMatch(String text, String pattern) {
        int ti = 0;
        int pi = 0;

        char[] pChars = pattern.toCharArray();
        char[] tChars = text.toCharArray();
        char t, p;
        char np, fp;
        for (; pi < pChars.length; ) {
            if (ti >= tChars.length) return false;
            p = pChars[pi];
            t = tChars[ti];

            if (pi + 1 < pChars.length && (pChars[pi + 1]) == '*') {
                // 出现了.* 的情况
                if (p == '.') {
                    if (pi + 2 < pChars.length) {
                        np = pChars[pi + 2];
                        for (;ti < tChars.length;) {
                            if (tChars[ti] != np) {
                                ti ++;
                            }else {
                                if (isMatch(text.substring(ti), pattern.substring(pi + 2))) {
                                    return true;
                                }else {
                                    ti ++;
                                }
                            }
                        }
                    }else {
                        return true;
                    }

                    pi = pi + 2;
                    continue;
                }

                while (ti < tChars.length && tChars[ti] == p){
                    ti ++;
                }

                while (pi + 2 < pChars.length && pChars[pi + 2] == p){
                    pi ++;
                }

                pi = pi + 2;
                continue;
            }


            if (p == t || p == '.'){
                pi ++;
                ti ++;
                continue;
            }
            return false;
        }

        if (ti == tChars.length) return true;

        return false;
    }
    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        for (int i = 0; i < p.length(); i++) { // here's the p's length, not s's
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true; // here's y axis should be i+1
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {//如果是任意元素 或者是对于元素匹配
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {//如果前一个元素不匹配 且不为任意元素
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                            /*
                            dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
                            or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                            or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                             */

                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }



}
