import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */

public class CombinationSumII {
    public static void main(String[] args) {
        System.out.println(new CombinationSumII().combine(new int[]{10,1,2,7,6,1,5}, 8));
    }

    public List<List<Integer>> combine(int[] candidates, int target){
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(ans, new ArrayList<>(), candidates, target, 0);
        return ans;
    }

    public void backtrack(List<List<Integer>> ans, List<Integer> temp, int[] nums, int target, int start){
        if(target<0) return;
        if(target == 0) {ans.add(new ArrayList<>(temp)); return;}
        for(int i = start; i<nums.length; i++){
            {
                if(i>start&&nums[i] == nums[i-1]) continue;//先排序再去重！！！！
                temp.add(nums[i]);
                backtrack(ans, temp, nums, target - nums[i], i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
