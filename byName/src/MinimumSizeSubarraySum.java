/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s.
 * If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
import java.util.*;
public class MinimumSizeSubarraySum {

    /****虽然这道题很想用nonoverlap的那招prefixSum（0。。。。j）-target = prefixSum（0，，i）
     *但是！！！ 题目这里是大于等于，而不是仅仅的等于，所以，并不能够使用。。。。
     */

    /**别急，还有一招吞吐大法。。细品*/
    /**这招感觉就超级像那个柱子装水啊！！！！！！Trapping Rain Drop*/
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int leftBound = 0;
        int prefixSum = 0;
        for(int i = 0 ; i< nums.length; i++){
            prefixSum+=nums[i];
            while(prefixSum>=s){
                min = Math.min(min, i-leftBound+1);
                prefixSum -= nums[leftBound];
                leftBound++;
            }
        }
        return (min == Integer.MAX_VALUE?0:min);
    }
}
