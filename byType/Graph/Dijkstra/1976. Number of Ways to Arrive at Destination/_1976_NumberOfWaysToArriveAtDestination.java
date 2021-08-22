import java.util.*;

/**
 * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
 *
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
 *
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * Output: 4
 * Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
 * The four ways to get there in 7 minutes are:
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 * Example 2:
 *
 * Input: n = 2, roads = [[1,0,10]]
 * Output: 1
 * Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n - 1 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * 1 <= timei <= 109
 * ui != vi
 * There is at most one road connecting any two intersections.
 * You can reach any intersection from any other intersection.
 */
public class _1976_NumberOfWaysToArriveAtDestination {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    public int countPaths(int n, int[][] roads) {
        if(roads.length<=0) return 1;
        for(int[] r: roads){
            graph.computeIfAbsent(r[0], a->new ArrayList<>()).add(new int[]{r[1],r[2]});
            graph.computeIfAbsent(r[1], a->new ArrayList<>()).add(new int[]{r[0],r[2]});
        }
        int paths[] = new int[n];
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        /**精髓数组，表示由0到目的地的距离*/
        dist[0] = 0;
        pq.offer(new int[]{0,0});
        /**dp存到这个点有几种路径*/
        paths[0] = 1;
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            int weight = temp[1];
            for(int[] neighbor: graph.get(temp[0])){
                /**找到更短的距离*/
                if(neighbor[1]+weight<dist[neighbor[0]]){
                    /**就更新*/
                    dist[neighbor[0]] = neighbor[1]+weight;
                    /**找到了更短的证明路径只能用更短的点的*/
                    paths[neighbor[0]] = paths[temp[0]];
                    /**去下一个点*/
                    pq.offer(new int[]{neighbor[0],neighbor[1]+weight});
                }else if(neighbor[1]+weight == dist[neighbor[0]]){
                    /**一样长的话就把那个点的都更新上来*/
                    paths[neighbor[0]] = (paths[neighbor[0]]+paths[temp[0]])%1000000007;
                }
            }
        }
        return paths[n-1];
    }
}
