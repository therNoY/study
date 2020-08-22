package leetCode.tags.dynamic_programming;

import java.util.Arrays;

/**
 * 740. 删除与获得点数
 * 给定一个整数数组 nums ，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [3, 4, 2]
 * 输出: 6
 * 解释:
 * 删除 4 来获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
 * 示例 2:
 * <p>
 * 输入: nums = [2, 2, 3, 3, 3, 4]
 * 输出: 9
 * 解释:
 * 删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * 注意:
 * <p>
 * nums的长度最大为20000。
 * 每个整数nums[i]的大小都在[1, 10000]范围内。
 * <p>
 * <p>
 * 1 2 2 8
 */
public class Code740删除与获得点数 {

    public static void main(String[] args) {
        System.out.println(new Code740删除与获得点数().deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
        System.out.println(new Code740删除与获得点数().deleteAndEarn(new int[]{3, 1}));
    }

    public int deleteAndEarn(int[] nums) {

        if (nums.length == 0) return 0;

        int[] distinctNums = Arrays.stream(nums).boxed()
                .distinct().sorted().mapToInt(Integer::valueOf).toArray();

        int[] numsCount = new int[distinctNums.length];

        for (int i = 0; i < distinctNums.length; i++) {
            int n = distinctNums[i];
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == n) {
                    numsCount[i]++;
                }
            }
        }

        /**
         * dp[i][0] 第i个数不抢取得的最大值
         * dp[i][1] 第i个数强取得的最大值
         */
        int[][] dp = new int[distinctNums.length][2];

        dp[0][0] = 0;
        dp[0][1] = distinctNums[0] * numsCount[0];

        for (int i = 1; i < distinctNums.length; i++) {
            if (distinctNums[i - 1] == distinctNums[i] - 1) {
                dp[i][1] = dp[i - 1][0] + distinctNums[i] * numsCount[i];
                dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            } else {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0]) + distinctNums[i] * numsCount[i];
                dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            }
        }
        return Math.max(dp[distinctNums.length - 1][0], dp[distinctNums.length - 1][1]);

    }
}
