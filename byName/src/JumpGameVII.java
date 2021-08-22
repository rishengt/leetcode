/**
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation:
 * In the first step, move from index 0 to index 3.
 * In the second step, move from index 3 to index 5.
 * Example 2:
 *
 * Input: s = "01101110", minJump = 2, maxJump = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 105
 * s[i] is either '0' or '1'.
 * s[0] == '0'
 * 1 <= minJump <= maxJump < s.length
 */
public class JumpGameVII {
    public boolean canReach(String s, int minJump, int maxJump) {
        boolean[] dp = new boolean[s.length()];
        dp[0] = true;
        int canJump = 0;
        for(int i = 1; i<s.length(); i++){
            if(i>=minJump&&dp[i-minJump]){
                canJump++;
            }
            if(i>maxJump&&dp[i-maxJump-1]){
                canJump--;
            }
            dp[i] = canJump>0&&s.charAt(i) == '0';
        }
        return dp[s.length()-1];
    }
}
