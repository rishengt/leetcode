import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 * Example 2:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 * Example 3:
 *
 *
 *
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 */
public class PathwithMaximumProbability {
    public static void main(String[] args) {
        System.out.println(new PathwithMaximumProbability().maxProbability(3,new int[][]{{0,1},{1,2},{0,2}}, new double[]{0.5,0.5,0.2},0,2));
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        ArrayList<double[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        /**用来建立图的，graph的index代表出发的点，index存的arrayList第一位是代表要去的点，第二位是去那里的probability*/
        /**一定要学会怎么建图，太重要了*/
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new double[] { edges[i][1], succProb[i] });
            graph[edges[i][1]].add(new double[] { edges[i][0], succProb[i] });
        }
        /**用priorityQueue最重要的意义就是确保你每次去下一个点都是最优解，可以看看dijkstra的视频*/
        PriorityQueue<double[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));/**对比double一定要这样写，记住了傻叼！！！*/
        boolean[] visited = new boolean[n];
        queue.add(new double[] { start, 1 });/**由start向start出发是一定不会失败的，所以它的probability是1*/
        while (!queue.isEmpty()) {
            double[] head = queue.remove();/**用priorityQueue的意义就是，当你遍历到end的时候，你poll出来的一定是最大的概率*/
            if(visited[(int) head[0]]) continue;/**visited也是Dijkstra的重要一环，它保证了你不会回到来你之前的上一个点，细品*/
            if (head[0] == end) {
                return head[1];/**用priorityQueue的意义，细品*/
            }
                visited[(int) head[0]] = true;
                for (double[] next : graph[(int) head[0]]) {
                    queue.add(new double[] { next[0], head[1] * next[1] });
                }
            }

        return 0;
    }
}
