/**
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 *
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string.
 * However, you must split the substrings such that all of them are unique.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc'].
 * Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 * Example 2:
 *
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 * Example 3:
 *
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 *
 * s contains only lower case English letters.
 */
import java.util.*;
public class SplitaStringIntotheMaxNumberofUniqueSubstrings {
    public int maxUniqueSplit(String s) {
        return dfs(s,new HashSet<>());
    }

    /**這是一個比較巧妙的dfs，截取String的時候往往可以把substring的一段給傳進去從而使推進過程巧妙起來**/
    public int dfs(String s, Set<String> set){
        /**大概你要返回數據類型的時候，那個數據你要自己在dfs裏面聲明啊**/
        int max = 0;
        for(int i = 1; i<=s.length(); i++){
            String sub = s.substring(0,i);
            if(!set.contains(sub)){
                set.add(sub);
                /**這裏的推進過程就很美妙了，你個菜鷄啥時候才能自己想出來。。。**/
                max = Math.max(max,1+dfs(s.substring(i),set));
                set.remove(sub);
            }
        }
        /**需要什麽返回什麽，這是定式*/
        return max;
    }

    public int dfs(String s, Set<String> set, int idx){
        /**這個可能也是一種定式，超標的時候代表最底層，那就返回0，如果是加法的話就正確，但是如果是除法或者乘法呢？？*/
        if(idx >= s.length()) return 0;
        int max = -1;
        for(int i = idx+1; i<=s.length(); i++){
            String sub = s.substring(idx,i);
            if(set.contains(sub)) continue;
            set.add(sub);
            max = Math.max(max,1+dfs(s,set,i));
            set.remove(sub);
        }
        return max;
    }
}
