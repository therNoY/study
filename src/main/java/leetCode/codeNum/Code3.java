package leetCode.codeNum;

import java.util.*;

/**
 * 给定⼀个 ⽆向图 包含 N 个节点和 M 条边, 每条边 Mi 的代价为 Ci 。图中⼀条
 * 路径的惩罚是指对该路径上所有边的代价 Ci 执⾏位运算或（bitwise OR）操
 * 作得到的。假如⼀条路径上包含了边 M1，M2，M3 … … ，Mk，那么对应的惩
 * 罚就是 C1 OR C2 OR C3 OR ... OR Ck。（OR代表位运算或，即 “|” ）
 * 问题：给定图上两个节点 start 和 end，找到从 start 到 end 的所有路径中惩罚
 * 值最⼩的路径，对应的最⼩惩罚值作为结果返回。如果路径不存在就返回 -1。
 * 注意：任意两个节点之间最多存在⼀条边，图中可能存在有环路。
 */
public class Code3 {


    // 记录一个邻接表信息
    static Map<Integer, Map<Integer, Integer>> map = new LinkedHashMap<>();
    // 记录是否访问过
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) {

        int[][] edges = {{1, 2, 1}, {2, 3, 3}, {1, 3, 100}};
        minPath(3, edges, 1, 3);

    }

    static int minPath(int n, int[][] edges, int start, int end) {

        // 先将结果封装成邻接表 start 作为key Map<Integer, Integer> 作为value 储存可达的节点和值
        for (int i = 0; i < edges.length; i++) {
            int[] res = edges[i];
            Map<Integer, Integer> kMap = map.get(res[0]);
            if (kMap == null) {
                kMap = new HashMap<>();
                map.put(res[0], kMap);
            }
            kMap.put(res[1], res[2]);
        }


        int minR = Integer.MAX_VALUE;


        Map<Integer, Integer> startPoint = map.get(start);
        for (Map.Entry<Integer, Integer> e : startPoint.entrySet()) {
            Integer s = e.getKey();
            if (!visited.contains(e.getKey())) {
                minR = Math.min(minR, DFS(s, end, map.get(start).get(s)));
                System.out.println();
                visited.clear();
            }
        }

        return minR;
    }

    /**
     * 递归深度优先遍历
     */
    static int DFS(Integer start, Integer end, Integer value) {
        System.out.print(" 访问" + start + " ");

        if (start == end){
            System.out.println("  访问到目标节点" + end + "返回" + value);
            return value;
        }

        visited.add(start);
        Map<Integer, Integer> startMap = map.get(start);
        // 没有可达的节点直接返回
        if (startMap == null)
            return Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> e : startMap.entrySet()) {
            System.out.print("访问" + start +"的路径" + e.getKey());
            // 判断是否访问过 没有访问过就访问
            if (!visited.contains(e.getKey())) {
                int v = DFS(e.getKey(), end, map.get(start).get(e.getKey()));
                return v == Integer.MAX_VALUE ? v : v | value;
            }else {
                return Integer.MAX_VALUE;
            }
        }

        return Integer.MAX_VALUE;

    }
}
