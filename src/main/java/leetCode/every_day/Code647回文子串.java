package leetCode.every_day;

import java.util.ArrayList;
import java.util.List;

/**
 * 647. 回文子串 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 示例 1： 输入："abc" 输出：3 解释：三个回文子串: "a",
 * "b", "c" 示例 2： 输入："aaa" 输出：6 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 提示： 输入的字符串长度不会超过 1000 。
 *
 * @Author mh32736
 * @Date 2020/8/20 8:40
 */
public class Code647回文子串 {

    public static void main(String[] args) {
        System.out.println(new Code647回文子串().countSubstrings("abc"));
    }

    /**
     * 思想还是dp 可以看成有List[] 优化
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int count = 1;
        // 表示前一个符合回文的头一个字母的index
        List<Integer> list = new ArrayList<>();
        // 表示当前符合回文的头一个字母的index
        List<Integer> currentList = new ArrayList<>();
        list.add(0);
        int c;
        char currChar;
        for (int i = 1; i < s.length(); i++) {
            currChar = s.charAt(i);
            currentList.clear();
            currentList.add(i);
            for (int j = 0; j < list.size(); j++) {
                if ((c = list.get(j)) > 0 && s.charAt(c - 1) == currChar) {
                    currentList.add(c - 1);
                }
            }
            if (i > 0 && s.charAt(i - 1) == s.charAt(i)) {
                currentList.add(i - 1);
            }
            list = new ArrayList<>(currentList);
            count += currentList.size();
        }
        return count;
    }
}
