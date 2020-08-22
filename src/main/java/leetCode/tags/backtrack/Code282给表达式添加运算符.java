package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. 给表达式添加运算符
 * 给定一个仅包含数字 0-9 的字符串和一个目标值，在数字之间添加二元运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
 * 示例 1:
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 示例 2:
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 示例 3:
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 * 示例 4:
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 * 示例 5:
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 */
public class Code282给表达式添加运算符 {

    public static void main(String[] args) {
        System.out.println(new Code282给表达式添加运算符().addOperators("123", 6));
    }

    List<String> res = new ArrayList<>();
    int sum, lastNum, numIndex;
    StringBuilder sb = new StringBuilder();

    public List<String> addOperators(String num, int target) {
        if (num.length() == 0) return res;
        numIndex = 0;
        sum = num.charAt(numIndex) - 48;
        lastNum = sum;
        sb.append(lastNum);
        numIndex++;
        backtrack(num, target);
        return res;
    }

    public void backtrack(String num, int target) {
        if (numIndex == num.length()) {
            if (sum == target) res.add(sb.toString());
            sum = 0;
            lastNum = 0;
            sb.setLength(0);
        } else {
            int c = num.charAt(numIndex) - 48;
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        sb.append("+").append(c);
                        sum += c;
                        lastNum = c;
                        break;
                    case 1:
                        sb.append("-").append(c);
                        sum -= c;
                        lastNum = -c;
                        break;
                    case 2:
                        sb.append("*").append(c);
                        sum = sum - lastNum + lastNum * c;
                        lastNum = sum;
                        break;
                }
                numIndex++;
                backtrack(num, target);
                numIndex--;
            }
        }


    }

}
