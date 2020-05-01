import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [3,2,4], target = 6,
 *
 * Because nums[1] + nums[2] = 2 + 4 = 6, because cannot use the same element twice so cannot return [0,0];
 * return [1, 2].
 *
 * Given nums = [3,3], target = 6,
 * return [0,1];
 */

public class TwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] ans = twoSum.twoSum(new int[]{3,3},6);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
    public int[] twoSum(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++){
            map.put(nums[i],i);
        }
        for(int i = 0; i<nums.length; i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)&&map.get(temp)!=i){
                return new int[] {map.get(temp), i};
            }
        }
        return new int[]{-1,-1};
    }

}
