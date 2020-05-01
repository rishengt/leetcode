import java.util.ArrayList;
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

    public List<List<Integer>> permute(int[] nums){
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
