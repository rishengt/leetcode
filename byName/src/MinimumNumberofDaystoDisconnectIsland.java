/**
 * Given a 2D grid consisting of 1s (land) and 0s (water).  An island is a maximal 4-directionally (horizontal or vertical) connected group of 1s.
 *
 * The grid is said to be connected if we have exactly one island, otherwise is said disconnected.
 *
 * In one day, we are allowed to change any single land cell (1) into a water cell (0).
 *
 * Return the minimum number of days to disconnect the grid.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 2
 * Explanation: We need at least 2 days to get a disconnected grid.
 * Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
 * Example 2:
 *
 * Input: grid = [[1,1]]
 * Output: 2
 * Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
 * Example 3:
 *
 * Input: grid = [[1,0,1,0]]
 * Output: 0
 * Example 4:
 *
 * Input: grid = [[1,1,0,1,1],
 *                [1,1,1,1,1],
 *                [1,1,0,1,1],
 *                [1,1,0,1,1]]
 * Output: 1
 * Example 5:
 *
 * Input: grid = [[1,1,0,1,1],
 *                [1,1,1,1,1],
 *                [1,1,0,1,1],
 *                [1,1,1,1,1]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[i].length <= 30
 * grid[i][j] is 0 or 1.
 */
public class MinimumNumberofDaystoDisconnectIsland {
    public static void main(String[] args) {
        System.out.println(new MinimumNumberofDaystoDisconnectIsland().minDays(new int[][]{{0,1,1,0},{0,1,1,0},{0,0,0,0}}));
        System.out.println(new MinimumNumberofDaystoDisconnectIsland().minDays(new int[][]{{1,1}}));
        System.out.println(new MinimumNumberofDaystoDisconnectIsland().minDays(new int[][]{{1,0,1,0}}));
        System.out.println(new MinimumNumberofDaystoDisconnectIsland().minDays(new int[][]{{1,1,0,1,1},{1,1,1,1,1},{1,1,0,1,1},{1,1,0,1,1}}));
        System.out.println(new MinimumNumberofDaystoDisconnectIsland().minDays(new int[][]{{1,1,0,1,1},{1,1,1,1,1},{1,1,0,1,1},{1,1,1,1,1}}));
    }
    public int minDays(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = numOfIsland(grid,visited);
        if(count != 1) return 0;
        else{
            for(int i = 0; i<grid.length; i++){
                for(int j = 0; j<grid[0].length; j++){
                    if(grid[i][j] == 1){
                        grid[i][j] = 0;
                        visited = new boolean[grid.length][grid[0].length];
                        int newCount = numOfIsland(grid,visited);
                        if(newCount!=1) return 1;
                        grid[i][j] = 1;
                    }
                }
            }
        }
        return 2;
    }
    public int numOfIsland(int[][] grid, boolean[][] visited){
        int count = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    count++;
                    visited[i][j] = true;
                    dfs(grid,i,j,visited);
                }
            }
        }
        return count;
    }
    public void dfs(int[][] grid, int i, int j, boolean[][] visited){
        int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        for(int[] dir: dirs){
            int x = i+dir[0];
            int y = j+dir[1];
            if(x<grid.length&&x>=0&&y<grid[0].length&&y>=0&& grid[x][y] == 1 && !visited[x][y]){
                visited[x][y] = true;
                dfs(grid,x,y,visited);
            }
        }
    }
}
