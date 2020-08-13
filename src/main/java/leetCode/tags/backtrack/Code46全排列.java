package leetCode.tags.backtrack;

import java.util.*;

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
//        System.out.println(new Code46全排列().permute(new int[]{1, 2, 3}));
        System.out.println(new Code46全排列().permuteUnique(new int[]{1, 1, 2}));
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

    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 示例:
     * 输入: [1,1,2]
     * 输出:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), new boolean[nums.length], nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> list, boolean[] used, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
        } else if (list.size() < nums.length) {
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue;
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                list.add(nums[i]);
                used[i] = true;
                backtrack(res, list, used, nums);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }
}
