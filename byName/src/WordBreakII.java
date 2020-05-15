import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreakII {
    public static void main(String[] args) {
        System.out.println(new WordBreakII().wordBreak("catsandog", Arrays.asList(new String[]{"cats", "dog", "sand", "and", "cat"})));
        System.out.println(new WordBreakII().wordBreak("pineapplepenapple", Arrays.asList(new String[]{"apple", "pen", "applepen", "pine", "pineapple"})));
        System.out.println(new WordBreakII().wordBreak("catsanddog", Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"})));
    }
    public List<String> wordBreak(String s, List<String> wordDict){
        return dfs(s,0,new HashSet<>(wordDict), new HashMap<>());
    }                                 /**要截取某一段东西的时候往递归里噻一个index*/
    public List<String> dfs(String s, int start, HashSet<String> set, HashMap<Integer, List<String>> map){/***这里每一个细节都要好好记住，每一一遍不过分吧。。。*/
        List<String> list = new ArrayList<>();
        if(map.containsKey(start)) return map.get(start);
        if(start == s.length()) list.add("");/**精妙绝伦*/
        for(int end = start+1; end<=s.length(); end++){
            if(set.contains(s.substring(start,end))){
                List<String> temp = dfs(s,end,set,map);
                for(String string:temp){
                    list.add(s.substring(start,end)+(string.equals("")?"": " ")+string);
                }
            }
        }
        map.put(start,list);
        return list;
    }
}
