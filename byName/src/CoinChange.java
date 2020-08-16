import java.util.Arrays;
import java.util.HashMap;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */


/**这道题是不是超级像combination sum呢，哈哈，其实不是！！！，是一道dp da！！！！*/
/**这是一道完全背包问题，complete backpack，每个element能拿无数次并以最小的代价装满背包*****/

/**据大神所说，这道题的递推公式已经很明显了。 dp[n] = min{dp[n-coin[0]]+1, dp[n-coin[1]]+1.... dp[n-coin[coin.length-1]]+1}*/
/**dp[n] 代表的是达到 n amount最小需要的硬币枚数********/
public class CoinChange {
    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[]{1,2,5},11));
        System.out.println(new CoinChange().coinChange(new int[]{2},3));
    }

    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,-1);
        dp[0] = 0; /**这是个base case；你达到0 amount的时候不需要任何硬币；嘛dp总是要有base case的啦，as always*/
        for(int i = 0; i<coins.length; i++){
            if(coins[i]>amount) continue;/**总是有些傻逼给你个面值比你amount还大的，没办法，只能跳过*/
            dp[coins[i]] = 1; /***for 每个coin的面值要达到那个面值确实也只需要一个coin，没得黑*/
        }
        for(int i = 1; i<=amount; i++){
            if(dp[i] != -1) continue;
            int temp = Integer.MAX_VALUE;
            for(int j = 0; j<coins.length; j++){
                if(i-coins[j]>0 && dp[i-coins[j]]!=-1) {
                    temp = Math.min(dp[i - coins[j]]+1, temp);
                }
            }
            if(temp == Integer.MAX_VALUE){
                dp[i] = -1;
            }else{
                dp[i] = temp;
            }
        }
        return dp[amount];
    }
}
