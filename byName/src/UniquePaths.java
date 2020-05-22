import javax.crypto.spec.PSource;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 * Above is a 7 x 3 grid. How many possible unique paths are there?

 * Example 1:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * Example 2:
 *
 * Input: m = 7, n = 3
 * Output: 28
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 100
 * It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
 */

public class UniquePaths {
    /**感觉跟爬楼梯很像*/
    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePathsRecursive(7,3));
        System.out.println(new UniquePaths().uniquePaths(7,3));
    }

    /**基本上每一道能用dp写的题都能用递归写，但递归可能会超时所以要用dp来优化*/
    public int uniquePathsRecursive(int m, int n){
        return recursive(0,0,m-1,n-1);
    }
    public int recursive(int x, int y, int m, int n){
        if(x==m && y == n) return 1;/**表示找到一条路了*/
        int n1 = 0;
        int n2 = 0;
        if(x<m){
            n1 = recursive(x+1, y,m,n);
        }
        if(y<n){
            n2 = recursive(x,y+1,m,n);
        }
        return n1+n2;
    }
/***********************************这道题的dp要求你记住不过分吧菜🐔*********************************************************/
    public int uniquePaths(int m, int n){
        int[][] dp = new int[m][n];
        for(int i = 0; i<m; i++){
            for(int j = 0; j< n; j++){
                if(i==0 || j== 0) dp[i][j] = 1; /**细品，当你在x轴或者y轴的时候你只有一条路可以走， 因为你不能回头，只能往右或者下*/
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
