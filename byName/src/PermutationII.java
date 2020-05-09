import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */

public class PermutationII {

    public static void main(String[] args) {
        List<List<Integer>> ans = new PermutationII().permuteUnique(new int[]{1,1,2});
        System.out.println(ans);
    }
    public List<List<Integer>> permuteUnique(int[] nums){
        Arrays.sort(nums);//排序然后去重
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, nums, new ArrayList<Integer>(), new ArrayList<Integer>());
        return ans;
    }

    public void backtrack(List<List<Integer>> ans, int[] nums, List<Integer> old, List<Integer> temp){
        if(temp.size() == nums.length){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 0; i< nums.length; i++){
            if(old.contains(i)) continue;
            if(i>0&&!old.contains(i-1)&&nums[i-1] == nums[i]) continue;/**虽不能理解但是要记住啊！！！！！**/
            old.add(i);
            temp.add(nums[i]);
            backtrack(ans,nums,old,temp);
            old.remove(old.size()-1);
            temp.remove(temp.size()-1);
        }
    }
}
