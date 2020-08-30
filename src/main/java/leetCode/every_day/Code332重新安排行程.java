package leetCode.every_day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 332. 重新安排行程 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从
 * JFK 开始。
 * <p>
 * 说明:
 * <p>
 * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。 示例 1:
 * <p>
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]] 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"] 示例 2:
 * <p>
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 * [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
 */
public class Code332重新安排行程 {

    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList<>();
//        lists.add(Arrays.asList("MUC", "LHR"));
//        lists.add(Arrays.asList("JFK", "MUC"));
//        lists.add(Arrays.asList("SFO", "SJC"));
//        lists.add(Arrays.asList("LHR", "SFO"));
        lists.add(Arrays.asList("JFK", "KUL"));
        lists.add(Arrays.asList("JFK", "NRT"));
        lists.add(Arrays.asList("NRT", "JFK"));
        System.out.println(new Code332重新安排行程().findItinerary(lists));
    }

    Map<String, PriorityQueue<String>> map = new HashMap<>();
    LinkedList<String> resultList = new LinkedList<>();
    int resultSize;

    /**
     * 图的深度优先
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        resultSize = tickets.size() + 1;
        // 预处理
        String start, end;
        for (List<String> list : tickets) {
            start = list.get(0);
            end = list.get(1);
            PriorityQueue<String> path = map.get(start);
            if (path == null) {
                path = new PriorityQueue<>();
                map.put(start, path);
            }
            path.add(end);
        }
        dfs("JFK");
        return resultList;
    }

    public void dfs(String p) {
        PriorityQueue<String> list = map.get(p);
        while (list != null && list.size() > 0) {
            dfs(list.poll());
        }
        resultList.addLast(p);
    }

}
