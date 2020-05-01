/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

public class BestTimetoBuyandSellStock {
    public static void main(String[] args) {
        BestTimetoBuyandSellStock b = new BestTimetoBuyandSellStock();
        System.out.println(b.maxProfitdp(new int[]{7,1,5,3,6,4}));

    }
    public int maxProfit(int [] prices){
        int buy = prices[0];
        int ans = 0;
        for(int i = 0; i< prices.length; i++){
            if(prices[i]<buy){
                buy = prices[i];
            }else if(prices[i]-buy>ans){
                ans = Math.max(ans,prices[i]-buy);
            }
        }
        return ans;
    }
/***********************************************************************************************************************/
    public int maxProfitdp(int[] prices){
        int[][] dp = new int[prices.length][2];
        for(int i = 0; i<prices.length; i++){
            if(i-1== -1){
                dp[i][0] = 0;
                dp[i][1] = -prices[0];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
        }
        return dp[prices.length-1][0];
    }
}



