package leetCode.tags.backtrack;

import java.util.Arrays;

public class Code31下一个排列 {

    public static void main(String[] args) {
        new Code31下一个排列().nextPermutation(new int[]{
                1, 3, 2
        });
    }

    public void nextPermutation(int[] nums) {
        int descIndex = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                descIndex = i - 1;
                break;
            }
        }
        if (descIndex != -1) {
            int minBig = Integer.MAX_VALUE, minBigIndex = descIndex;
            for (int i = descIndex + 1; i < nums.length; i++) {
                if (nums[i] - nums[descIndex] < minBig) {
                    minBigIndex = i;
                    minBig = nums[i] - nums[descIndex];
                }
            }
            swap(nums, minBigIndex, descIndex);
            return;
        }
        Arrays.sort(nums);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
