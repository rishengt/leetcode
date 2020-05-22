/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 *
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 *
 * Input:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 *
 *
 * Algorithm
 *
 * If the first cell i.e. obstacleGrid[0,0] contains 1, this means there is an obstacle in the first cell.
 * Hence the robot won't be able to make any move and we would return the number of ways as 0.
 * Otherwise, if obstacleGrid[0,0] has a 0 originally we set it to 1 and move ahead.
 * Iterate the first row. If a cell originally contains a 1, this means the current cell has an obstacle and shouldn't contribute to any path.
 * Hence, set the value of that cell to 0. Otherwise, set it to the value of previous cell i.e. obstacleGrid[i,j] = obstacleGrid[i,j-1]
 * Iterate the first column. If a cell originally contains a 1, this means the current cell has an obstacle and shouldn't contribute to any path.
 * Hence, set the value of that cell to 0. Otherwise, set it to the value of previous cell i.e. obstacleGrid[i,j] = obstacleGrid[i-1,j]
 * Now, iterate through the array starting from cell obstacleGrid[1,1].
 * If a cell originally doesn't contain any obstacle then the number of ways of reaching that cell would be
 * the sum of number of ways of reaching the cell above it and number of ways of reaching the cell to the left of it.
 *  obstacleGrid[i,j] = obstacleGrid[i-1,j] + obstacleGrid[i,j-1]
 * If a cell contains an obstacle set it to 0 and continue. This is done to make sure it doesn't contribute to any other path.
 */
public class UniquePathsII {
    public static void main(String[] args) {
        System.out.println(new UniquePathsII().uniquePathsWithobstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
    }
    public int uniquePathsWithobstacles(int[][] grid){
        int row = grid.length;
        int col = grid[0].length;
        if(grid[0][0] == 1) return 0; /**要是起点就是obstacle的话，就不用再玩下去了*/
        grid[0][0] = 1;/**直接用grid上的点储存到此点的路数*/

        /**这两个for循环记得住就有鬼了你个菜🐔*/
        for(int i = 1; i <row; i++){/**就像上面说的，当你在第一行或者第一列而你前面一旦有挡路的（1），
         你得把所有在第一行和第一列的点都变成1，应为此路不通了，只能向下或者右，细品*/
            grid[i][0] = (grid[i][0] == 0 && grid[i-1][0] == 1)? 1:0;
        }
        for(int j = 1; j<col; j++){
            grid[0][j] = (grid[0][j] == 0 && grid[0][j-1] == 1)? 1:0;
        }
        for(int i = 1; i< row; i++){
            for(int j = 1; j< col; j++){
                if(grid[i][j]==0){/**只有代表为0的时候才通，可以更新一波*/
                    grid[i][j] = grid[i-1][j] + grid[i][j-1];
                }else{
                    grid[i][j] = 0;
                }
            }
        }
        return grid[row-1][col-1];
    }
}
