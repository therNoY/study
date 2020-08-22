package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Code39组合总和 {

    public static void main(String[] args) {
//        System.out.println(new Code39组合总和().combinationSum(new int[]{
//                10, 1, 2, 7, 6, 1, 5
//        }, 8));
//        System.out.println(new Code39组合总和().combinationSum3(3, 9));
        System.out.println(new Code39组合总和().combinationSum4(new int[]{
                2, 1, 3
        }, 4));
    }

    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1：
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     * [7],
     * [2,2,3]
     * ]
     * 示例 2：
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     * [2,2,2,2],
     * [2,3,3],
     * [3,5]
     * ]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (target < candidates[i])
                    continue;
                list.add(candidates[i]);
                backtrack(res, list, candidates, target - candidates[i], i + 1);
                list.remove(list.size() - 1);
            }
        }
    }


    /**
     * 40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     * [1,2,2],
     * [5]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        Arrays.sort(candidates);
        backtrack2(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    public void backtrack2(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (target < candidates[i]) continue;
                if ((i > start && candidates[i] == candidates[i - 1])) continue;
                list.add(candidates[i]);
                backtrack2(res, list, candidates, target - candidates[i], i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int k, int n, int start) {
        if (list.size() == k && n == 0) {
            res.add(new ArrayList<>(list));
        } else if (list.size() < k && n > 0) {
            for (int i = start; i <= Math.min(n, 10); i++) {
                list.add(i);
                backtrack(res, list, k, n - i, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 377. 组合总和 Ⅳ
     * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
     * <p>
     * 示例:
     * <p>
     * nums = [1, 2, 3]
     * target = 4
     * <p>
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * <p>
     * 请注意，顺序不同的序列被视作不同的组合。
     * <p>
     * 因此输出为 7。
     */

    int count;

    public int combinationSum4(int[] nums, int target) {
//        count = 0;
//        backtrack(nums, new ArrayList<>(), target);
//        return count;
        /**
         * dp[i] 表示 目标数是j的前i个数组合总数量
         */
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i < target+1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }

    public void backtrack(int[] nums, List<Integer> list, int target) {
        if (target == 0) {
            count++;
        } else if (target > 0) {
            for (int i = 0; i < nums.length; i++) {
                list.add(nums[i]);
                backtrack(nums, list, target - nums[i]);
                list.remove(list.size() - 1);
            }
        }
    }
}
