/**
 * Say you have an array prices for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Example 2:
 *
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 */

public class BestTimetoBuyandSellStockII {
    public static void main(String[] args) {
        BestTimetoBuyandSellStockII solution = new BestTimetoBuyandSellStockII();
        System.out.println(solution.maxProfitdp(new int[]{7,1,5,3,6,4}));
        System.out.println(solution.maxProfitdp(new int[]{1,2,3,4,5}));
    }

    public int maxProfitdp(int[] prices){

        int[][] dp = new int[prices.length][2];
        for(int i =0; i< prices.length;i++){
            if(i-1 == -1){
                dp[i][0]=0;
                dp[i][1]=-prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);

        }
        return dp[prices.length-1][0];
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2) return 0;
        int[] hold = new int[n];
        int[] sold = new int[n];
        hold[0] = -prices[0];
        sold[0] = 0;
        for(int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i]);
        }
        return sold[n- 1];
    }
}
