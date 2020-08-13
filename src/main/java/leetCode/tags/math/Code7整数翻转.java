package leetCode.tags.math;

/**
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 */
public class Code7整数翻转 {
    public static void main(String[] args) {
//        System.out.println(new Code7整数翻转().reverse(123));
//        System.out.println(new Code7整数翻转().reverse(-123));
//        System.out.println(new Code7整数翻转().reverse(-120));
//        System.out.println(new Code7整数翻转().reverse(120));
        System.out.println(new Code7整数翻转().reverse(1534236469));
    }

    public int reverse(int x) {
        char[] chars = (x + "").toCharArray();
        boolean isF = chars[0] == '-';
        double res = 0;
        for (int i = isF ? 1 : 0, j = 0; i < chars.length; i++, j++) {
            res = res + ((chars[i] - 48) * Math.pow(10, j));
            if (res >= Integer.MAX_VALUE) return 0;
        }
        return (int) (res * (isF ? -1 : 1));
    }
}
