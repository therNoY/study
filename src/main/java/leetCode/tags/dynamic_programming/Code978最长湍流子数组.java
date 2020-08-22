package leetCode.tags.dynamic_programming;

/**
 * 978. 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 返回 A 的最大湍流子数组的长度。
 * 示例 1：
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * 输入：[100]
 * 输出：1
 */
public class Code978最长湍流子数组 {

    public static void main(String[] args) {
        System.out.println(new Code978最长湍流子数组().maxTurbulenceSize(new int[]{72764, 55778, 56126, 4923, 67219,
                41218, 51928, 10800, 94338, 91010}));
//        System.out.println(new MaxTurbulenceSize().maxTurbulenceSize(
//                new int[]{20139, 81378, 2481, 28634, 85929, 85544, 11925, 81989, 7988, 37255,
//                        40179, 47403, 94119, 6931, 3304, 75116, 50953, 93923, 81930, 41072,
//                        46267, 68680, 30665, 87113, 62556, 7249, 18549, 1108, 35840, 62910,
//                        44416, 22825, 76705, 48168, 41182, 33930, 59080, 34150, 33752, 27250,
//                        74819, 72764, 55778, 56126, 4923, 67219, 41218, 51928, 10800, 94338,
//                        91010, 95816, 45597, 66196, 71747, 52922, 80013, 10182}));
//        System.out.println(new MaxTurbulenceSize().maxTurbulenceSize(new int[]{4,8,12,16}));
//        System.out.println(new MaxTurbulenceSize().maxTurbulenceSize(new int[]{4}));
    }

    public int maxTurbulenceSize(int[] A) {
        if (A.length <= 1) return A.length;
        int maxLength = 1;
        // dp[i][j] 表示字符串i到j是否是湍流子数
        boolean[][] dp = new boolean[A.length][A.length];

        boolean[] dp1 = new boolean[A.length];
        boolean[] dp2 = new boolean[A.length];

        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = i; j < A.length; j++) {
                if (j == i) {
                    dp2[i] = true;
                } else if ((j - i) == 1) {
                    dp2[i] = (A[i] != A[j]);
                } else {
                    dp2[i] = (dp[i + 1][j] && isDiff((A[i] - A[i + 1]), (A[i + 1] - A[i + 2])))
                            || (dp[i][j - 1] && isDiff((A[j] - A[j - 1]), (A[j - 1] - A[j - 2])));
                }
                maxLength = dp[i][j] ? Math.max(maxLength, j - i + 1) : maxLength;
            }
        }
        return maxLength;
    }


    public boolean isDiff(int a, int b) {
        if (a == 0 || b == 0) {
            return false;
        } else {
            return (a ^ b) < 0;
        }
    }

    public int maxTurbulenceSize2(int[] A) {
        if (A.length == 1) {
            return 1;
        }
        int[] dp = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            //如果i和i-1的值相等，那么i位的初始值为1，譬如A={9,9}，它返回的长度为1，而不是2。
            dp[i] = A[i] == A[i - 1] ? 1 : 2;
        }
        //初始化dp以后，从2到N去计算最长长度。
        //状态转移方程: dp[i] = dp[i-1] + 1;
        //i位的可能最大长度可能是：i-1位上最大长度 + 1（包含i自己）
        //那么什么时候可以加上自己算总长度呢，当i位和i-1位的大小正好跟i-1和i-2的大小情况相反。说明i成功可以加入到前面已经计算的总长度里。
        //否则i位就是默认初始化的长度。
        int max = dp[1];
        for (int i = 2; i < A.length; i++) {
            if (A[i - 1] - A[i - 2] > 0 && A[i] - A[i - 1] < 0 || A[i - 1] - A[i - 2] < 0 && A[i] - A[i - 1] > 0) {
                dp[i] = dp[i - 1] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


}
