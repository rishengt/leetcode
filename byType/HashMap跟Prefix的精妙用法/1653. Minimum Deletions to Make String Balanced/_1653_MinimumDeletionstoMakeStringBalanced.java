/**
 * You are given a string s consisting only of characters 'a' and 'b'​​​​.
 *
 * You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 *
 * Return the minimum number of deletions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 * Example 2:
 *
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is 'a' or 'b'​​.
 */
public class _1653_MinimumDeletionstoMakeStringBalanced {
    public int minimumDeletions(String s) {
        int[] s1 = new int[s.length()+1];
        int[] s2 = new int[s.length()+1];
        for(int i = 1; i<=s.length(); i++){
            s1[i] = s1[i-1];
            s2[i] = s2[i-1];
            if(s.charAt(i-1) == 'a'){
                s1[i]++;
            }else{
                s2[i]++;
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i<=s.length(); i++){
            res = Math.min(res, s2[i]+s1[s.length()]-s1[i]);
        }
        return res;
    }
}
