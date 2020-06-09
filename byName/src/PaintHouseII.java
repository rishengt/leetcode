/**
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0;
 * costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 */
public class PaintHouseII {

    /********时间复杂度为O(nk^2)*/
    public int minCost(int[][] costs){
        if(costs == null || costs.length == 0){
            return 0;
        }

        int n = costs.length;
        int k = costs[0].length;

        int [][] dp = new int[n][k];

        for(int i = 0; i<k; i++){
            dp[0][i] = costs[0][i];
        }

        for(int i = 1; i<n; i++){
            for(int j = 0; j<k; j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int m = 0; m<k; m++){
                    if(m != j){ /**必须确保你找到的那个最小开销不是自身的颜色*/
                        dp[i][j] = Math.min(dp[i-1][m] + costs[i][j], dp[i][j]);
                    }
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for(int i = 0; i< k; i++){
            minCost = Math.min(minCost, dp[n-1][i]);
        }

        return minCost;
    }


    /**************************************** O(n)的解法，跟House Robber的one pass解法好像，但妈的都不知其所以然******************/
    public int minCostII(int[][] costs){
        if(costs != null && costs.length == 0) return 0;
        int prevMin = 0, prevSec = 0, prevIdx = -1;
        for(int i = 0; i < costs.length; i++){
            int currMin = Integer.MAX_VALUE, currSec = Integer.MAX_VALUE, currIdx = -1;
            for(int j = 0; j<costs[0].length; i++){
                costs[i][j] = costs[i][j] + (prevIdx == j? prevSec : prevMin);
                // 找出最小和次小的，最小的要记录下标，方便下一轮的判断
                if(costs[i][j] < currMin){
                    currSec = currMin;
                    currMin = costs[i][j];
                    currIdx = j;
                }else if(costs[i][j] < currSec){
                    currSec = costs[i][j];
                }
            }
            prevMin = currMin;
            prevSec = currSec;
            prevIdx = currIdx;
        }
        return prevMin;
    }
}
