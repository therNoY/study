package leetCode.tags.array;

import java.util.Arrays;

/**
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 * <p>
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）
 * 总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数 不超过 h 次。）
 * <p>
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 *  
 * <p>
 * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/h-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class H_Index {

    public static void main(String[] args) {
        H_Index h_index = new H_Index();
//        int res = h_index.hIndex(new int[]{1, 1});
//        int res = h_index.hIndex(new int[]{100});
//        int res = h_index.hIndex(new int[]{3, 0, 6, 1, 5});
//        int res = h_index.hIndex2(new int[]{0,1,3,5,6});
//        int res = h_index.hIndex2(new int[]{1});
        int res = h_index.hIndex2(new int[]{1,1,1,1,3,3,4,4,5,6,7,7,8,9,10});
//        int res = h_index.hIndex2(new int[]{10,16,17,18,23,25,25,35,37,37,43,43,46,46,48,49,54,54,55,55,55,62,63,63,65,66,68,72,77,80,80,82,82,83,84,85,86,88,90,99,101,103,103,106,111,118,119,120,120,122,123,124,125,128,130,131,131,138,142,142,143,153,157,157,166,169,172,174,177,180,185,188,191,192,193,194,201,203,206,209,211,212,214,218,219,221,223,225,225,225,226,228,234,239,245,249});
    }

    public int hIndex(int[] citations) {
        int maxH = 0, tempNum;
        for (int i = 0, c = 0; i < citations.length; i++) {
            for (int j = 0; j < citations.length; j++) {
                if (citations[j] >= citations[i]) c++;
            }
            tempNum = Math.min(citations[i], c);
            maxH = Math.max(tempNum, maxH);
            c = 0;
        }
        return maxH;
    }


    /**
     * 一个原本的有序的
     * 0,1,3,5,6
     */
    public int hIndex2(int[] citations) {

        int maxNum = 0, temp;
        for (int i = 0; i < citations.length; i++) {
            if (maxNum <= (temp = Math.min(citations[i], citations.length - i))) maxNum = temp;
            else break;
        }
        return maxNum;
//        int maxNum = 0, s = 0, e = citations.length - 1, temp;
//
//        Boolean isFront = null;
//        for (int i; s <= e; ) {
//            i = (s + e) / 2;
//            if (maxNum < (temp = Math.min(citations[i], citations.length - i)))
//                maxNum = temp;
//
//            if ((isFront == null || isFront) && i + 1 < citations.length && maxNum <= (temp = Math.min(citations[i + 1], citations.length - i - 1))) {
//                maxNum = temp;
//                s = i + 1;
//                isFront = true;
//                continue;
//            }
//
//            if ((isFront == null || !isFront) && i - 1 >= 0 && maxNum <= (temp = Math.min(citations[i - 1], citations.length - i + 1))) {
//                maxNum = temp;
//                e = i - 1;
//                isFront = false;
//                continue;
//            }
//
//            if (isFront != null && s != e) {
//                if (isFront) {
//                    e = i - 1;
//                }else {
//                    s = i + 1;
//                }
//                continue;
//            }
//
//            return maxNum;
//        }
//
//        return maxNum;

    }

}
