package leetCode.dfs_bfs;

import java.util.Arrays;
import java.util.List;

/**
 * 127. 单词接龙 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则： 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。 说明: 如果不存在这样的转换序列，返回 0。 所有单词具有相同的长度。 所有单词只由小写字母组成。 字典中不存在重复的单词。 你可以假设 beginWord 和 endWord
 * 是非空的，且二者不相同。 示例 1: 输入: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。 示例 2:
 * <p>
 * 输入: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class Code127单词接龙 {

    public static void main(String[] args) {
        System.out.println(new Code127单词接龙().ladderLength("hit", "cog",
            Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
    int min = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.remove(beginWord);
        boolean[] isVisit = new boolean[wordList.size()];
        dfs(isVisit, wordList, beginWord, endWord, 0);
        System.out.println(min);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public void dfs(boolean[] isVisit, List<String> wordList, String current, String end, int step) {
        if (current.equals(end)) {
            min = Math.min(step, min);
        }
        for (int i = 0; i < isVisit.length; i++) {
            if (!isVisit[i] && isHasOneWord(wordList.get(i), current)) {
                System.out.println(current + "->" + wordList.get(i));
                isVisit[i] = true;
                dfs(isVisit, wordList, wordList.get(i), end, step + 1);
            }
        }
    }

    private boolean isHasOneWord(String s, String current) {
        if (s.length() != current.length()) {
            return false;
        }
        int maxDif = 1, currDif = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != current.charAt(i)) {
                currDif++;
            }
            if (currDif > maxDif) {
                return false;
            }
        }
        return true;
    }

}
