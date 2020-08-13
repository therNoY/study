package leetCode.sort;

public class MergeSort extends Sort {


    @Override
    public void sort(int[] nums) {
        int[] temp = new int[nums.length];
        // 注意-1
        mergerSort(nums, temp, 0, nums.length - 1);
    }

    private void mergerSort(int[] nums, int[] temp, int start, int end) {
        if (start >= end) return;
        int mid = start + (end - start) / 2;
        mergerSort(nums, temp, start, mid);
        // 注意+1
        mergerSort(nums, temp, mid + 1, end);
        merge(nums, temp, start, mid, end);
    }

    public void merge(int[] nums, int[] temp, int start, int mid, int end) {
        int length = 0, left = start, right = mid + 1;
        // 注意带等号
        while (left <= mid && right <= end) {
            if (nums[left] < nums[right])  temp[length++] = nums[left++];
            else temp[length++] = nums[right++];
        }
        while (left <= mid) temp[length++] = nums[left++];
        while (right <= end) temp[length++] = nums[right++];
        for (int t = 0; t < length; t++) nums[t + start] = temp[t];
    }

    public void myMerge(int[] nums, int[] temp, int start, int mid, int end) {
    }

}
