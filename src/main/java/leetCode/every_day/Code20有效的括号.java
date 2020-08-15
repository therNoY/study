package leetCode.every_day;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 */
public class Code20有效的括号 {
    public static void main(String[] args) {
        System.out.println(new Code20有效的括号().isValid("([)]"));
    }

    /**
     * 左括号入栈 右括号匹配最近的出栈
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c== '{' || c == '[') {
                stack.addLast(c);
            }else {
                Character pollChar = stack.pollLast();
                if (pollChar == null || !isMatch(pollChar, c)) return false;
            }
        }
        return stack.size() == 0;
    }

    public boolean isMatch(char left, char right) {
        if (left == '(') return right == ')';
        if (left == '{') return right == '}';
        if (left == '[') return right == ']';
        return false;
    }
}
