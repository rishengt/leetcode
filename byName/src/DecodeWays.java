/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 *
 *
 * The basic concept is to build up the number of ways to get to state i from all the previous states less than i.
 * We can do this by initializing a cache with a size of s.length() + 1.
 * We always set waysToDecode[0] to 1 because there is only 1 way to decode an empty string.
 * We can then build up the number of ways to decode starting from the first value and work our way to the end.
 *
 * We only ever need to look at the character at i - 1 because we can't have values greater than 26, so three digits is never possible.
 * There are four possibilities to consider:
 * 1) The previous value is 0 and the current value is 0, we can't make progress, return 0.
 * 2) The current value is 0, we have to use the previous value, if it is greater than 2, we can't make progress, return 0,
 * otherwise we have to transition to this state from waysToDecode[i - 1].
 * 3) The previous value is 0, we can't use the previous, so the only way to transition to the current state is from the previous, so use waysToDecode[i].
 * 4) lastly, both previous and curr can be used so there are two ways to transition to the current state, thus at waysToDecode[i + 1]
 * we can get here by using all the ways we can get to waysToDecode[i] + all the ways to get to waysToDecode[i - 1].
 *
 * Keep in mind that the indices are adjusted for the cache because its size differs from the string size.
 */

public class DecodeWays {/**早点刷亚麻是不是有了。。。。。。跟爬楼梯很像，思路，但爬楼梯你都做不出来啦菜🐔，还亚麻，吃屎啦你*/

    public static void main(String[] args) {
        System.out.println(new DecodeWays().waysToDecode("226"));
        System.out.println(new DecodeWays().waysToDecode("12"));
    }
    public int waysToDecode(String s){
        if(s.isEmpty() || s.charAt(0) == '0') return 0; /**审题，有意义的数字只有1-26，要是一开始就给你个0，不用玩了, 但是！！要是数字中间有0就另说了*/
        int[] dp = new int[s.length()+1];
        dp[0] = 1; /**空字符串，只能是一种decode方法，，，说是这么说，强行给basecase而已*/
        dp[1] = 1; /**这个就真有逻辑了，你只有一个数字，玩出花也就一种decode方法*/
        for(int i = 1; i<s.length(); i++){
            int cur = s.charAt(i) -'0', pre = s.charAt(i-1)-'0';
            if(cur == 0 && pre == 0 || pre*10+cur>26) return 0; /**00 or 30, you can't make a way to decode, just return 0,反正就是前后两个数都用不上*/
            else if(pre == 0 || pre*10+cur>26) {dp[i+1] = dp[i];} /**前面一个数用不上，只能从当前的可能组合向前迈进*/
            else if(cur == 0) {dp[i+1] = dp[i-1];}/**现在的数用不上，只能从更早的可能性进化*/
            else {dp[i+1] = dp[i] + dp[i-1];}/**两个数都能用，加一起*/
        }
        return dp[s.length()];
    }
}
