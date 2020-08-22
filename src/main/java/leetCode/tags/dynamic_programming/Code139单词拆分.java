package leetCode.tags.dynamic_programming;

import java.util.*;

/**
 * 139. 单词拆分 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict， 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。 说明： 拆分时可以重复使用字典中的单词。 你可以假设字典中没有重复的单词。
 * 示例 1： 输入: s = "leetcode", wordDict = ["leet", "code"] 输出: true 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"] 输出: true 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen
 * apple"。      注意你可以重复使用字典中的单词。 示例 3： 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] 输出: false
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/word-break 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code139单词拆分 {

    public static void main(String[] args) {
       System.out.println(
           new Code139单词拆分().wordBreak("catsandog", Arrays.asList("cats", "dog", "do", "an", "sand", "and", "cat")));
//        System.out.println(new Code139单词拆分().wordBreak("leetcode", Arrays.asList("leet", "code")));
    }

    /**
     * 动态规划
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
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
        boolean[] dp = new boolean[s.length()];
        char currentChar;
        List<String> stringList;
        for (int i = 0; i < dp.length; i++) {
            currentChar = s.charAt(i);
            stringList = wordMap.get(currentChar);
            if (stringList != null) {
                String string;
                for (int j = 0; j < stringList.size(); j++) {
                    string = stringList.get(j);
                    if (i >= string.length() - 1 &&
                        (i == string.length() - 1 || dp[i - string.length()]) &&
                        s.substring(i - string.length() + 1, i + 1).equals(string)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    /**
     * 自己实现的递归
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public boolean myWordBreak(String s, List<String> wordDict, Map<String, Boolean> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        if (s.equals("") || wordDict.contains(s)) {
            return true;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            if (wordDict.contains(s.substring(0, i)) && wordBreak(s.substring(i), wordDict)) {
                map.put(s, Boolean.TRUE);
                return true;
            }
        }
        map.put(s, Boolean.FALSE);
        return false;
    }

}
