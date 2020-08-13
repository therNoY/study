package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Code77组合 {

    public static void main(String[] args) {
        System.out.println(new Code77组合().combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new LinkedList<>(), n, 0, k);
        return res;
    }

    private void backtrack(List<List<Integer>> res, LinkedList<Integer> visit, int count, int start, int length) {
        if (visit.size() == length) {
            res.add(new ArrayList<>(visit));
        } else {
            for (int i = start; i < count; i++) {
                visit.addLast(i + 1);
                backtrack(res, visit, count, i + 1, length);
                visit.pollLast();
            }
        }
    }
}
