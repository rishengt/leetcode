/**
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * Example 2:
 *
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 * Example 3:
 *
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 * Example 4:
 *
 * Input: nums = [-1,2]
 * Output: 1
 * Example 5:
 *
 * Input: nums = [1,2,3,5,-6,4,0,10]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MaximumLengthofSubarrayWithPositiveProduct {
    public static void main(String[] args) {
        System.out.println(new MaximumLengthofSubarrayWithPositiveProduct().getMaxLen(new int[]{-1,-2}));
    }
    /**下面那个应该是最优解，空间复杂度最小，但是真的很难领悟跟记住，还是递推dp好记，想想看这是属于什么dp*/
    /**不属于Guan神总结的那些dp套路但应该可以看成是线性dp吧***/
    /**我们定义一个f(i) 表示第i位的时候最长的正subarray， g(i)表示第i位时最长的负subarray
     * 1。nums[i] > 0 f(i) = f(i-1)+1 and g(i) = g(i-1) + 1;
     * 2. nums[i] = 0 f(i) = 0; g(i) = 0;
     * 3. nums[i] <0 f(i) = g(i-1)+1; g(i) = f(i-1)+1;
     */
    public int getMaxLen(int[] nums){
        int dp1[] = new int[nums.length];
        int dp2[] = new int[nums.length];
        if(nums[0] > 0) dp1[0] = 1;
        else if(nums[0]<0) dp2[0] = 1;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i<nums.length; i++){
            if(nums[i]>0){
                dp1[i] = dp1[i-1]+1;
                if(dp2[i-1]>0) dp2[i] = dp2[i-1]+1;/**这个应该是特殊情况，你前面压根没有负的那你永远变不成负的，所以要先判断你前面有没有负数*/
            }
            else if(nums[i]<0){
                dp2[i] = dp1[i-1] + 1;
                if(dp2[i-1]>0) dp1[i] = dp2[i-1]+1;/**同理。。。*/
            }
            max = Math.max(dp1[i],max);
        }
        return max;
    }


    public int getMaxLenII(int[] nums) {
        // sum is used to count the number of negative numbers from zeroPosition to current index
        int firstNegative = -1, zeroPosition = -1, sum = 0, max = 0;
        for(int i = 0;i < nums.length; i++){
            if(nums[i] < 0){
                sum++;
                // we only need to know index of first negative number
                if(firstNegative == -1) firstNegative = i;
            }
            // if current number is 0, we can't use any element from index 0 to i anymore, so update zeroPosition, and reset sum and firstNegative. If it is a game, we should refresh the game when we meet 0.
            if(nums[i] == 0){
                sum = 0;
                firstNegative = -1;
                zeroPosition = i;
            }
            else{
                // consider index of zero
                if(sum%2 == 0) max = Math.max(i - zeroPosition, max);
                    // consider index of first negative number
                else max = Math.max(i - firstNegative, max);
            }
        }
        return max;
    }
}
