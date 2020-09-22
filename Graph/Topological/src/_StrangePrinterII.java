import java.util.*;
/**
 * There is a strange printer with the following two special requirements:
 *
 * On each turn, the printer will print a solid rectangular pattern of a single color on the grid.
 * This will cover up the existing colors in the rectangle.
 * Once the printer has used a color for the above operation, the same color cannot be used again.
 * You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of the grid.
 *
 * Return true if it is possible to print the matrix targetGrid, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
 * Output: true
 * Example 2:
 *
 *
 *
 * Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
 * Output: true
 * Example 3:
 *
 * Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
 * Output: false
 * Explanation: It is impossible to form targetGrid because it is not allowed to print the same color in different turns.
 * Example 4:
 *
 * Input: targetGrid = [[1,1,1],[3,1,3]]
 * Output: false
 *
 *
 * Constraints:
 *
 * m == targetGrid.length
 * n == targetGrid[i].length
 * 1 <= m, n <= 60
 * 1 <= targetGrid[row][col] <= 60
 */

/**这道题看了Guan大神的分析跟答案以后，发现是个拓扑排序的问题，因为你要染上一层的色，必须要把下一层的色先染了，
 * 所以要先建立一个有向的图然后拓扑看看能不能完成所有颜色的遍历，
 * 建图是个难点，
 * 拓扑又是个难点，
 * 但是要克服
 */

public class _StrangePrinterII {
    public boolean isPrintable(int[][] targetGrid) {
        int degree[] = new int[61];
        ArrayList<Integer> graph[] = new ArrayList[61];
        for(int i = 0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        /**这一步的建图逻辑跟我自己想的不一样，好好品*/
        for(int i = 1; i<= 60; i++)buildGraph(targetGrid,graph,i,degree);
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<degree.length; i++){
            if(degree[i] == 0){
                count++;
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int temp = queue.poll();
            for(int k: graph[temp]){
                degree[k] --;
                if(degree[k] == 0){
                    count++;
                    queue.offer(k);
                }
            }
        }
        return count == 61;
    }

    public void buildGraph(int[][] targetGrid, ArrayList<Integer>[] graph, int color, int degree[]){
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for(int i = 0; i<targetGrid.length; i++)
            for(int j = 0; j<targetGrid[0].length; j++)
                if(targetGrid[i][j] == color){
                    maxX = Math.max(j,maxX);
                    minX = Math.min(j,minX);
                    maxY = Math.max(i,maxY);
                    minY = Math.min(i,minY);
                }
        for(int i = minY; i<=maxY; i++){
            for(int j = minX; j<=maxX; j++){
                if(targetGrid[i][j] != color){
                    degree[color]++;
                    graph[targetGrid[i][j]].add(color);
                }
            }
        }
    }
}
