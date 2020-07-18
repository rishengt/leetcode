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
                sums[i] = sums[i+1] + piles[i]; //the sum from piles[i] to the end
            }

            int[][] hash = new int[n][n];
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
                min = Math.min(min, helper(a, i+x, Math.max(M,x), sums,hash));
            }
            hash[i][M] = sums[i] - min;  //max stones = all the left stones - the min stones next player can get
            return hash[i][M];
        }


        /**************************************Game Theory*************************************************************/
        public int playII(int[] piles) {
            return help(piles, 0, 1, 0, new Integer[piles.length + 1][2 * piles.length + 1][2]);
        }

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
                maxScore = Math.min(maxScore, next);
        }

        return dp[i][m][isAlex] = maxScore;
    }
}
