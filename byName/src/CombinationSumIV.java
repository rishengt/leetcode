import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 *
 * Therefore the output is 7.
 *
 *
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */

public class CombinationSumIV {
    public static void main(String[] args) {
        System.out.println(new CombinationSumIV().totalpossible(new int[]{1,2,3}, 4));
    }
    public int totalpossible(int[] nums, int target){
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), nums, target,0);
        return ans.size();
    }

    public void backtrack(List<List<Integer>> ans, List<Integer> temp, int[] nums, int target,int start){
        if(target<0) return;
        if(target==0) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i<nums.length; i++){
            temp.add(nums[i]);
            backtrack(ans,temp,nums,target-nums[i], start);
            temp.remove(temp.size()-1);
        }
    }
}
