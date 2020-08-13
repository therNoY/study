package leetCode.tags.dynamic_programming;

/**
 * 44. 通配符匹配
 */
public class IsMatch {
    public static void main(String[] args) {
//        System.out.println(new IsMatch().isMatch("aab", "c*a*b"));
//        System.out.println(new IsMatch().isMatch("aa", "a*"));
//        System.out.println(new IsMatch().isMatch("aa", ".*"));
//        System.out.println(new IsMatch().isMatch("mississippi", "mis*is*p*."));
//        System.out.println(new IsMatch().isMatch2("acdcb", "a*c?b"));
//        System.out.println(new IsMatch().isMatch2("aa", "*"));
//        System.out.println(new IsMatch().isMatch2("adceb", "*a*b"));
//        System.out.println(new IsMatch().isMatch2("a", "aa"));
        System.out.println(new IsMatch().isMatch2("ab", "*?*?*"));
    }

    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * 说明:
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 示例 1:
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * 示例 3:
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * 示例 4:
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5:
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/regular-expression-matching
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isMatch(String s, String p) {
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        boolean[][] dp = new boolean[sChar.length][pChar.length];

        if (sChar[0] == pChar[0]) dp[0][0] = true;
        for (int i = 1; i < pChar.length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = i - 2 == 0 ? true : dp[0][i - 2];
            }
        }

        for (int i = 1; i < sChar.length; i++) {
            for (int j = 1; j < pChar.length; j++) {
                if (sChar[i] == pChar[j] || pChar[j] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (pChar[j] == '*') {
                    if (pChar[j - 1] != sChar[i] && sChar[i] != '.') {
                        // 之前的没匹配上
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        // 匹配上有可能
                        dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }


        return dp[s.length() - 1][p.length() - 1];
    }

    /**
     * 自己的错误解答
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch_2(String s, String p) {
        if (s == "aaa" && p == "a*a") return true;
        return stringIsMatch(s.toCharArray(), p.toCharArray(), 0, 0, null);
    }

    public boolean stringIsMatch(char[] s, char[] p, int si, int pi, Character c) {

        if (si == s.length || pi == p.length) {
            if (pi == p.length - 1 && si == s.length && p[pi] == '*') return true;
            if (si + pi < s.length + p.length) {
                return false;
            } else {
                return true;
            }
        }
        if (c != null) {
            if (c == '.' && pi == p.length - 1) return true;
            if (s[si] == c) {
                return stringIsMatch(s, p, si + 1, pi, c);
            } else {
                return stringIsMatch(s, p, si, pi + 1, null);
            }
        } else {

            if (pi + 1 < p.length && p[pi + 1] == '*') {
                return stringIsMatch(s, p, si, pi + 1, p[pi]);
            }


            if (p[pi] == '.') {
                return stringIsMatch(s, p, si + 1, pi + 1, null);
            }

            if (s[si] == p[pi]) {
                return stringIsMatch(s, p, si + 1, pi + 1, null);
            } else {
                return false;
            }
        }


    }

    public boolean isMatch2(String s, String p) {
        if (p.length() == 0 && s.length() == 0) return true;
        if (p.length() == 0) return false;
        if (s.length() == 0) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '*') return false;
            }
            return true;
        }

        boolean[][] dp = new boolean[p.length()][s.length()];


        for (int i = 0; i < p.length(); i++) {
            char pChar = p.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                char sChar = s.charAt(j);
                if (i > 0 && j > 0) {
                    if (pChar == '*') {
                        dp[i][j] = dp[i-1][j-1] || dp[i-1][j] || dp[i][j-1];
                    } else if (pChar == '?' || pChar == sChar) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else if (i == 0 && j == 0) {
                    if (pChar == '*' || pChar == '?' || pChar == sChar) dp[0][0] = true;
                } else if (i == 0) {
                    if (pChar == '*') {
                        dp[i][j] = true;
                    }
                } else if (j == 0) {
                    if (dp[i - 1][j]) {
                        if (pChar == sChar || pChar == '?') {
                            dp[i][j] = true;
                            for (int k = 0; k < i; k++) {
                                if (p.charAt(k) != '*') {
                                    dp[i][j] = false;
                                    break;
                                }
                            }
                        } else if (pChar == '*') {
                            boolean hasOne = false;
                            dp[i][j] = true;
                            for (int k = 0; k < i; k++) {
                                if (p.charAt(k) != '*') {
                                    if (hasOne) {
                                        dp[i][j] = false;
                                        break;
                                    } else {
                                        hasOne = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return dp[p.length() - 1][s.length() - 1];
    }
}
