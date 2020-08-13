package leetCode.tags.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class Code76最小覆盖子串 {

    public static void main(String[] args) {

//        System.out.println(new Code76最小覆盖子串().minWindow("ADOBECODEBANC", "ABC"));
//        System.out.println(new Code76最小覆盖子串().minWindow2("ADOBECODEBANC", "ABC"));
        System.out.println(new Code76最小覆盖子串().minWindow2("a", "aa"));

    }


    public String minWindow(String s, String t) {
        int left, right, count, minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;

        //needs存储t的<字符,出现次数>,windows存储<s中与t中字符相同的字符,出现次数>
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();

        //初始化needs
        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }

        left = right = count = 0;
        while (right < s.length()) {
            //获取字符
            char temp = s.charAt(right);
            //如果是t中字符，在windows里添加，累计出现次数
            if (needs.containsKey(temp)) {
                windows.put(temp, windows.getOrDefault(temp, 0) + 1);
                //注意：Integer不能用==比较，要用compareTo
                if (windows.get(temp).compareTo(needs.get(temp)) == 0) {
                    //字符temp出现次数符合要求，count代表符合要求的字符个数
                    count++;
                }
            }
            //优化到不满足情况，right继续前进找可行解
            right++;
            //符合要求的字符个数正好是t中所有字符，获得一个可行解
            while (count == needs.size()) {
                //更新结果
                if (right - left < minLen) {
                    start = left;
                    end = right;
                    minLen = end - left;
                }
                //开始进行优化，即缩小区间，删除s[left],
                char c = s.charAt(left);

                //当前删除的字符包含于t,更新Windows中对应出现的次数，如果更新后的次数<t中出现的次数，符合要求的字符数减1，下次判断count==needs.size()时不满足情况，
                if (needs.containsKey(c)) {
                    windows.put(c, windows.getOrDefault(c, 1) - 1);
                    if (windows.get(c) < needs.get(c)) {
                        count--;
                    }
                }
                left++;
            }

        }
        //返回覆盖的最小串
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }

    public String minWindow2(String s, String t) {
        int left = 0, start = 0, end = 0, right = 0, minLen = Integer.MAX_VALUE;
        int count = 0;
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }

        while (right < s.length()) {
            char temp = s.charAt(right);
            if (needs.containsKey(temp)) {
                window.put(temp, window.getOrDefault(temp, 0) + 1);
                if (needs.get(temp) == window.get(temp)) {
                    count++;
                }
            }
            right++;
            while (count == needs.size()) {
                temp = s.charAt(left);
                // 已经满足条件 准备收缩
                if (right - left < minLen) {
                    start = left;
                    end = right;
                    minLen = right - left;
                }

                if (needs.containsKey(temp)) {
                    window.put(temp, window.get(temp) - 1);
                    if (needs.get(temp) > window.get(temp)) {
                        count--;
                    }
                }
                left ++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end+1);
    }

}
