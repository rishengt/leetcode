import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence_N2_DP {
    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubsequence_N2_DP().LIS(new int[]{10,9,2,5,3,7,101,18}));
    }
    public int LIS(int[] nums){
        int[] dp = new int[nums.length+1];
        Arrays.fill(dp,1);
        int ans = 1;
        for(int i = 0; i<nums.length; i++){
            for(int j = 0; j<i; j++){
                if(nums[j]>nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                    ans = Math.max(dp[i],ans);
                }
            }
        }
        return ans;
    }
}
