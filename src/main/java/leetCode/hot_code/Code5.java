package leetCode.hot_code;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
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
public class Code5 {


    public static void main(String[] args) {

        System.out.println(longestPalindrome("azwdzwmwcqzgcobeeiphemqbjtxzwkhiqpbrprocbppbxrnsxnwgikiaqutwpftbiinlnpyqstkiqzbggcsdzzjbrkfmhgtnbujzszxsycmvipjtktpebaafycngqasbbhxaeawwmkjcziybxowkaibqnndcjbsoehtamhspnidjylyisiaewmypfyiqtwlmejkpzlieolfdjnxntonnzfgcqlcfpoxcwqctalwrgwhvqvtrpwemxhirpgizjffqgntsmvzldpjfijdncexbwtxnmbnoykxshkqbounzrewkpqjxocvaufnhunsmsazgibxedtopnccriwcfzeomsrrangufkjfzipkmwfbmkarnyyrgdsooosgqlkzvorrrsaveuoxjeajvbdpgxlcrtqomliphnlehgrzgwujogxteyulphhuhwyoyvcxqatfkboahfqhjgujcaapoyqtsdqfwnijlkknuralezqmcryvkankszmzpgqutojoyzsnyfwsyeqqzrlhzbc"));
    }

    // 思路： 从第一个字符开始判断 判断到最后两个
    public static String longestPalindrome(String s) {

        if (s.length() == 0) {
            return s;
        }

        String maxHuiWen = s.substring(0, 1);

        for (int sIndex = 0, length = s.length() - 1; sIndex < s.length() && maxHuiWen.length() < length - sIndex + 1; sIndex++, length = s.length() - 1) {
            for (; sIndex < length; length--) {
                if (huiWen(s, sIndex, length)) {
                    String newHuiWen = s.substring(sIndex, length + 1);
                    maxHuiWen = maxHuiWen.length() < newHuiWen.length() ? newHuiWen : maxHuiWen;
                    break;
                }
            }
        }
        return maxHuiWen;
    }

    public static boolean huiWen(String s, int i, int l) {


        int i1, l1;
        if ((i + l) % 2 == 0) {
            i1 = l1 = (i + l) / 2;
        } else {
            i1 = (i + l) / 2;
            l1 = i1 + 1;
        }

        char[] chars = s.toCharArray();
        for (;i1 >= i && l1 <= l;i1 --, l1 ++) {
            if (chars[i1] != chars[l1]) {
                return false;
            }
        }
        return true;
    }
}
