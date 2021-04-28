import java.util.*;

/**
 * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.
 *
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
 *
 * The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 *
 * Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
 * Output: 3
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
 * 1) 1 --> 2 --> 5
 * 2) 1 --> 2 --> 3 --> 5
 * 3) 1 --> 3 --> 5
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
 * Output: 1
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * n - 1 <= edges.length <= 4 * 104
 * edges[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= weighti <= 105
 * There is at most one edge between any two nodes.
 * There is at least one path between any two nodes.
 */
public class _1786_NumberofRestrictedPathsFromFirsttoLastNode {
    public static void main(String[] args) {

    }

    int mod = 1000000007;
    Map<Integer, List<int[]>> graph = new HashMap<>();
    public int countRestrictedPaths(int n, int[][] edges) {
        for(int[] e: edges){
            /** computeIfAbsent 还能这么用，牛逼**/
            graph.computeIfAbsent(e[0], x->new ArrayList<>()).add(new int[]{e[1],e[2]});
            graph.computeIfAbsent(e[1], x->new ArrayList<>()).add(new int[]{e[0],e[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;
        pq.offer(new int[]{n,0});
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            int weight = temp[1];
            List<int[]> neighbors = graph.get(temp[0]);
            for(int[] neighbor: neighbors){
                int w = neighbor[1]+weight;
                if(w<dist[neighbor[0]]){
                    dist[neighbor[0]] = w;
                    pq.offer(new int[]{neighbor[0],w});
                }
            }
        }
        Integer[] dp = new Integer[n+1];
        return dfs(1,n,dist,dp);
    }

    int dfs(int node, int end, int[] dist, Integer[] dp){
        if(node == end) return 1;
        if(dp[node] != null) return dp[node];
        long res = 0;
        for(int[] neighbor: graph.get(node)){
            if(dist[node]> dist[neighbor[0]]){
                res += dfs(neighbor[0],end,dist,dp)%mod;
            }
        }
        res %= mod;
        return dp[node] = (int)res;
    }
}
