import java.util.HashMap;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 *
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * 样例
 * Example1
 *
 * Input:  nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation:
 * because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * Example2
 *
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2
 * Explanation:
 * because the subarray [-1, 2] sums to 1 and is the longest.
 */
public class MaximumSizeSubarraySumEqualsk {
    public static void main(String[] args) {
        System.out.println(new MaximumSizeSubarraySumEqualsk().maxSubArrayLen(new int[]{1, -1, 5, -2, 3},3));
        System.out.println(new MaximumSizeSubarraySumEqualsk().maxSubArrayLen(new int[]{-2, -1, 2, 1},1));
        System.out.println(new MaximumSizeSubarraySumEqualsk().maxSubArrayLen(new int[]{1, -1, 1, -1},0));
    }
    public int maxSubArrayLen(int[] nums, int k) {
        int max = 0, prefix = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i = 0; i<nums.length; i++){
            prefix+=nums[i];
            if(map.containsKey(prefix-k)){
                max = Math.max(max,i-map.get(prefix-k));
            }
            if(!map.containsKey(prefix)){
                map.put(prefix,i);
            }
        }
        return max;
    }
}
