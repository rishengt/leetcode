/**
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 *
 * Alex and Lee take turns, with Alex starting first.  Each turn,
 * a player takes the entire pile of stones from either the beginning or the end of the row.
 * This continues until there are no more piles left, at which point the person with the most stones wins.
 *
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 *
 *
 *
 * Example 1:
 *
 * Input: [5,3,4,5]
 * Output: true
 * Explanation:
 * Alex starts first, and can only take the first 5 or the last 5.
 * Say he takes the first 5, so that the row becomes [3, 4, 5].
 * If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
 * If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 *
 *
 * Note:
 *
 * 2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles) is odd.
 */
public class StoneGame {

    /**
     * dp[i][j] means the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j].
     * You can first pick piles[i] or piles[j].
     *
     * If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
     * If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
     * So we get:
     * dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
     * We start from smaller subarray and then we use that to calculate bigger subarray.
     */
    public boolean stoneGame(int[] p) {
        int n = p.length;
        int[][] dp  = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = p[i];/**这莫名其妙的数组初始化，看那个视频勉强有点印象，但说服不了我自己啊！！！*/
        for (int d = 1; d < n; d++)/**这d又是怎么回事啊操！！！！*/
            for (int i = 0; i + d < n; i++)
                dp[i][i + d] = Math.max(p[i] - dp[i + 1][i + d], p[i + d] - dp[i][i + d - 1]);
        return dp[0][n - 1] > 0;
    }

    public boolean stoneGameII(int[] piles) {

        // dp[i][j]: scores of Alex and Lee given piles[i:j], Alex picks first
        int dp[][][] = new int[piles.length][piles.length][], N = piles.length;

        for (int i = 0; i < N; i++)
            dp[i][i] = new int[]{piles[i], 0};


        for (int k = 1; k < N; k++) {

            // piles[i, ..., j]
            for (int i = 0; i + k < N; i++) {
                int j = i + k;

                // score if Alex pikcs piles[i]
                int pickLeftScore = piles[i] + dp[i + 1][j][1];

                // score if Alex picks piles[j]
                int pickRightScore = piles[j] + dp[i][j - 1][1];

                if (pickLeftScore > pickRightScore) {
                    int leeScore = dp[i + 1][j][0];
                    dp[i][j] = new int[]{pickLeftScore, leeScore};
                } else {
                    int leeScore = dp[i][j - 1][0];
                    dp[i][j] = new int[]{pickRightScore, leeScore};
                }
            }
        }

        return dp[0][N - 1][0] > dp[0][N - 1][1];
    }

    public boolean stoneGameIII(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }

        return dp[1][N] > 0;
    }
}
