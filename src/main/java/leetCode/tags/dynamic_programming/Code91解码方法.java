package leetCode.tags.dynamic_programming;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Code91解码方法 {

    public static void main(String[] args) {
        System.out.println(new Code91解码方法().numDecodings("17"));
//        System.out.println(new DecodeWays().numDecodings("10"));
//        System.out.println(new DecodeWays().numDecodings("120"));
//        System.out.println(new DecodeWays().numDecodings("12"));
//        System.out.println(new DecodeWays().numDecodings("226"));
    }


    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[] dp = new int[s.length()];

        if (s.charAt(0) == '0') {
            return 0;
        }
        dp[0] = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    dp[i] = i >= 2 ? dp[i - 2] : 1;
                } else {
                    return 0;
                }
            } else {
                if (s.charAt(i - 1) == '0') {
                    dp[i] = dp[i - 1];
                } else {
                    if (s.charAt(i-1) == '1') {
                        if (s.charAt(i) > '6') {
                            dp[i] = i >= 2 ? dp[i - 2] : 2;
                        } else {
                            dp[i] = dp[i - 1] + 1;
                        }
                    }else if (s.charAt(i-1) == '2') {
                        if (s.charAt(i) <= '6') {
                            dp[i] = dp[i - 1] + 1;
                        } else {
                            dp[i] = dp[i - 1];
                        }
                    }else {
                        dp[i] = dp[i - 1];
                    }
                }
            }
        }
        return dp[s.length() - 1];

    }
}
