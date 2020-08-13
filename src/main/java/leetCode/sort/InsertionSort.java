package leetCode.sort;

/**
 * 插入排序
 */
public class InsertionSort extends Sort {


    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 记住当前在插入的值 和前一个比较
            int j = i - 1, insert = nums[i];
            while (j >= 0 && insert < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = insert;
        }

    }


    public void mySort(int[] nums) {

    }
}
