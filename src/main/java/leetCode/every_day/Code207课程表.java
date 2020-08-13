package leetCode.every_day;

import java.util.*;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 */
public class Code207课程表 {

    public static void main(String[] args) {
        System.out.println(canFinish(3, new int[][]{{0,1}, {0,2}, {1,2}}));
    }

    /**
     * 使用map 进行记忆化递归
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = map.get(prerequisites[i][0]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(prerequisites[i][0], list);
            }
            list.add(prerequisites[i][1]);
        }
        boolean[] dp = new boolean[numCourses];
        for (int i = 0; i < dp.length; i++) {
            if (!helper(map, new HashSet<>(), i, dp)) return false;
        }
        return true;
    }

    /**
     *
     * @param map
     * @param nums 存放便利过的数
     * @return
     */
    private static boolean helper(Map<Integer, List<Integer>> map, Set<Integer> nums, int num, boolean[] dp) {
        if (dp[num]) return true;
        if (nums.contains(num)) return false;
        nums.add(num);
        List<Integer> need;
        if ((need = map.get(num)) == null) {
            dp[num] = true;
            return true;
        }else {
            for (int i = 0; i < need.size(); i++) {
                if (!helper(map, nums, need.get(i), dp)) return false;
            }
        }
        dp[num] = true;
        return true;
    }

}
