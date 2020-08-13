package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Code78子集 {

    public static void main(String[] args) {
        System.out.println(new Code78子集().subsets2(new int[]{
                1, 2, 3
        }));
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        backtrack(res, list, nums, -1);
        return res;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            backtrack(res, list, nums, i + 1, 0);
        }
        return res;
    }

    public void backtrack(List<List<Integer>> res, LinkedList<Integer> list, int[] nums, int length, int index) {
        if (list.size() == length) {
            res.add((LinkedList<Integer>) list.clone());
        } else {
            for (int i = index; i < nums.length; i++) {
                if (!list.contains(nums[i])) {
                    list.addLast(nums[i]);
                    backtrack(res, list, nums, length, i + 1);
                    list.removeLast();
                }
            }
        }
    }


    public void backtrack(List<List<Integer>> res, LinkedList<Integer> list, int[] nums, int start) {
        res.add((List<Integer>) list.clone());
        for (int i = start + 1; i < nums.length; i++) {
            list.addLast(nums[i]);
            backtrack(res, list, nums, i);
            list.pollLast();
        }
    }
}
