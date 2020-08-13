package leetCode.sort;

/**
 * 冒泡排序 交换次数多
 */
public class BubbleSort extends Sort {


    /**
     * 循环n次 每次都是选出来最大的放到最后面
     *      逐个和前一个进行比较 比较i次
     * @param nums
     */
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 注意这里只需要排到nums.length - i - 1; -i 是因为有i个拍好了 -1 是因为判断里面有+1
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) swap(nums, j, j + 1);
            }
        }
    }

    /**
     * 自己练习
     * @param nums
     */
    private void mySort(int[] nums) {

    }


}
