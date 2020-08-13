package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class Code93复原IP地址 {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("010010"));
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s, 4, new LinkedList<>(), res);
        return res;
    }

    public static void helper(String s, int need, LinkedList<String> his, List<String> res) {
        if (need == 1) {
            if (s.length() <= 3 &&
                    Integer.valueOf(s) <= 255
                    && (s.length() == 1 || s.charAt(0) != '0')) {
                his.addLast(s);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < his.size(); i++) {
                    stringBuilder.append(his.get(i));
                    if (i != his.size() - 1) stringBuilder.append(".");
                }
                res.add(stringBuilder.toString());
                his.removeLast();
            }
        } else {
            int index = 1;
            String tempS;
            while (index < s.length()
                    && Integer.valueOf(tempS = s.substring(0, index)) <= 255
                    && (tempS.length() == 1 || tempS.charAt(0) != '0')) {
                his.addLast(tempS);
                helper(s.substring(index), need - 1, his, res);
                his.removeLast();
                index++;
            }
        }
    }

}
