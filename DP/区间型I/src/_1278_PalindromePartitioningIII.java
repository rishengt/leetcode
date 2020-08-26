/**
 * You are given a string s containing lowercase letters and an integer k. You need to :
 *
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 * Example 2:
 *
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 * Example 3:
 *
 * Input: s = "leetcode", k = 8
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= k <= s.length <= 100.
 * s only contains lowercase English letters.
 */
public class _1278_PalindromePartitioningIII {
    /**照抄套路， dp[i][k] => 最小的自负变动，使得字符串S[1:i]能够恰能分成k个子串，且每串都是回文串*/
    /**第一层循环遍历i**/
    /**第二层循环遍历k**/
    /**第三层循环寻找最优的位置j作为最后一个分区的起始位置**/
    /**将dp[i][k]分割成dp[j-1][k-1]和s[j:i]求解**/
    public int palindromePartition(String s, int k) {
        int[][] dp = new int[s.length()][k];

    }
}
