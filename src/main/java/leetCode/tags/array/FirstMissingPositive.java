package leetCode.tags.array;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstMissingPositive {

    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
//        int a[] = {3,4,-1,1};
        int a[] = {1,2,0};
        int res = firstMissingPositive.firstMissingPositive(a);
    }


    public int firstMissingPositive(int[] nums) {
        // 1.将大于数组长度的和复数改成1
        boolean has1 = false;
        for (int i = 0; i < nums.length; i++) {
            if (!has1 && nums[i] == 1) has1 = true;
            if (nums[i] < 1 || nums[i] > nums.length) nums[i] = 1;
        }
        if (!has1) return 1;
        // 2.映射hash
        for (int i = 0, index; i < nums.length; i++) {
            index = Math.abs(nums[i]) - 1;
            nums[index] = -1 * Math.abs(nums[index]);
        }
        // 3.判断第一个大于0的
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return nums.length + 1;
    }
}
