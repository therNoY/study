package leetCode.hot_code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 */
public class Code1 {

    static int nums[] = {2, 7, 11, 15, 3, 9, 8};

    static int target = 11;

    public static void main(String[] args) {

        getTargetNum();

    }

    private static int[] getTargetNum() {
        Map<Integer, Integer> newNums = new HashMap(nums.length);

        int firstNum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] + firstNum == target){
                System.out.println("0 与 " + i);
                return new int[]{0, i};
            }else if (!newNums.containsKey(nums[i])){
                newNums.put(nums[i], i);
            }
        }

        for (int i = 2; i < nums.length; i++) {
            int wantNum = target - nums[i];
            Integer resV = null;
            if ((resV = newNums.get(wantNum)) != null && i != resV) {
                System.out.println(i + "  与 " + resV);
                return new int[]{i, resV};
            }
        }

        System.out.println("没有这个数");
        return null;
    }
}
