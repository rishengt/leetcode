import java.util.Arrays;

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

public class _322CoinChange {
    public static void main(String[] args) {
        System.out.println(new _322CoinChange().coinChange(new int[]{1,2,5},11));
        System.out.println(new _322CoinChange().coinChange(new int[]{2},3));
    }

    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j <= amount; j++){
                if(f[j - coins[i]] != Integer.MAX_VALUE){
                    f[j] = Math.min(f[j], f[j - coins[i]] + 1);
                }
            }
        }
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }

    public int coinChangeII(int[] coins, int amount) {
        int[][] f = new int[coins.length + 1][amount + 1];
        for(int[] a: f) Arrays.fill(a, Integer.MAX_VALUE);
        for(int i = 0; i <= coins.length; i++){
            f[i][0] = 0;
        }
        for(int i = 1; i <= coins.length; i++){
            for(int j = 0; j <= amount; j++){
                f[i][j] = f[i - 1][j];
                if(j >= coins[i - 1]){
                    if(f[i][j - coins[i - 1]] != Integer.MAX_VALUE)
                        f[i][j] = Math.min(f[i][j], f[i][j - coins[i - 1]] + 1);
                }

            }
        }
        return f[coins.length][amount] == Integer.MAX_VALUE ? -1 : f[coins.length][amount];
    }
}
