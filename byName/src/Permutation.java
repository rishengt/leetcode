import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */

public class Permutation {

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<Integer>(), new boolean[nums.length], nums);
        return ans;
    }
    public void backtrack(List<List<Integer>> ans, List<Integer> temp, boolean[] used, int[] nums){
        if(temp.size() == nums.length) {ans.add(new ArrayList<Integer>(temp)); return;}
        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;
            used[i] = true;
            temp.add(nums[i]);
            backtrack(ans,temp,used,nums);
            used[i] = false;
            temp.remove(temp.size()-1);
        }
    }

    public List<List<Integer>> permuteII(int[] nums){
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans,nums,new ArrayList<Integer>());
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, int[] nums, List<Integer> integers) {
        if(integers.size() == nums.length){
            ans.add(new ArrayList<>(integers));/**至关重要，一定要instantiate， 不然integers里面是空的*/
            return;
        }

        for(int i = 0; i< nums.length; i++){
            if(integers.contains(nums[i])) continue;
            integers.add(nums[i]);
            backtrack(ans,nums,integers);
            integers.remove(integers.size()-1);
        }
    }


}
