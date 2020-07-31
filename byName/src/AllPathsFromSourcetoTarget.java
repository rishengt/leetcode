import java.util.*;

/**
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Note:
 *
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class AllPathsFromSourcetoTarget {
    public static void main(String[] args) {
        System.out.println(new AllPathsFromSourcetoTarget().bfs(new int[][]{{1,2},{3},{3},{}}));
        System.out.println(new AllPathsFromSourcetoTarget().useDfs(new int[][]{{1,2},{3},{3},{}}));
        System.out.println(new AllPathsFromSourcetoTarget().useBackTrack(new int[][]{{1,2},{3},{3},{}}));
    }
    public List<List<Integer>> bfs(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(0));
        int end = graph.length-1;
        while(!queue.isEmpty()){
            List<Integer> temp = queue.poll();
            int cur = temp.get(temp.size()-1);
            if(cur == end) ans.add(new ArrayList<>(temp));
            for(int i : graph[cur]){
                List<Integer> path = new ArrayList<>(temp);
                path.add(i);
                queue.offer(path);
            }
        }
        return ans;
    }

/********************************************* dfs 太秀了 **************************************************************/
    public List<List<Integer>> useDfs(int[][] graph){
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans,0,graph.length-1,new ArrayList<>(),graph);
        return ans;
    }
    public void dfs(List<List<Integer>> ans, int start, int end, List<Integer> list, int[][] graph){
        list.add(start);/**这条list是什么。。。是由0⃣️开始一直到终点的list，传参简直妙不可言我日你奶奶个嘴*/
        if(start == end) ans.add(new ArrayList<>(list));
        for(int i : graph[start]){
            List<Integer> carbonCopy = new ArrayList<>(list);
            dfs(ans,i, end, carbonCopy,graph);
        }
    }

    /********************************************* 仔细想想确实就是一道回溯。。。。 **************************************************************/
    public List<List<Integer>> useBackTrack(int[][] graph){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        backtrack(graph,0,graph.length-1,ans,list);
        return ans;
    }

    public void backtrack(int[][] graph, int start, int end, List<List<Integer>> ans, List<Integer> temp){
        if(start == end){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i : graph[start]){
            temp.add(i);
            backtrack(graph,i,end,ans,temp);
            temp.remove(temp.size()-1);
        }
    }
}
