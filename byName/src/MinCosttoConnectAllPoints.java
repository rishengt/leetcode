/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation:
 *
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 * Example 2:
 *
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 * Example 3:
 *
 * Input: points = [[0,0],[1,1],[1,0],[-1,1]]
 * Output: 4
 * Example 4:
 *
 * Input: points = [[-1000000,-1000000],[1000000,1000000]]
 * Output: 4000000
 * Example 5:
 *
 * Input: points = [[0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * All pairs (xi, yi) are distinct.
 */
import java.util.*;
public class MinCosttoConnectAllPoints {
    public static void main(String[] args) {
        System.out.println(new MinCosttoConnectAllPoints().prim(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}));
    }
    public int prim(int[][] points) {
        int n = points.length;
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = Math.abs(points[i][0] - points[j][0])
                        + Math.abs(points[i][1] - points[j][1]);
            }
        }
        boolean[] v = new boolean[n];
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        /**这里代表了自己去自己距离为0*/
        d[0] = 0;
        /**这里就是整个prim的精髓了，好好翻译翻译。。。*/
        for (int i = 0; i < n; i++) {
            /**这里很好地贯彻了prim的思想，把不存在的parent先用-1占住*/
            int x = -1;
            /**这条loop代表了什么。。。。,是当前节点可以去的节点？？？*/
            for (int j = 0; j < n; j++) {
                /**这里x是怎么更新的呢，判断条件是什么。。*/
                if (!v[j] && (x == -1 || d[j] < d[x])) x = j;
            }
            v[x] = true;
            /**这条loop又是什么。。。。*/
            for (int y = 0; y < n; y++)
                if (!v[y]) d[y] = Math.min(d[y], a[x][y]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += d[i];
        }
        return res;
    }

    public int primII(int[][] points) {
        int n = points.length, ans = 0;
        HashSet<Integer> mst = new HashSet<>();
        mst.add(0);
        int[] dist = new int[n];
        for(int i = 1; i < n; i++) dist[i] = findDist(points, 0, i);
        while(mst.size() != n) {
            // Find the node that has shortest distance
            int next = -1;
            for(int i = 0; i < n; i++) {
                if(mst.contains(i)) continue;
                if(next == -1 || dist[next] > dist[i]) next = i;
            }
            // Put the node into the Minning Spanning Tree
            mst.add(next);
            ans += dist[next];
            // Update distance array
            for(int i = 0; i < n; i++) {
                if(!mst.contains(i)) {
                    dist[i] = Math.min(dist[i], findDist(points, i, next));
                }
            }
        }
        return ans;
    }
    private int findDist(int[][] points, int a, int b) {
        return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
    }
}
