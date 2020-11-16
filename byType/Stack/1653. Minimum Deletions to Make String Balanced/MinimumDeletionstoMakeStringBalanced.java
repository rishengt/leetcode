import java.util.*;
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
public class MinimumDeletionstoMakeStringBalanced {
    public static void main(String[] args) {
        System.out.println(new MinimumDeletionstoMakeStringBalanced().minimumDeletions("aababba"));
    }

    public int minimumDeletions(String s) {
        Stack<Character> st = new Stack<>();
        int n = s.length();
        int res = 0;
        for(int i = 0; i<n; i++){
            char c = s.charAt(i);
            if(!st.isEmpty() && st.peek() > c){
                res++;
                st.pop();
            }else{
                st.push(c);
            }
        }
        return res;
    }
    public int minimumDeletionsII(String s) {
        Stack<Character> st = new Stack<>();
        int n =s.length();
        int res = 0;

        for(int i = n-1; i >=0; i--){
            char c = s.charAt(i);
            if(!st.isEmpty() && st.peek() < c){
                res++;
                st.pop();
            }else{
                st.push(c);
            }
        }

        return res;
    }

}
