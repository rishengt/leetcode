import java.util.Arrays;

/**
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 *
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 * Example 2:
 *
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 * Example 3:
 *
 * Input: nums = [6,6,0,1,1,4,6]
 * Output: 2
 * Example 4:
 *
 * Input: nums = [1,5,6,14,15]
 * Output: 1
 */
public class MinimumDifferenceBetweenLargestandSmallestValueinThreeMoves {
    /**
     * We have 4 plans:
     *
     * kill 3 biggest elements
     * kill 2 biggest elements + 1 smallest elements
     * kill 1 biggest elements + 2 smallest elements
     * kill 3 smallest elements
     *
     * Example from @himanshusingh11:
     *
     * A = [1,5,6,13,14,15,16,17]
     * n = 8
     *
     * Case 1: kill 3 biggest elements
     *
     * All three biggest elements can be replaced with 14
     * [1,5,6,13,14,15,16,17] -> [1,5,6,13,14,14,14,14] == can be written as A[n-4] - A[0] == (14-1 = 13)
     *
     * Case 2: kill 2 biggest elements + 1 smallest elements
     *
     * [1,5,6,13,14,15,16,17] -> [5,5,6,13,14,15,15,15] == can be written as A[n-3] - A[1] == (15-5 = 10)
     *
     * Case 3: kill 1 biggest elements + 2 smallest elements
     *
     * [1,5,6,13,14,15,16,17] -> [6,6,6,13,14,15,16,16] == can be written as A[n-2] - A[2] == (16-6 = 10)
     *
     * Case 4: kill 3 smallest elements
     *
     * [1,5,6,13,14,15,16,17] -> [13,13,13,13,14,15,16,17] == can be written as A[n-1] - A[3] == (17-13 = 4)
     *
     * Answer is minimum of all these cases!
     * @param nums
     * @return
     */
    public int minDifference(int[] nums) {
        if(nums.length<=4) return 0;
        int n = nums.length;
        Arrays.sort(nums);
        /**
         * for (int i = 0; i < 4; ++i) {
         *             res = Math.min(res, A[n - 4 + i] - A[i]);  /这种写法你不觉得跟那个抽卡的很像吗，index都快被你们玩坏啦喂！！
         *         }
         */
        return Math.min(nums[n-4]-nums[0], Math.min(nums[n-3]-nums[1],Math.min(nums[n-2]-nums[2],nums[n-1]-nums[3])));
    }
}
