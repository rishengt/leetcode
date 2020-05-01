/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimetoBuyandSellStockwithCooldown {
    public static void main(String[] args) {
        BestTimetoBuyandSellStockwithCooldown solution = new BestTimetoBuyandSellStockwithCooldown();
        System.out.println(solution.maxProfit(new int[]{1,2,3,0,2},1));
    }
    public int maxProfit(int[] prices){
        int[][] dp = new int[prices.length][2];
        for(int i = 0; i< prices.length; i++){
            if(i == 0){
                dp[i][0] = 0;
                dp[i][1] = -prices[0];
                continue;
            }
            if(i == 1){
                dp[i][1] = 0;/**because dp[i-2][0] will cause indexOutOfBound exception (-1), and you cannot buy within one day after you sell
                                so when you bought stock in day one(i==0) and already held stock in day2 (i==1), your max profit could only be zero because
                                you are still in Cooldown;*/
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }

    public int maxProfit(int[] prices, int Cooldown){
        int[][] dp = new int[prices.length][2];
        for(int i = 0; i< prices.length; i++){
            if(i == 0){
                dp[i][0] = 0;
                dp[i][1] = -prices[0];
                continue;
            }else if(i-Cooldown-1<0){
                dp[i][1] = 0;
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-Cooldown-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }
}
