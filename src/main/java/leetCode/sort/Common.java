package leetCode.sort;

import java.util.Random;

public class Common {

    /**
     * 返回一定范围的随机整数
     *
     * @param start 开始最小的数字
     * @param end   最大的数字
     * @param num   有多少个数
     */
    public static int[] randomMake(int start, int end, int num) {
        if (start > end) {
            return null;
        }
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = (int) (Math.round(Math.random() * (end - start)) + start);
        }
        return result;
    }

    /**
     * 返回一定范围的随机整数
     */
    public static void show(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
