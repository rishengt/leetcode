/**
 * Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
 *
 * Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
 *
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 *
 * The game continues until all the stones have been taken.
 *
 * Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [2,7,9,4,4]
 * Output: 10
 * Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 */
public class StoneGameII {
    public static void main(String[] args) {
        System.out.println(new StoneGameII().play(new int[]{2,7,9,4,4}));
    }
    public int play(int[] piles) {
            if(piles == null || piles.length == 0) return 0;
            int n = piles.length;
            int[] sums = new int[n];
            sums[n-1] = piles[n-1];
            for(int i = n -2; i>=0;i--) {
                /**一定是最多的，跟第三题不同，pile【i】一定是正数，看constrain*/
                sums[i] = sums[i+1] + piles[i]; //the sum from piles[i] to the end
            }

            int[][] hash = new int[n][n];
            /**i表示从哪堆pile开始玩起，细品第一题的区间表达方法*/
            return helper(piles, 0, 1, sums, hash);
        }

        public int helper(int[] a, int i, int M, int[] sums,int[][] hash) {
            if(i == a.length) return 0;
            if(2*M >= a.length - i) {
                return sums[i];
            }
            if(hash[i][M] != 0) return hash[i][M];
            int min = Integer.MAX_VALUE;//the min value the next player can get
            for(int x=1;x<=2*M;x++){
                min = Math.min(min, helper(a, i+x, Math.max(M,x), sums,hash));/**因为Alex一共可以拿x pile，所以下一次开始的时候是i+x*/
            }
            hash[i][M] = sums[i] - min;  //max stones = all the left stones - the min stones next player can get
            return hash[i][M];
        }


        /**************************************Game Theory*************************************************************/
        public int playII(int[] piles) {
            return help(piles, 0, 1, 0, new Integer[piles.length + 1][2 * piles.length + 1][2]);
        }

    /**
     * The idea of minimax :
     *
     * If am the player 1 (whose winning sum we are trying to calculate), then I recurse on all possibilities and get the max.
     * If am the player 2 (the opponent), then I try to minimize what P1 gets, and since we are not interested in what score P2 gets, we only calculate the min(all P1 next moves) and dont include the score P2 gets.
     * Thanks to @douzigege for his comment which explains the minimax scenario specifically for this problem.
     *
     * if player == 1st player,
     * gain = first x piles + minimax(..., 2nd player), where the gain is maximized
     * if player == 2nd player,
     * gain = 0 + minimax(..., 1st player), where the gain is minimized because the 2nd player tries to maximize his**

     Regardless of who the player is, we want the function call to only return Alex's score.
     So, we'll keep a turn variable isAlex to keep track of whose turn it is.
     So if we're Alex, we'll return the maximum of the set: (stones grabbed this turn + recurse on Lee's turn starting at the next index from what we grabbed).
     If we're Lee, we will return the minumum of (recurse on Alex's turn starting at the next index from what we grabbed).
     The size of these sets will be--assuming we're not at the end of the array--2m, since one can try grabbing 2m stones at a turn.
     */
    private int help(int[] piles, int i, int m, int isAlex, Integer[][][] dp) {
        if (i >= piles.length)
            return 0;

        if (dp[i][m][isAlex] != null)
            return dp[i][m][isAlex];

        int maxScore = isAlex == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        int currSum = 0;

        for (int x = i; x < piles.length && x < i + 2 * m; x++) {
            currSum += piles[x];

            int next = help(piles, x + 1, Math.max(x - i + 1, m), isAlex ^ 1, dp);

            if (isAlex == 0)
                maxScore = Math.max(maxScore, currSum + next);
            else
                maxScore = Math.min(maxScore, next);/**p2跑只是要对p1的maxScore造成伤害而已，细品*/
        }

        return dp[i][m][isAlex] = maxScore;
    }
}
