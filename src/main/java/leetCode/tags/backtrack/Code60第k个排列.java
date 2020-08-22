package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. 第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class Code60第k个排列 {
    public static void main(String[] args) {
        System.out.println(new Code60第k个排列().getPermutation(9, 28533));
    }

    int currCount = 0;
    String res = null;

    /**
     * 就是全排列
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        boolean[] isHas = new boolean[n+1];
        backtrack(new ArrayList<>(),isHas, n, k);
        return res;
    }

    private void backtrack(List<Integer> list,boolean[] isHas, int n, int count) {
        if (list.size() == n) {
            if (++currCount == count) {
                StringBuilder sb = new StringBuilder();
                list.forEach(s -> {
                    sb.append(s);
                });
                res = sb.toString();
            }
        } else {
            for (int i = 1; i <= n; i++) {
                if (!isHas[i]) {
                    list.add(i);
                    isHas[i] = true;
                    if (res == null) backtrack(list,isHas, n, count);
                    list.remove(list.size() - 1);
                    isHas[i] = false;
                }
            }
        }
    }

}


