package leetCode.every_day;

import java.util.ArrayList;
import java.util.List;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * 提示：
 * 输入的字符串长度不会超过 1000 。
 * @Author mh32736
 * @Date 2020/8/20 8:40
 */
public class Code647回文子串 {

    public static void main(String[] args) {
        System.out.println(new Code647回文子串().countSubstrings("aaa"));
    }

    public int countSubstrings(String s) {

        /**
         * 如果除了自身回文 想要一串回文（除两个外 aa是回文的）
         * 要求除了前面的是回文的 并且需要和前面的回文的最前方的前一个相同
         */
//        int[] dp = new int[s.length()];

        List<Integer> list = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();

        return -1;
    }
}
