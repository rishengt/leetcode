/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{2,3,-2,4}));
        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{-2,0,-1}));
        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{-2,3,-4}));
        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{-2,-3,-4}));
    }
    public int maxProduct(int[] nums) {
        int cur = nums[0];
        int max = nums[0];
        int min = nums[0];
        for(int i = 1; i<nums.length; i++){
            int newCur = Math.max(nums[i], Math.max(cur*nums[i], min*nums[i]));
            int newMin = Math.min(nums[i], Math.min(cur*nums[i],min*nums[i]));
            cur = newCur;
            min = newMin;
            max = Math.max(max,newCur);
        }
        return max;
    }
}
