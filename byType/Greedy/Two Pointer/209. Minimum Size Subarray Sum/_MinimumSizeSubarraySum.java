/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class _MinimumSizeSubarraySum {
    public static void main(String[] args) {
        System.out.println(new _MinimumSizeSubarraySum().minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
    public int minSubArrayLen(int s, int[] nums) {
        int ans = Integer.MAX_VALUE, sum = 0, i = 0, j = 0;
        for(;j<nums.length; j++){
            sum+=nums[j];
            while(sum>=s){
                ans = Math.min(ans,j-i+1);
                sum-=nums[i++];
            }
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }
}

