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
        if(s==null || s.length() == 0) return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int right = 0;
        int left = 0;
        for(int j = 1; j<s.length();j++){
            for(int i = 0; i<j; i++){
                if(s.charAt(i)==s.charAt(j)&&(dp[i+1][j-1]||j-i<=2)){
                    dp[i][j] = true;
                    if(j-i>left-right){
                        right = j;
                        left = i;
                    }
                }
            }
        }
        return s.substring(left,right+1);
    }
}
