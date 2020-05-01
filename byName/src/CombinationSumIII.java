import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 *
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

public class CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(new CombinationSumIII().CombinationSum3(3,7));
        System.out.println(new CombinationSumIII().CombinationSum3(3,9));
    }

    public List<List<Integer>> CombinationSum3(int k, int n){
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans,new ArrayList<>(),k,n,0,new int[]{1,2,3,4,5,6,7,8,9});
        return ans;
    }

    public void backtrack(List<List<Integer>> ans, List<Integer> temp, int k, int n, int start, int[] nums) {
        if(n<0) return;/**这里淘汰不合格的选项并返回上一层。贼关键！！！！！*/
        if (temp.size() == k && n == 0) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i< nums.length; i++){
            if(i>0&&nums[i]==nums[i-1]){ /**用这招记得先排序啊杀卵！！！！*/
                continue;//去重
            }
            temp.add(nums[i]);
            backtrack(ans,temp,k,n-nums[i],i+1,nums);
            temp.remove(temp.size()-1);
        }
    }
}
