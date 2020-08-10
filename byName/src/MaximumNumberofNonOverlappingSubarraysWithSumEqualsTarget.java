import java.util.*;
/**
 * Given an array nums and an integer target.
 *
 * Return the maximum number of non-empty non-overlapping subarrays such that the sum of values in each subarray is equal to target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], target = 2
 * Output: 2
 * Explanation: There are 2 non-overlapping subarrays [1,1,1,1,1] with sum equals to target(2).
 * Example 2:
 *
 * Input: nums = [-1,3,5,1,4,2,-9], target = 6
 * Output: 2
 * Explanation: There are 3 subarrays with sum equal to 6.
 * ([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping.
 * Example 3:
 *
 * Input: nums = [-2,6,6,3,5,4,1,2,8], target = 10
 * Output: 3
 * Example 4:
 *
 * Input: nums = [0,0,0], target = 0
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 0 <= target <= 10^6
 */
public class MaximumNumberofNonOverlappingSubarraysWithSumEqualsTarget {
    /** prefix sum 跟 hashmap的问题， 浓浓的一股会考的气息啊！！！！！*/
    /**顺便一题，PathSumIII的最优解也是用的这种思路，你小子还没去好好研究呢。。。。。。*/

    public static void main(String[] args) {
        System.out.println(new MaximumNumberofNonOverlappingSubarraysWithSumEqualsTarget().maxNonOverlapping(new int[]{1,1,1,1,1},2));
    }

    /**
     * Steps -
     * We follow greedy approach here.
     *
     * If we find a subarray with target sum, ending at index i, we count that subarray greedily. And we update last index to i.
     * Next time, we make sure that we count subarray only when it starts after last index.
     * Approach
     * We store all the sums seen so far in hashmap with key = sum, value = end index of subarray with that sum.
     * Example -
     * [1,1,1,1,1], target sum = 2
     * 1.index = 0, hashmap[1 -> 0] sum 1 at index 0
     * 2.index = 1, hashmap [1 -> 0, 2 -> 1] sum 2 at index 1
     * We find that there is subarray with sum 2 ending at index 1. Hence we increment count and set lastIndex = 1
     * index = 2, hashmap [1 -> 0, 2 -> 1, 3 -> 2] sum 3 at index 2
     * We again find there is subarray with sum 3.
     * But the start index is 0 {from hashmap}. So we cannot consider this sum, since our lastIndex is already 1.
     * Which means that we have already counted subarray ending at index 1 and cannot again count subarray starting at index 0.
     * Also, another trick is that , we always consider smallest subarray with a particular sum. map.put(sum, i)
     * This will override the a particular sum, if it already exists in map, so that we always consider the smallest subarray
     */
    public int maxNonOverlapping(int[] nums, int target) {
        int sum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int lastIndex =-1;
        for (int i = 0; i<nums.length; i++) {
            // calculate cumulative sum
            sum += nums[i];
            /**这个if说实话我理解不了。。这里理解不了这道题就等于白刷*/
            /**有一点点理解了，因为我们保存的是由开始到current index的prefix sum，然后当你 current prefixSum - 以前保存过的prefix sum == target的时候，
             * 证明你这一小段的所有数加起来 = target， 下面的式子只是变换了一下位置。
             */
            /**比如有一段由j到i的subarray每个数加起来等于target，可以看成是PrefixSum【0。。。i】-PrefixSum【0。。j】 = target
             * 也就是说prefixSum【0。。i】（sum） - target = prefixSum【0。。。j】；
             * 如果我们的map保存过prefixSum【0。。j】并且那段东西的结尾不比最新的边界小，那么就是一段不重复又合格的subarray出现了，count++，更新边界
             */
            if (map.containsKey(sum - target) && map.get(sum-target)>=lastIndex){
                count++;
                lastIndex = i;
            }
            map.put(sum, i);
        }
        return count;

    }
}
