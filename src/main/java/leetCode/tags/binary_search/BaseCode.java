package leetCode.tags.binary_search;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class BaseCode {


    public static void main(String[] args) {

        int baseNum = 100;

        int[] nums = new int[baseNum];
        for (int i = 0; i < baseNum; i++) {
            nums[i] = new Random().nextInt(baseNum << 1);
        }

        nums = Arrays.stream(nums).boxed()
                .sorted()
                .mapToInt(Integer::valueOf)
                .toArray();

        int r = binarySearchLeft(nums, new Random().nextInt(baseNum << 1));

        nums = Arrays.stream(nums).boxed()
                .sorted()
                .distinct()
                .mapToInt(Integer::valueOf)
                .toArray();

        long whileSearch = 0;
        long recursiveSearch = 0;

        for (int i = 0; i < 100; i++) {
            int searchNum = new Random().nextInt(baseNum << 1);
            long start1 = new Date().getTime();
            int res = binarySearch3(nums, searchNum);
            whileSearch += new Date().getTime() - start1;
            long start2 = new Date().getTime();
            int res2 = binarySearch2(nums, searchNum);
            recursiveSearch += new Date().getTime() - start2;

            System.out.println("第" + i + "次比较 循环选出的结果:" + res + "递归：" + res2);


        }

        System.out.println("循环耗时" + whileSearch + "递归耗时" + recursiveSearch);
    }

    /**
     * 循环
     *
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearch2(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 搜索左边界 按顺序 但是有可能有重复的
     *
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearchLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 搜索右侧边界 按顺序 但是有可能有重复的
     *
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearchRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return left;
    }


    /**
     * 递归
     *
     * @param nums
     * @param i
     * @return
     */
    private static int binarySearch3(int[] nums, int i) {

        int res = recursive(nums, 0, nums.length - 1, i);
        return res;
    }

    private static int recursive(int[] nums, int left, int right, int target) {
        if (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                return recursive(nums, mid + 1, right, target);
            } else {
                return recursive(nums, left, mid - 1, target);
            }
        } else if (left == right) {
            if (nums[left] == target) return left;
        }
        return -1;
    }


    static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1; // 注意
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }
}
