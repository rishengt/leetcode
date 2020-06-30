import java.util.HashMap;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 *
 * A subsequence of a string is a new string generated from the original string with some characters(can be none)
 * deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not).
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * If there is no common subsequence, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 */
public class LongestCommonSebsequence {
    public static void main(String[] args) {
        System.out.println(new LongestCommonSebsequence().longestCommonSubsequence("abcde", "ace"));
        System.out.println(new LongestCommonSebsequence().dp("abcde", "ace"));
    }
    public int longestCommonSubsequence(String text1, String text2) {
        return recursion(text1, text2, 0, 0);
    }
    public int recursion(String text1, String text2, int p, int q){
        if(p==text1.length()||q==text2.length()) return 0;
        if(text1.charAt(p) == text2.charAt(q)) return 1+recursion(text1, text2, p+1, q+1);
        else{
            return Math.max(recursion(text1,text2,p+1,q), recursion(text1,text2, p, q+1));
        }
    }


    public int dp(String text1, String text2){
        if(text1.length() == 0 || text2.length()== 0) return 0;
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i = text1.length()-1; i>= 0; i--){
            for(int j = text2.length()-1; j>=0; j--){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i+1][j+1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }
}
