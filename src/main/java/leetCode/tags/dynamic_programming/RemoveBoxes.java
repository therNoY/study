package leetCode.tags.dynamic_programming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 546. 移除盒子
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。
 * 示例 1：
 * 输入:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * 输出:
 * 23
 * 解释:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 */
public class RemoveBoxes {

    public static void main(String[] args) {
        System.out.println(new RemoveBoxes().removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }

    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
    }

    public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) return 0;
        if (dp[l][r][k] != 0) return dp[l][r][k];
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        // 直接消除之后的相连的
        dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                //
                dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
            }
        }
        return dp[l][r][k];
    }


    /**
     * 回溯
     * @param boxes
     * @return
     */
    public int removeBoxes2(int[] boxes) {
        if (boxes.length == 1) return 1;
        // 1.整理数据
        LinkedList<Integer> numList = new LinkedList<>();
        LinkedList<Integer> nums = new LinkedList<>();
        int temp = boxes[0];
        numList.add(temp);
        nums.add(1);
        for (int i = 1; i < boxes.length; i++) {
            if (boxes[i] != temp) {
                numList.add(boxes[i]);
                nums.add(1);
                temp = boxes[i];
            }else {
                nums.set(nums.size() - 1, numList.get(nums.size() - 1) + 1);
            }
        }

        return getRemoveMax(numList, nums);
    }

    /**
     * 使用回溯发
     * @param numList
     * @param nums
     * @return
     */
    private int getRemoveMax(LinkedList<Integer> numList, LinkedList<Integer> nums) {

        if (numList.size() == 1) return nums.get(0) * nums.get(0);

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < numList.size(); i++) {
            int num = numList.get(i);
            int price = nums.get(i);
            numList.remove(i);
            nums.remove(i);

            max = Math.max(max, getRemoveMax(numList, nums) + price * price);

            numList.add(num);
            nums.add(price);
        }

        return max;

    }
}
