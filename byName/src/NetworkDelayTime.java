import java.lang.reflect.Array;
import java.util.*;

/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node,
 * and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 *
 *
 * Note:
 *
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 */
public class NetworkDelayTime {
    public static void main(String[] args) {
        System.out.println(new NetworkDelayTime().networkDelayTime(new int[][]{{1,2,1}},2,2));
        System.out.println(new NetworkDelayTime().networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}},4,2));
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        ArrayList<int[]>[] graph = new ArrayList[N+1];/**为什么这里要加一呢？因为N从1开始，注意审题傻叼*/
        for(int i = 0; i<= N; i++){
            graph[i] = new ArrayList<int[]>();
        }
        for(int i = 0; i< times.length; i++){
            graph[times[i][0]].add(new int[]{times[i][1],times[i][2]});
        }
        boolean[] visited = new boolean[N+1];
        Queue<int[]> queue = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        queue.offer(new int[]{K, 0});
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            if(visited[temp[0]]) continue;/**这个写法才是最他妈安全的，visit过的就continue*/
            visited[temp[0]] = true;
            N--;/**后面一系列动作都是要在不continue的情况下进行的*/
            if(N == 0) return temp[1];
            visited[temp[0]] = true;
            for(int[] next : graph[temp[0]]){
                queue.offer(new int[]{next[0],temp[1]+next[1]});
            }

        }
        return -1;
    }
}
