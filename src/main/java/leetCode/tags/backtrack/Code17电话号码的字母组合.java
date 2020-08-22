package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code17电话号码的字母组合 {

    public static void main(String[] args) {
        System.out.println(new Code17电话号码的字母组合().letterCombinations("23"));
    }

    Map<Character, char[]> map = new HashMap<>();
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        init();

        backtrack(new ArrayList<>(), digits, 0);

        return res;
    }

    private void backtrack(List<Character> list, String digits, int digitStart) {
        if (list.size() == digits.length()) {
            StringBuilder sb = new StringBuilder();
            list.forEach(s -> sb.append(s));
            res.add(sb.toString());
        } else {
            char c = digits.charAt(list.size());
            char[] chars = map.get(c);
            for (int j = 0; j < chars.length; j++) {
                list.add(chars[j]);
                backtrack(list, digits, digitStart + 1);
                list.remove(list.size() - 1);
            }
        }

    }

    private void init() {
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'g', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

}
