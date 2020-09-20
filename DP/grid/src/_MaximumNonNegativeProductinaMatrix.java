/**
 * You are given a rows x cols matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.
 *
 * Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (rows - 1, cols - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.
 *
 * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative return -1.
 *
 * Notice that the modulo is performed after getting the maximum product.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[-1,-2,-3],
 *                [-2,-3,-3],
 *                [-3,-3,-2]]
 * Output: -1
 * Explanation: It's not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
 * Example 2:
 *
 * Input: grid = [[1,-2,1],
 *                [1,-2,1],
 *                [3,-4,1]]
 * Output: 8
 * Explanation: Maximum non-negative product is in bold (1 * 1 * -2 * -4 * 1 = 8).
 * Example 3:
 *
 * Input: grid = [[1, 3],
 *                [0,-4]]
 * Output: 0
 * Explanation: Maximum non-negative product is in bold (1 * 0 * -4 = 0).
 * Example 4:
 *
 * Input: grid = [[ 1, 4,4,0],
 *                [-2, 0,0,1],
 *                [ 1,-1,1,1]]
 * Output: 2
 * Explanation: Maximum non-negative product is in bold (1 * -2 * 1 * -1 * 1 * 1 = 2).
 *
 *
 * Constraints:
 *
 * 1 <= rows, cols <= 15
 * -4 <= grid[i][j] <= 4
 */
public class _MaximumNonNegativeProductinaMatrix {
    public static void main(String[] args) {
        System.out.println(new _MaximumNonNegativeProductinaMatrix().maxProductPath(new int[][]{{-1,-2,-3},{-2,-3,-3},{-3,-3,-2}}));
        System.out.println(new _MaximumNonNegativeProductinaMatrix().maxProductPath(new int[][]{{1,-2,1},{1,-2,1},{3,-4,1}}));
        System.out.println(new _MaximumNonNegativeProductinaMatrix().maxProductPath(new int[][]{{1, 3},{0,-4}}));
        System.out.println(new _MaximumNonNegativeProductinaMatrix().maxProductPath(new int[][]{{1, 4,4,0},{-2, 0,0,1},{1,-1,1,1}}));
    }

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][] mindp = new long[m][n];
        long[][] maxdp = new long[m][n];
        int mod = (int)(1e9) + 7;
        for(int i = 0; i < m; i++){
            mindp[i][0] = grid[i][0];
            maxdp[i][0] = grid[i][0];
            if(i!=0){
                mindp[i][0] *= mindp[i-1][0];
                maxdp[i][0] *= maxdp[i-1][0];
            }
        }
        for(int i = 0; i < n; i++){
            mindp[0][i] = grid[0][i];
            maxdp[0][i] = grid[0][i];
            if(i!=0){
                mindp[0][i] *= mindp[0][i-1];
                maxdp[0][i] *= maxdp[0][i-1];
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                // If current value is negative, minimum value is maximum value multiply current value, maximum value is minimum value multiply current value
                if(grid[i][j] < 0){
                    mindp[i][j] = Math.min(maxdp[i - 1][j]* grid[i][j], maxdp[i][j-1] * grid[i][j]) ;
                    maxdp[i][j] = Math.max(mindp[i - 1][j]* grid[i][j], mindp[i][j-1]* grid[i][j]);
                }
                else if(grid[i][j] == 0){
                    mindp[i][j] = 0;
                    maxdp[i][j] = 0;
                }
                else{
                    mindp[i][j] = Math.min(mindp[i - 1][j]* grid[i][j], mindp[i][j-1]* grid[i][j]);
                    maxdp[i][j] = Math.max(maxdp[i - 1][j]* grid[i][j], maxdp[i][j-1]* grid[i][j]);
                }

            }
        }
        return maxdp[m-1][n-1] < 0 ? -1 : (int)(maxdp[m-1][n-1] % mod);
    }

    /** 過不了下面這個case， 不知道爲何。。。。
     * [[1,-1,2,1,-1,0,0,4,3,2,0,-2,-2],[-2,3,3,-1,-1,0,0,-2,4,-3,3,0,0],[-4,-1,-1,-2,2,-1,-2,-2,0,3,-1,-4,1],[-3,4,-3,0,-3,1,-3,1,4,4,-4,-4,-2],[3,-3,1,0,-1,-4,-4,-4,3,2,2,3,3],[2,-1,-1,-4,-3,-3,4,2,3,4,4,-4,0],[4,-1,2,-3,-1,-1,-3,-4,4,4,4,-3,-1],[-3,-4,4,-2,-1,2,3,-1,2,3,4,4,-4],[-3,-1,-2,1,1,-1,-3,-4,-3,1,-3,3,-4],[2,4,4,4,-3,-3,1,-1,3,4,-1,1,4],[2,-2,0,4,-1,0,-2,4,-4,0,0,2,-3],[1,1,-3,0,-4,-4,-4,-4,0,-1,-4,-1,0],[3,-1,-3,-3,-3,-2,-1,4,-1,-2,4,2,3]]
     */
    public int maxProductPathII(int[][] grid) {
        /**凡是這種給有負數有正數然後讓找最大乘積的希望你能想到kadane 算法。。。*/
        int min[][] = new int[grid.length][grid[0].length], max[][] = new int[grid.length][grid[0].length];

        /**縱觀grid類型凡是只能往右邊或者下面走的它第一行和第一列的初始化都很有邏輯的，你要自己琢磨出來*/
        min[0][0] = max[0][0] = grid[0][0];
        for(int i = 1; i<grid.length; i++){
            min[i][0] = min[i-1][0]*grid[i][0];
            max[i][0] = max[i-1][0]*grid[i][0];
        }
        for(int j = 1; j<grid[0].length; j++){
            min[0][j] = min[0][j-1]*grid[0][j];
            max[0][j] = max[0][j-1]*grid[0][j];
        }

        for(int i = 1; i<grid.length; i++){
            for(int j = 1; j<grid[0].length; j++){
                max[i][j] = Math.max(max[i-1][j]*grid[i][j], Math.max(max[i][j-1]*grid[i][j], Math.max(min[i][j-1]*grid[i][j],min[i-1][j]*grid[i][j])));
                min[i][j] = Math.min(max[i-1][j]*grid[i][j], Math.min(max[i][j-1]*grid[i][j], Math.min(min[i][j-1]*grid[i][j],min[i-1][j]*grid[i][j])));
            }
        }
        return max[grid.length-1][grid[0].length-1] < 0 ? -1: max[grid.length-1][grid[0].length-1];
    }
}
