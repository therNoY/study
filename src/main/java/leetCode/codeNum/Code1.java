package leetCode.codeNum;


import java.util.ArrayList;
import java.util.List;

/**
 * 合并时间区间（建议时间复杂度 O(n) ）
 * 给定⼀个按开始时间从⼩到⼤排序的时间区间集合，请将重叠的区间合并。时
 * 间区间集合⽤⼀个⼆维数组表示，⼆维数组的每⼀⾏表示⼀个时间区间（闭区
 * 间），其中 0 位置元素表示时间区间开始，1 位置元素表示时间区间结束。
 * 例 1：输⼊：[ [1, 3], [2, 6], [8, 10], [15, 18] ]
 * 返回： [ [1, 6], [8, 10], [15, 18]]
 * 解释：时间区间 [1, 3] 和 [2, 6] 有部分重叠，合并之后为 [1, 6]
 * 例 2：输⼊：[[1, 4], [4, 5]]
 * 返回：[[1, 5]]
 * 解释：时间区间[1，4] 和 [4，5]重叠了⼀个时间点，合并之后为 [1，5]
 * 需要实现的⽅法原型：int[][] merge(int[][] intervals)
 */
public class Code1 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {5, 6}};
        merge(intervals);
    }

    static int[][] merge(int[][] intervals) {

        List<int[]> resList = new ArrayList<>();

        // 定义开始的时间段 和 当前时间段
        int[] pInterval = null,cInterval;
        int minStartTime = -1, endTime = -1;

        for (int intervalIndex = 0; intervalIndex < intervals.length; intervalIndex ++) {

            cInterval = intervals[intervalIndex];
            if (pInterval == null){
                pInterval = intervals[intervalIndex];
                minStartTime = getStartTime(pInterval);
                endTime = getEndTime(pInterval);
            }

            // 出现比结束时间大的开始时间段 放到结果集
            if (getStartTime(cInterval) > endTime) {
                int[] resIntervals = {minStartTime, endTime};
                resList.add(resIntervals);
                minStartTime = getStartTime(cInterval);
                endTime = getEndTime(cInterval);
            }else {
                endTime = Math.max(endTime, getEndTime(cInterval));
            }

        }

        if (pInterval != null){
            int[] resIntervals = {minStartTime, Math.max(endTime, getEndTime(pInterval))};
            resList.add(resIntervals);
        }

        int[][] resNums = new int[resList.size()][];

        for (int i = 0; i < resList.size(); i++) {
            resNums[i] = resList.get(i);
        }
        return resNums;
    }

    static int getEndTime(int[] interval) {

        if (interval.length > 1){
            return interval[1];
        }

        throw new RuntimeException();

    }

    static int getStartTime(int[] interval) {
        if (interval.length > 0){
            return interval[0];
        }

        throw new RuntimeException();
    }
}
