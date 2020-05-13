import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {
    public static void main(String[] args) {
        System.out.println(new HouseRobber().robHouse(new int[]{1,2,3,1}));
        System.out.println(new HouseRobber().robHouse(new int[]{2,7,9,3,1}));
        System.out.println(new HouseRobber().robHouseII(new int[]{1,2,1,1}));
        System.out.println(new HouseRobber().robHouseII(new int[]{2,7,9,3,1}));
        System.out.println(new HouseRobber().robHouseIII(new int[]{1,2,3,1}));
        System.out.println(new HouseRobber().robHouseIII(new int[]{2,7,9,3,1}));
    }

    public int robHouse(int[] nums){
        return dfs(nums,0);
    }

    public int robHouseII(int[] nums){
        int[] memo = new int[nums.length];/**这里才是dp的精髓，把每一步的结果储存起来*/
        Arrays.fill(memo,-1);
        return dp(nums,0,memo);
    }

    public int robHouseIII(int[] nums){/**one pass, 究极dp*/
        int dp_i_1 = 0, dp_i_2 = 0, dp_i = 0;
        for(int i = nums.length-1; i>=0; i--){
            dp_i = Math.max(dp_i_1, nums[i]+dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

    public int dfs(int[] nums, int start){
        if(start>=nums.length) return 0;/**函数出口难寻，哎*/
        int res = Math.max(dfs(nums,start+1), nums[start]+dfs(nums,start+2));
        return res;
    }

    public int dp(int[] nums, int start, int[] memo){
        if(start>=nums.length) return 0;
        if(memo[start]!=-1) return memo[start];/**细品，返回储存结果，迅猛提升速度*/
        int ans = Math.max(dp(nums,start+1,memo), nums[start]+dp(nums,start+2,memo));
        memo[start] = ans;/**这了细啊，储存，dp！！！*/
        return ans;
    }
}
