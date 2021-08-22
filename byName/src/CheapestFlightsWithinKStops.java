import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 *
 * Constraints:
 *
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The Date.format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        System.out.println(new CheapestFlightsWithinKStops().findCheapestPrice(3,new int[][]{{0,1,100},{1,2,100},{0,2,500}},0,2,1));
        System.out.println(new CheapestFlightsWithinKStops().findCheapestPrice(3,new int[][]{{0,1,100},{1,2,100},{0,2,500}},0,2,0));
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        ArrayList<int[]>[] graph = new ArrayList[n];
        for(int i = 0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i< flights.length; i++){
            graph[flights[i][0]].add(new int[]{flights[i][1], flights[i][2]});
        }
        //boolean[] visited = new boolean[n]; 也许如果要保留所有路径而不是只选最优的话这个就不能要了
        Queue<int[]> queue = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));
        queue.offer(new int[]{src,0, K+1});/**精髓K+1传参，至今没能掌握这类dfs的精华*/
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
//            if (visited[temp[0]]) continue; 不能要，因为要保留所以路径
//            visited[temp[0]] = true;
            if (temp[0] == dst) return temp[1];
            if (temp[2] > 0) {
                for (int[] next : graph[temp[0]]) {
                    queue.offer(new int[]{next[0], temp[1] + next[1], temp[2]-1});/**精髓传参的follow up*/
                }
            }
        }
        return -1;
    }
}
