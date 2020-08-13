package leetCode.codeNum;

/**
 * 你有两个 有序 且数组内元素互不相同的数组 nums1 和 nums2 。
 * <p>
 * 一条 合法路径 定义如下：
 * <p>
 * 选择数组 nums1 或者 nums2 开始遍历（从下标 0 处开始）。
 * 从左到右遍历当前数组。
 * 如果你遇到了 nums1 和 nums2 中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。
 * 得分定义为合法路径中不同数字的和。
 * <p>
 * 请你返回所有可能合法路径中的最大得分。
 * <p>
 * 由于答案可能很大，请你将它对 10^9 + 7 取余后返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * 输出：30
 * 解释：合法路径包括：
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],（从 nums1 开始遍历）
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]  （从 nums2 开始遍历）
 * 最大得分为上图中的绿色路径 [2,4,6,8,10] 。
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,3,5,7,9], nums2 = [3,5,100]
 * 输出：109
 * 解释：最大得分由路径 [1,3,5,100] 得到。
 * 示例 3：
 * <p>
 * 输入：nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
 * 输出：40
 * 解释：nums1 和 nums2 之间无相同数字。
 * 最大得分由路径 [6,7,8,9,10] 得到。
 * 示例 4：
 * <p>
 * 输入：nums1 = [1,4,5,8,9,11,19], nums2 = [2,3,4,11,12]
 * 输出：61
 * [6,7,12,13,14,17,20]
 * [1,4,5,7]
 */
public class Code5478最大得分 {

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{1,4,5,8,9,11,19}, new int[]{2,3,4,11,12}));
//        System.out.println(maxSum(new int[]{2,4,5,8,10}, new int[]{4,6,8,9}));
    }

    public static int maxSum(int[] nums1, int[] nums2) {
        /**
         * dp[i][j][0] 表示
         */
        int[][][] dp = new int[nums2.length][nums1.length][2];
        for (int i = 0; i < nums2.length; i++) {
            for (int j = 0; j < nums1.length; j++) {
                int num1 = nums1[j], num2 = nums2[i];
                if (i == 0 && j == 0) {
                    dp[i][j][0] = num1;
                    dp[i][j][1] = num2;
                } else if (i == 0) {
                    dp[i][j][0] = dp[i][j - 1][0] + num1;
                    dp[i][j][1] = num1 == num2 ? dp[i][j][0] : dp[i][j - 1][1];
                } else if (j == 0) {
                    dp[i][j][1] = dp[i - 1][j][1] + num2;
                    dp[i][j][0] = num1 == num2 ? dp[i][j][1] : dp[i - 1][j][0];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i][j - 1][0] + num1);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1] + num2, dp[i][j - 1][1]);
                    if (num1 == num2) {
                        dp[i][j][0] = dp[i][j][1] = Math.max(dp[i][j][0], dp[i][j][1]);
                    }
                }
            }
        }
        return Math.max(dp[nums2.length - 1][nums1.length - 1][0], dp[nums2.length - 1][nums1.length - 1][1]);
    }
}
