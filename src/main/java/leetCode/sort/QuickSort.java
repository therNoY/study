package leetCode.sort;

/**
 * 快速排序
 */
public class QuickSort extends Sort {

    @Override
    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    /**
     * 快速排序挖坑方法
     */
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int pivot = getPivot(nums, start, end);
        quickSort(nums, start, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }
    private int getPivot(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= pivot) end--;
            if (start < end) nums[start] = nums[end];
            while (start < end && nums[start] <= pivot) start++;
            if (start < end) nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    public void mySort(int[] nums, int start, int end) {
        if (start >= end) return;
        int pivot = myGetPivot(nums, 0, nums.length - 1);
        mySort(nums, start, pivot - 1);
        mySort(nums, pivot + 1, end);
    }

    /**
     * 以第一个为基准 耗时大
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int getPivot2(int[] nums, int start, int end) {
        int pivot = nums[start], firstSwap = start + 1;
        for (int i = firstSwap; i <= end; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, firstSwap);
                firstSwap++;
            }
        }
        swap(nums, start, firstSwap - 1);
        return firstSwap - 1;
    }

    private int myGetPivot(int[] nums, int start, int end) {
        return -1;
    }

}
