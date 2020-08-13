package leetCode.sort;

public class ShellSort extends Sort {


    @Override
    public void sort(int[] nums) {
        int gap = nums.length;
        while (gap > 0) {
            gap = gap / 2;
            // 注意这里是从gap开始 相当于插入的从1开始
            for (int i = gap; i < nums.length; i++) {
                int j = i - gap,insert = nums[i];
                while (j >= 0 && insert < nums[j]) {
                    nums[j+gap] = nums[j];
                    j -= gap;
                }
                nums[j+gap] = insert;
            }
        }
    }

    public void mySort(int[] nums) {

    }
}
