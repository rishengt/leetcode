import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island
 * (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1.
 * The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 *
 * Example:
 *
 * Input:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * Output: 16
 *
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 */
public class IslandPerimeter {
    public static void main(String[] args) {
        System.out.println(new IslandPerimeter().islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
    }
    public int islandPerimeter(int[][] grid) {
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int ans = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                visited[i][j] = true;
                if(grid[i][j] == 1){
                    ans+=4;
                    for(int[] d: dir){
                        int x = i+d[0];
                        int y = j+d[1];
                        if(x>=0&&y>=0&&x<grid.length&&y<grid[0].length&&!visited[x][y]&&grid[x][y]==1){
                            ans-=2;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /******************************************* 不用像我搞得那么负责，直接只检查你走过的路就好了********************************/
    public int islandPerimeterII(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    if (i > 0 && grid[i-1][j] == 1) result -= 2;
                    if (j > 0 && grid[i][j-1] == 1) result -= 2;
                }
            }
        }
        return result;
    }
}
