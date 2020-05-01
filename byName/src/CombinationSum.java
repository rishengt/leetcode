import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum {
    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{2,3,6,7}, 7));
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans,new ArrayList<>(), candidates, target, 0);
        return ans;
    }
    public void backtrack(List<List<Integer>> ans, List<Integer> temp, int[] candidates, int target, int start){/**这个start为何如此关键？？*/
        if(target<0) return;/**这列妈的也超级重要*/
        if(target == 0) ans.add(new ArrayList<>(temp));
        for(int i = start; i<candidates.length; i++){
            //target -= candidates[i]; //不能这样写因为target是全局变量一旦变了就return不回去了。。。。。
            temp.add(candidates[i]);
            backtrack(ans,temp,candidates,target-candidates[i]/**这样写能把target变成局部变量*/, start/**这里给start永远从0 index开始遍历，
             反观下一题传了i+1，就是从下一个index开始遍历*/);
            temp.remove(temp.size()-1);
        }
    }
}
