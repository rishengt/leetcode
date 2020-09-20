/**
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
 *
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 *
 * A subarray is defined as a contiguous block of elements in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
 * Example 2:
 *
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 * Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
 * Example 3:
 *
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
 * Example 4:
 *
 * Input: nums = [1,2,3], p = 7
 * Output: -1
 * Explanation: There is no way to remove a subarray in order to get a sum divisible by 7.
 * Example 5:
 *
 * Input: nums = [1000000000,1000000000,1000000000], p = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= p <= 109
 */
import java.util.*;
public class MakeSumDivisiblebyP {
    public static void main(String[] args) {
        System.out.println(new MakeSumDivisiblebyP().minSubarray(new int[]{6,3,7,5,2,9},9));
    }
    /**
     * We first compute modulo mod of the sum for the entire array. This modulo should be zero for the array to be divisible by p.
     * To make it so, we need to remove a subarray, which sum modulo is a compliment comp to mod.
     *
     * We use a hash map, where we track the running sum modulo, and the most recent position for that modulo.
     * As we go through the array, we look up for a comp modulo in that hash map, and track the minimum window size.
     */

    /**
     * After getting the sum of the entire array % p. This is the new target to find in a subarray.
     * You can now think of the question as regular subarray target finding problem.
     * Maybe this is obvious, but took me a while...
     *
     * Key calculation: long key = (preSum - remainder + p) % p;
     * The current sum - our target would be the target in a normal prefix sum problem. But we have modulus, so we need to offset by p.
     * Let says P = 2000, target = 1000, and current sum = 600; This current sum could be 2600, 4600.....etc. Key = 600 - 1000 + 2000 = 1600.
     * If we found some prefix of 1600, then the rest of the array would sum to 1000, which is our target
     */
    public int minSubarray(int[] nums, int p) {
        int mod = 0, r_mod = 0, min_w = nums.length;
        for (int n : nums)
            mod = (mod + n) % p;
        if (mod == 0)
            return 0;
        Map<Integer, Integer> pos = new HashMap<>();
        pos.put(0, -1);
        for (int i = 0; i < nums.length; ++i) {
            r_mod = (r_mod + nums[i]) % p;
            /**整題的精華。。。。看不懂**/
            int comp = (r_mod-mod + p) % p;
            if (pos.containsKey(comp))
                min_w = Math.min(min_w, i - pos.get(comp));
            pos.put(r_mod, i);
        }
        return min_w >= nums.length ? -1 : min_w;
    }
}