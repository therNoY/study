package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code46全排列 {

    public static void main(String[] args) {
        System.out.println(new Code46全排列().permute(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        getOnePermute(nums, new LinkedList<>(), res);
        return res;
    }

    public void getOnePermute(int[] nums, LinkedList<Integer> choiceNum, List<List<Integer>> res) {
        if (choiceNum.size() == nums.length) {
            res.add((LinkedList<Integer>) choiceNum.clone());
            return;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!choiceNum.contains(nums[i])) {
                    choiceNum.addLast(nums[i]);
                    getOnePermute(nums, choiceNum, res);
                    choiceNum.removeLast();
                }
            }
        }
    }
}
