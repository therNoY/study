package leetCode.every_day;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 */
public class Code415字符串相加 {
    public static void main(String[] args) {
        System.out.println(addStrings("98", "9"));
    }

    public static String addStrings(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        StringBuilder res = new StringBuilder();
        int i = 0, j = 0;
        int more = 0, r;
        while (i < chars1.length || j < chars2.length) {
            char char1 = i < chars1.length ? chars1[chars1.length - i - 1] : 48;
            char char2 = j < chars2.length ? chars2[chars2.length - j - 1] : 48;
            r = char1 + char2 - 96 + more;
            more = r / 10;
            res.append((char) (r - more * 10 + 48));
            i++;
            j++;
        }
        if (more > 0) res.append((char) (more + 48));
        return res.reverse().toString();
    }
}
