/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color, and you need to cost the least.
 * Return the minimum cost.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 */
public class PaintHouse {
    public int minCost(int[][] costs){
        if(costs == null || costs.length == 0) return 0;
        for(int i=1; i<costs.length; i++){
            /**大概每个位置都记录到这里为止所有涂房子的总和，而且因为颜色不能相同所以上一家房子要在除自己的颜色以外选个花销最小的*/
            /**注意，房子是row，横着排开的，审题*/
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        return Math.min(costs[costs.length-1][0], Math.min(costs[costs.length-1][1], costs[costs.length-1][2]));
    }
}
