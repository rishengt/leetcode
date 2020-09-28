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

import javax.crypto.spec.PSource;
import java.util.HashSet;
import java.util.Set;

public class _1593SplitaStringIntotheMaxNumberofUniqueSubstrings {
    public static void main(String[] args) {
        System.out.println(new _1593SplitaStringIntotheMaxNumberofUniqueSubstrings().maxUniqueSplit("ababccc"));
    }
    public int maxUniqueSplit(String s){
        return dfs(s,0,new HashSet<String>());
    }

    public int dfs(String s, int start, HashSet<String> set){
        int max = 0;
        for(int i = start+1; i<=s.length(); i++){
            if(!set.contains(s.substring(start,i))){
                set.add(s.substring(start,i));
                max = Math.max(max,1+dfs(s.substring(i),i+1,set));
                set.remove(s.substring(start,i));
            }
        }
        return max;
    }
}
