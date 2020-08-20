/**
 * For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 *
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1.
 * You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 *
 * You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * Example 1 :
 * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 *         0
 *         |
 *         1
 *        / \
 *       2   3
 *
 * Output: [1]
 *
 *
 * Example 2 :
 * Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 *      0  1  2
 *       \ | /
 *         3
 *         |
 *         4
 *         |
 *         5
 *
 * Output: [3, 4]
 */
import java.util.*;
public class MinimumHeightTrees {
    public static void main(String[] args) {
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(4,new int[][]{{1,0},{1,2},{1,3}}));
//        System.out.println(new MinimumHeightTrees().findMinHeightTrees(6,new int[][]{{0,3},{1,3},{2,3},{4,3},{5,4}}));
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n==1) return Arrays.asList(0);
        if (n==2) return Arrays.asList(0,1);
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] e: edges){
            graph.putIfAbsent(e[0],new ArrayList<>());
            graph.get(e[0]).add(e[1]);
            graph.putIfAbsent(e[1],new ArrayList<>());
            graph.get(e[1]).add(e[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] degree = new int[graph.size()];
        for(Map.Entry<Integer, List<Integer>> entry: graph.entrySet()){
            degree[entry.getKey()] = entry.getValue().size();
        }
        for (int i = 0; i<degree.length; i++){
            if (degree[i] ==1){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            list = new ArrayList<>(); /**我艹，这里是整到题的精髓。。最后一个或者最后两个出queue的就是答案了，看湿老子。*/
            for(int i = 0; i<size; i++){
                int temp = queue.poll();
                list.add(temp);
                List<Integer> tempList = graph.get(temp);
                for(int j: tempList){
                    if(--degree[j] == 1){
                        queue.offer(j);
                    }
                }
            }
        }
        return list;
    }
}
