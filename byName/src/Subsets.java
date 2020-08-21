/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
import java.util.*;
public class Subsets {
    public static void main(String[] args) {
        System.out.println(new Subsets().subsets(new int[]{1,2,3}));
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans,new ArrayList<>(),0,nums);
        return ans;
    }
    public void backtrack(List<List<Integer>> ans , List<Integer> temp, int start, int[] nums){
        ans.add(new ArrayList<>(temp));
        for(int i = start; i<nums.length; i++){
            temp.add(nums[i]);
            backtrack(ans,temp,i+1,nums);
            temp.remove(temp.size()-1);
        }
    }
}
