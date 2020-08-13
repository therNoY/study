package leetCode.tags.array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        System.out.println(Math.abs((long)-2147483648));
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        int[] a = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
//        containsDuplicate.containsDuplicate(a);
        int[] b = {-1,2147483647};
        int k = 1;
        int t = 2147483647;
        containsDuplicate.containsNearbyAlmostDuplicate(b, k, t);
    }

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                return true;
            else
                map.put(nums[i], Boolean.TRUE);
        }
        return false;

    }

    /**
     * 给定一个整数数组和一个整数 k，
     * 判断数组中是否存在两个不同的索引 i 和 j，
     * 使得 nums [i] = nums [j]，
     * 并且 i 和 j 的差的 绝对值 至多为 k。
     * <p>
     * <p>
     * 示例 1:
     * 输入: nums = [1,2,3,1], k = 3
     * 输出: true
     * 示例 2:
     * 输入: nums = [1,0,1,1], k = 1
     * 输出: true
     * 示例 3:
     * 输入: nums = [1,2,3,1,2,3], k = 2
     * 输出: false
     *
     * @param nums
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        Integer j;
        for (int i = 0; i < nums.length; i++) {
            if ((j = map.get(nums[i])) != null) {
                if (i - j <= k) return true;
                else map.put(nums[i], i);
            } else
                map.put(nums[i], i);
        }
        return false;

    }

    /**
     *
     * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t
     * ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
     * 如果存在则返回 true，不存在返回 false。
     *
     * 示例 1:
     *
     * 输入: nums = [1,2,3,1], k = 3, t = 0
     * 输出: true
     * 示例 2:
     *
     * 输入: nums = [1,0,1,1], k = 1, t = 2
     * 输出: true
     * 示例 3:
     *
     * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
     * 输出: false
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) <= t && Math.abs(i-j) <= k)
                    return true;
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
       TreeSet<Integer> treeSet = new TreeSet();
        for (int i = 0; i < nums.length; i++) {
            Integer c = treeSet.ceiling(nums[i]);
            if (c != null && Math.abs((long)nums[i] - c) <= t) return true;
            Integer f = treeSet.floor(nums[i]);
            if (f != null && Math.abs((long)nums[i] - f) <= t) return true;
            if (treeSet.size() > k) treeSet.remove(nums[i - k]);
            treeSet.add(nums[i]);
        }
        return false;

    }
}
