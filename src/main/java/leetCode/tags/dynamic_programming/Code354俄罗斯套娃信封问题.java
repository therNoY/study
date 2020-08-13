package leetCode.tags.dynamic_programming;

/**
 * 354. 俄罗斯套娃信封问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 说明:
 * 不允许旋转信封。
 * 示例:
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class Code354俄罗斯套娃信封问题 {

    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
    }

    /**
     * 最长上升子序列的变种
     * 1.首先对一个边就进行排序 然后操作最长上升子序列
     *
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;
        bubbleSort(envelopes);
        int max = 1;
        /**
         * dp[i] 表示排好序之后以后面的值的结尾的最大上升子序列的长度
         */
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        for (int i = 1; i < envelopes.length; i++) {
            dp[i] = 1;
            int num1 = envelopes[i][0];
            int num2 = envelopes[i][1];
            for (int j = 0; j < i; j++) {
                if (num2 > envelopes[j][1] && num1 > envelopes[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 冒泡
     * @param envelopes
     */
    public static void bubbleSort(int[][] envelopes) {
        int length = envelopes.length;
        boolean isOrder;
        for (int i = 0; i < length - 1; i++) {
            isOrder = true;
            for (int j = 0; j < length - 1 - i; j++) {
                if (envelopes[j][0] > envelopes[j + 1][0]) {
                    isOrder = false;
                    swap(envelopes, j, j + 1);
                }
            }
            if (isOrder) return;
        }
    }

    public static void selectionSort(int[][] envelopes) {
        int cIndex;
        for (int i = 0; i < envelopes.length; i++) {
            cIndex = i;
            for (int j = i; j < envelopes.length; j++) {
                if (envelopes[cIndex][0] > envelopes[j][0]) cIndex = j;
            }
            if (cIndex != i) swap(envelopes, cIndex, i);
        }
    }

    public static void swap(int[][] nums, int i, int j) {
        int[] temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
