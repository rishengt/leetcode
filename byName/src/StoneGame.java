import java.util.Arrays;

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
     *
     * 这道题也可以使用动态规划 Dynamic Programming 来做，由于玩家获胜的规则是拿到的石子数多，那么多的石子数就可以量化为 dp 值。
     * 所以我们用一个二维数组，其中 dp[i][j] 表示在区间 [i, j] 内 Alex 比 Lee 多拿的石子数，若为正数，说明 Alex 拿得多，若为负数，则表示 Lee 拿得多。
     * 则最终只要看 dp[0][n-1] 的值，若为正数，则 Alex 能获胜。现在就要找状态转移方程了，我们想，在区间 [i, j] 内要计算 Alex 比 Lee 多拿的石子数，
     * 在这个区间内，Alex 只能拿i或者j位置上的石子，那么当 Alex 拿了 piles[i] 的话，等于 Alex 多了 piles[i] 个石子，此时区间缩小成了 [i+1, j]，
     * 此时应该 Lee 拿了，此时根据我们以往的 DP 经验，应该调用子区间的 dp 值，没错，但这里 dp[i+1][j] 表示是在区间 [i+1, j] 内 Alex 多拿的石子数，
     * 但是若区间 [i+1, j] 内 Lee 先拿的话，其多拿的石子数也应该是 dp[i+1][j]，因为两个人都要最优化拿，那么 dp[i][j] 的值其实可以被 piles[i] - dp[i+1][j] 更新，
     * 因为 Alex 拿了 piles[i]，减去 Lee 多出的 dp[i+1][j]，就是区间 [i, j] 中 Alex 多拿的石子数。
     * 同理，假如 Alex 先拿 piles[j]，那么就用 piles[j] - dp[i][j-1] 来更新 dp[i][j]，则我们用二者的较大值来更新即可。
     * 注意开始的时候要把 dp[i][i] 都初始化为 piles[i]，还需要注意的是，这里的更新顺序很重要，是从小区间开始更新，
     */
    public boolean stoneGame(int[] p) {
        int n = p.length;
        int[][] dp  = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = p[i];/**这莫名其妙的数组初始化，看那个视频勉强有点印象，但说服不了我自己啊！！！*/
        for (int d = 1; d < n; d++)/**这d又是怎么回事啊操！！！！ 呵呵，看上面的解释，从小区间开始，怎样能令到区间最小？？？呵呵*/
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

    /*******************************************博弈问题神技，minimax，好好品品*********************************************/
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

    /**
     * This is a Minimax problem. Each player plays optimally to win, but you can't greedily choose the optimal strategy so you have to try all strategies, this is DP now.
     *
     * What does it mean for Alex to win? Alex will win if score(Alex) >= score(Lee), but this also means score(Alex) - score(Lee) >= 0, so here you have a common parameter which is a score variable. The score parameter really means score = score(Alex) - score(Lee).
     *
     * Now, if each player is suppoed to play optimally, how do you decide this with one variable?
     *
     * Well since Alex is playing optimally, he wants to maximize the score variable because remember, Alex only wins if score = score(Alex) - score(Lee) >= 0 Alex should add to the score because he wants to maximize it.
     * Since Lee is also playing optimally, he wants to minimize the score variable, since if the score variable becomes negative, Lee has more individual score than Alex. But since we have only one variable, Lee should damage the score (or in other words, subtract from the score).
     *
     * Now, let's think of the brute force solution. You are at the current state, say this is Alex's turn. Alex can choose either left or right, but he can't greedily pick so you try both of them, this is O(2^n) for runtime.
     *
     * But realize the state you are in. You can easily memoize the this, the varying parameters are l, r, ID, where ID is the player ID (1 for Alex, 0 for Lee), hence you can make a DP/Cache table and return answer if you have discovered the state.
     *
     * Finally, in your main function you call this helper function and check if you were able to get a score >= 0.
     */
    int [][][] memo;
    public boolean playIIII(int[] piles) {
        memo = new int[piles.length + 1][piles.length + 1][2];
        for(int [][] arr : memo)
            for(int [] subArr : arr)
                Arrays.fill(subArr, -1);

        return (helper(0, piles.length - 1, piles, 1) >= 0);
    }

    public int helper(int l, int r, int [] piles, int ID){
        if(r < l)
            return 0;
        if(memo[l][r][ID] != -1)
            return memo[l][r][ID];

        int next = Math.abs(ID - 1);
        if(ID == 1)
            memo[l][r][ID] = Math.max(piles[l] + helper(l + 1, r, piles, next), piles[r] + helper(l, r - 1, piles, next));
        else
            memo[l][r][ID] = Math.min(-piles[l] + helper(l + 1, r, piles, next), -piles[r] + helper(l, r - 1, piles, next));

        return memo[l][r][ID];
    }
}
