package leetCode.tags.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MajorityElement {

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        int[] a = {3,3,4};
        int res = majorityElement.majorityElement(a);
    }

    public int majorityElement(int[] nums) {
        int maxNum = -1, maxCount = -1, c = -1;
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int n : nums) {
            Integer count = map.get(n);
            if (count == null) {
                map.put(n, c = 1);
            }else {
                map.put(n, c = count + 1);
            }
            if (c > maxCount) {
                maxCount = c;
                maxNum = n;
            }
            if (maxCount > nums.length/2) return maxNum;
        }
        return maxNum;
    }


    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int maxNum = nums[0];
        int countNum = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == maxNum)
                countNum ++;
            else if (-- countNum == 0){
                maxNum = nums[i];
                countNum = 1;
            }
        }
        return maxNum;
    }
}
