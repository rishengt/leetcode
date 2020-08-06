/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 * Follow up:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
public class FirstMissingPositive {
    /**我只能说，这种骚操作是真尼玛骚，过个几天就想不起来了吧。。。。*/
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        /**这一步把所有nums的数都变成正数*/
        for(int i = 0; i<nums.length; i++){
            if(nums[i] > n || nums[i] <= 0){ /**这里小于等于0其实很精髓*/
                nums[i] = n+1;
            }
        }
        for(int i = 0; i<n; i++){
            int index = Math.abs(nums[i]) - 1;
            if(index >= n) continue; /**index大于等于n， 代表那个index不在【1 - n】里*/
            else if(nums[index] >0) nums[index] *= -1; /**把所有nums在【1-n】的index的位置变负数*/
        }

        for(int i = 0; i<n; i++){
            if(nums[i] >0) return i+1; /**找到的第一个就是他missing的最小的positive number；*/
        }
        return n+1; /**要是全部都是负数，代表每一个index都在【1-n】并且在nums里出现，那么最小的missing positive就是 n+1*/
    }
}
