package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 140. 单词拆分 II 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。 说明： 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。 示例 1： 输入: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"] 输出: [   "cats and dog",
 *   "cat sand dog" ] 示例 2： 输入: s = "pineapplepenapple" wordDict = ["apple", "pen", "applepen", "pine", "pineapple"] 输出:
 * [   "pine apple pen apple",   "pineapple pen apple",   "pine applepen apple" ] 解释: 注意你可以重复使用字典中的单词。 示例 3： 输入: s =
 * "catsandog" wordDict = ["cats", "dog", "sand", "and", "cat"] 输出: [] 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code140单词拆分2 {

    public static void main(String[] args) {
        System.out.println(
//            new Code140单词拆分2().wordBreak("catsandog", Arrays.asList("cats", "dog", "do", "an", "sand", "and", "cat")));
            new Code140单词拆分2().wordBreak("catsanddog", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        /**
         * 整理词典
         */
        Map<Character, List<String>> wordMap = new HashMap<>();
        wordDict.forEach(string -> {
            Character character = string.charAt(string.length() - 1);
            List<String> stringList = wordMap.get(character);
            if (stringList == null) {
                stringList = new ArrayList<>();
                wordMap.put(character, stringList);
            }
            stringList.add(string);
        });

        /**
         * dp[i] 表示长度为i能否进行拆分
         */
        List<String>[] resList = new List[s.length()];
        char currentChar;
        List<String> stringList;
        for (int i = 0; i < s.length(); i++) {
            currentChar = s.charAt(i);
            stringList = wordMap.get(currentChar);
            if (stringList != null) {
                String string;
                for (int j = 0; j < stringList.size(); j++) {
                    string = stringList.get(j);
                    if (i >= string.length() - 1 &&
                        (i == string.length() - 1 || resList[i - string.length()] != null) &&
                        s.substring(i - string.length() + 1, i + 1).equals(string)) {
                        if (resList[i] == null) resList[i] = new ArrayList<>();
                        if (i == string.length() - 1) {
                            if (i != s.length()-1) {
                                resList[i].add(string + " ");
                            }else {
                                resList[i].add(string);
                            }
                        } else {
                            for (String s1 : resList[i - string.length()]) {
                                if (i != s.length()-1) {
                                    resList[i].add(s1 + string + " ");
                                }else {
                                    resList[i].add(s1 + string);
                                }
                            }
                        }

                    }
                }
            }
        }
        return resList[s.length() - 1] == null ? new ArrayList<>(1) : resList[s.length() - 1];
    }

}
