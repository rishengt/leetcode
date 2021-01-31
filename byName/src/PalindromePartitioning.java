import java.util.*;
/**
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        dfs(list,new ArrayList<String>(), s, 0);
        return list;
    }

    public void dfs(List<List<String>> ans, List<String> temp, String s, int start){
        if(start >= s.length()){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int end = start; end<s.length(); end++){
            if(isPalindrome(s.substring(start, end+1))){
                temp.add(s.substring(start,end+1));
                dfs(ans,temp,s,end+1);
                temp.remove(temp.size()-1);
            }
        }
    }

    public boolean isPalindrome(String s){
        int i = 0, j = s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
