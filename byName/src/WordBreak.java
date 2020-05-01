import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {
    public static void main(String[] args) {
        WordBreak b = new WordBreak();
        System.out.println(b.wordBreak("leetcode", Arrays.asList(new String[]{"leet", "code"})));
        System.out.println(b.wordBreak("applepenapple", Arrays.asList(new String[]{"apple", "pen"})));
        System.out.println(b.wordBreak("catsandog", Arrays.asList(new String[]{"cats", "dog","sand","and","cat"})));
        System.out.println(b.wordBreakII("L", Arrays.asList(new String[]{"L"})));//用第2种算法这个通不过。好奇怪
        System.out.println(b.wordBreak("L", Arrays.asList(new String[]{"L"})));
    }

    public boolean wordBreak(String s, List<String> wordDict){
        return backtrack(s,new HashSet<>(wordDict),0);
    }

    public boolean backtrack(String s, HashSet<String> set, int start){
        if(start == s.length()) return true;
        for(int i = start+1; i<=s.length(); i++){
            if(set.contains(s.substring(start,i))&&backtrack(s,set,i)) return true;
        }
        return false;
    }


    /******************************************************************************************************************/
    public boolean wordBreakII(String s, List<String> wordDict){
        return dfs(s,new HashSet<>(wordDict),0);
    }

    public boolean dfs(String s, HashSet<String> set, int start){
        if(start == s.length()) return true;
        for(int i = start+ 1; i<=s.length();i++){
            String prefix = s.substring(start,i);
            if(set.contains(prefix)){
                String suffix = s.substring(i);
                if(set.contains(suffix)||dfs(suffix,set,i)) return true;
            }
        }
        return false;
    }

/******************************************* dp 可遇不可求，能记就记， dfs 才是王道*******************************************/
    public boolean wordBreakIII(String s, List<String> wordDict){
        boolean[] dp = new boolean[s.length()+1];
        HashSet<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        for(int i = 1; i<=s.length(); i++){
            for(int j = 0; j<i; j++){
                if(dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
