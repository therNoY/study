package leetCode.sort;

public abstract class Sort {


    /**
     * 是否正序
     */
    protected boolean asc = true;

    public abstract void sort(int[] nums);

    /**
     * 交换
     *
     * @param nums
     * @param i
     * @param j
     */
    protected void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
