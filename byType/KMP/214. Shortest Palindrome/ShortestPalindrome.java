import java.util.*;
/**
 * Given a string s, you can convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: "dcbabcd"
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of lowercase English letters only.
 */
public class ShortestPalindrome {
    public static void main(String[] args) {
        System.out.println(new ShortestPalindrome().shortestPalindrome("aabba"));
    }
    public String shortestPalindrome(String s) {
        String temp = new StringBuilder(s).reverse().toString();
        String newString = s + "#" + temp;
        int n = newString.length();
        int[] next = new int[n+1];
        for(int i = 1, j= 0; i<n; i++){
            while(j>0 && newString.charAt(i)!=newString.charAt(j)){
                j = next[j];
            }
            if(newString.charAt(i) == newString.charAt(j))j++;
            next[i+1] = j;
        }
        return temp.substring(0,s.length()-next[n])+s;
    }
}
