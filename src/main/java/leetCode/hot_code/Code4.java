package leetCode.hot_code;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 1 12 14 15
 * <p>
 * 9 10 11 12
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code4 {


    public static void main(String[] args) {
        int[] nums1 = {1};
//        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] nums2 = {2,3,4};
        findMedianSortedArrays(nums1, nums2);
    }

    // 使用的解法属于leetCode答案的第K小数
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0) {
            if (nums2.length % 2 == 0){
                return (nums2[nums2.length/2] + nums2[nums2.length/2 - 1])/2.0;
            }else {
                return nums2[nums2.length/2];
            }
        }else if (nums2.length == 0) {
            if (nums1.length % 2 == 0){
                return (nums1[nums1.length/2] + nums1[nums1.length/2 - 1])/2.0;
            }else {
                return nums1[nums1.length/2];
            }
        }

        int a1L = nums1.length, a2L = nums2.length;

        int sL = a1L + a2L;

        int a1S = 0, a2S = 0;

        // 需要找的第 k 小数
        int k = (sL + 2) / 2;
        int i, a1I, a2I, fontI = -1, minI = -1;
        for (i = k / 2; ; i = (k - a1S - a2S) / 2) {
            fontI = Math.max(fontI, minI);
            // 如果要找的数是第一个 直接返回
            if (1 == k - a1S - a2S) {
                if (a1S >= a1L && a1L != 0) {
                    minI = nums2[a2S];
                } else if (a2S >= a2L && a2L != 0) {
                    minI = nums1[a1S];
                } else {
                    minI = Math.min(nums1[a1S], nums2[a2S]);
                }
                break;
            }
            a1I = Math.min(i - 1, a1L - 1);
            a2I = Math.min(i - 1, a2L - 1);


            // 前两种情况是有一个数组为空了
            if (a1S >= a1L){
                minI = nums2[a2S + a2I];
                a2S += a2I + 1;
            }else if (a2S >= a2L){
                minI = nums1[a1S + a1I];
                a1S += a1I + 1;
            }else if (nums1[a1S + a1I] < nums2[a2S + a2I]) {
                minI = nums1[a1S + a1I];
                a1S += a1I + 1;
            } else {
                minI = nums2[a2S + a2I];
                a2S += a2I + 1;
            }
        }


        if (sL % 2 == 0)
            return (fontI + minI) / 2.0;
        else
            return minI;
    }
}
