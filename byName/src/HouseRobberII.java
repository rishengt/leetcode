/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have security system connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobberII {
    public static void main(String[] args) {
        //System.out.println(new HouseRobberII().robHouse(new int[]{2,3,2}));
//        System.out.println(new HouseRobberII().robHouse(new int[]{1,2,1,1}));
        System.out.println(new HouseRobberII().robHouseII(new int[]{2,3,2}));
        System.out.println(new HouseRobberII().robHouseII(new int[]{1,2,3,1}));
        System.out.println(new HouseRobberII().robHouseII(new int[]{1,2}));
        System.out.println(new HouseRobberII().robHouseII(new int[]{3,2}));
        System.out.println(new HouseRobberII().robHouseII(new int[]{1}));
        System.out.println(new HouseRobberII().robHouseII(new int[]{1,2,1,1}));
    }
    public int robHouse(int[] nums){
        if(nums.length<2) return nums[0];
        return Math.max(onepass(nums,0,nums.length-2),onepass(nums,1,nums.length-1));
    }

    public int robHouseII(int[] nums){/****又有贪心的影子，区间判断很帅气，细品*****/
        if(nums.length<2) return nums[0];
//        int[] memo = new int[nums.length]; 不能只用一个数组以为多次调用方程会记录不对的值
//        Arrays.fill(memo,-1);
        return Math.max(dp(nums,0,nums.length-1,new int[nums.length]/**每次都传新的数组就不会因为上一次执行这个函数而污染了数组*/)
                ,dp(nums,1,nums.length,new int[nums.length]));
    }
/***************正向dp，凭记忆写的，过几天大概会忘吧***********/
/***************[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]会超时但是无伤大雅*****/
    public int dp(int[] nums, int start, int end, int[] memo){
        if(start>=end) return 0;
        if(memo[start]!=0) return memo[start];
        int ans = Math.max(nums[start]+dp(nums,start+2,end,memo), dp(nums,start+1,end,memo));
        memo[start] = ans;
        return ans;
    }

/*******one pass凭记忆都很容易犯错，但非常天才的解法哦*********/
    public int onepass(int[] nums, int start, int end){
        int dp_i_2 = 0, dp_i_1 = 0, dp_i = 0;
        for(int i = end; i>=start; i--){
            dp_i = Math.max(dp_i_1,nums[i]+dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
