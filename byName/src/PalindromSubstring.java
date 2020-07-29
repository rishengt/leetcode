/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class PalindromSubstring {
    public static void main(String[] args) {
        System.out.println(new PalindromSubstring().longestPalindrome("babad"));
        System.out.println(new PalindromSubstring().longestPalindrome("cbbd"));
        System.out.println(new PalindromSubstring().longestPalindrome("d"));
    }

    public String longestPalindrome(String s){
        boolean[][] dp = new boolean[s.length()][s.length()];
        int compare = Integer.MIN_VALUE;
        String ans="";
        for(int i = 0; i<s.length();i++){
            for(int j = 0; j<=i; j++){
                dp[j][i] = s.charAt(i)==s.charAt(j)&&(i-j<=2||dp[j+1][i-1]);
                if(dp[j][i]){
                    if(i-j+1>compare){
                        compare = Math.max(compare,i-j+1);
                        ans = s.substring(j,i+1);
                    }
                }
            }
        }
        return ans;
    }
}
