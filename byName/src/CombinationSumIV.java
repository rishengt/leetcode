import java.util.ArrayList;
import java.util.HashMap;
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
        System.out.println(new CombinationSumIV().combinationSum4(new int[]{4,2,1}, 32));
        System.out.println(new CombinationSumIV().combinationSumWithMemoi(new int[]{4,2,1}, 32));
        //System.out.println(new CombinationSumIV().totalpossible(new int[]{4,2,1}, 32));时间复杂度太高，这部PC跑都跑不出来
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
            backtrack(ans,temp,nums,target-nums[i], start/**其实你在这里传了start跟不设这个参数的效果是一样的，因为两种写法都是每次递归从nums【0】开始搞*/);
            temp.remove(temp.size()-1);
        }
    }

    /**********************可用memoization来做优化，跳过重复遍历, 大概用memorization优化都不能直接在void上改，而是要有一个返回值，不太行，以后多留意*/
    /*****友情提示，houseRobber你也抄了别人的memoization哦菜🐔*/
    public int combinationSumWithMemoi(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        return useMemoi(nums,target,map);
    }

    public int useMemoi(int[] nums, int target, HashMap<Integer, Integer> map){
        int count = 0;
        if(nums == null || nums.length == 0 || target<0) return 0;
        if(target == 0) return 1;/**递归出口*/
        if(map.containsKey(target)) return map.get(target); /**遇到碰到过的target就返回，少女口啊*/
        for(int num: nums){
            count += useMemoi(nums,target-num,map);/**灵魂递归公式*/
        }
        map.put(target,count);/**在递归过程中target会逐渐变小，所以把每个有可能出现的target的所有组合都存到memoization里*/
        return count;
    }


    /***************用回溯LC会超时，大概这类combination都有它们的dp解吧，说实话我这个dp看不太懂，目前先记memoization吧*/
    public int combinationSum4(int[] nums, int target){
        int[] comb = new int[target+1];
        comb[0] = 1;
        for(int i = 1; i<comb.length; i++){
            for(int j = 0; j< nums.length; j++){
                if(i-nums[j] >=0){
                    comb[i] += comb[i-nums[j]];
                }
            }
        }
        return comb[target];
    }
}
