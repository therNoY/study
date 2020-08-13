package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class Code90子集II {
    public static void main(String[] args) {

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        backtrack(res, list, nums, -1);
        return res;
    }

    public void backtrack(List<List<Integer>> res, LinkedList<Integer> list, int[] nums, int start) {
        res.add((List<Integer>) list.clone());
        for (int i = start + 1; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) continue;
            list.addLast(nums[i]);
            backtrack(res, list, nums, i);
            list.pollLast();
        }
    }
}
