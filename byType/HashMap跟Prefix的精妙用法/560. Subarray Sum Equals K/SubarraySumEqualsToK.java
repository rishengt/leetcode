import java.util.*;
/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 *
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 *
 *
 * Constraints:
 *
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class SubarraySumEqualsToK {
    public static void main(String[] args) {
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{-1,65,4,-7,3,2,1},2));
    }
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int ans = 0;
        /**想通思路以后这一行代码就关键到炸裂了，防止[1,-1]这种少算一次的*/
        map.put(0,1);
        for(int i = 0; i<nums.length; i++){
            sum+=nums[i];
            int temp = sum-k;
            if(map.containsKey(temp)){
                ans+=map.get(temp);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return ans;
    }
}