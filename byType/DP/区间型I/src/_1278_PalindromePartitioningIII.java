import java.util.Arrays;

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
    public static void main(String[] args) {
        System.out.println(new _1278_PalindromePartitioningIII().palindromePartition("abc",2));
        System.out.println(new _1278_PalindromePartitioningIII().palindromePartition("aabbc",3));
        System.out.println(new _1278_PalindromePartitioningIII().palindromePartition("leetcode",8));
    }
    /**照抄套路， dp[i][k] => 最小的字符变动，使得字符串S[1:i]能够恰能分成k个子串，且每串都是回文串, 这里是1 indexed，所以S[1:i]就是全部S哦*/
    /**第一层循环遍历i**/
    /**第二层循环遍历k**/
    /**第三层循环寻找最优的位置j作为最后一个分区的起始位置**/
    /**将dp[i][k]分割成dp[j-1][k-1]和s[j:i]求解**/
    public int palindromePartition(String s, int K) {
        int n = s.length();
        s = "#" + s;
        int[][] dp = new int[n+1][K+1];
        for(int i = 0; i<dp.length; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE/2);
        }
        dp[0][0] = 0;
        for(int i = 1; i<=n; i++){
            /**这里的min其实挺好理解的，因为你i代表截取的部分，把你每个部分都分了都不够k的时候，自然最多能分到的就是i份啊，k。。还用说吗？？*/
            for(int k = 1; k<=Math.min(i,K); k++){
                /**这里因为要找最后一个分区的起始点，你j代表的是每个分区的起点，我l不可能比你j还小吧。。。。*/
                for(int j = k; j<=i; j++){
                    /**上面的1 index 就是为了方便这里 dp[l-1][j-1] 不越界。。。秒啊！！！！*/
                    dp[i][k] = Math.min(dp[i][k],dp[j-1][k-1] + helper(s,j,i));
                }
            }
        }
        return dp[n][K];
    }
    /**[xxxxxxxxx]
     *        j i
     */
    /** a helper function which returns the minimum number of moves that require to make a string to palindrome*/
    public int helper(String s, int left, int right){
        int count = 0;
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                count++;
            }
            left++;
            right--;
        }
        return count;
    }
}
