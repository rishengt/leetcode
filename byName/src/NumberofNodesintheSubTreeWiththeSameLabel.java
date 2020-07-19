import java.util.ArrayList;

/**
 * Given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
 * The root of the tree is the node 0, and each node of the tree has a label which is a lower-case character given in the string labels
 * (i.e. The node with the number i has the label labels[i]).
 *
 * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.
 *
 * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.
 *
 * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
 * Output: [2,1,1,1,1,1,1]
 * Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2.
 * Notice that any node is part of its sub-tree.
 * Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1,
 * the answer is just 1 (the node itself).
 * Example 2:
 *
 *
 * Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
 * Output: [4,2,1,1]
 * Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
 * The sub-tree of node 3 contains only node 3, so the answer is 1.
 * The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
 * The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.
 * Example 3:
 *
 *
 * Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
 * Output: [3,2,1,1,1]
 * Example 4:
 *
 * Input: n = 6, edges = [[0,1],[0,2],[1,3],[3,4],[4,5]], labels = "cbabaa"
 * Output: [1,2,1,1,2,1]
 * Example 5:
 *
 * Input: n = 7, edges = [[0,1],[1,2],[2,3],[3,4],[4,5],[5,6]], labels = "aaabaaa"
 * Output: [6,5,4,1,3,2,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * labels.length == n
 * labels is consisting of only of lower-case English letters.
 */
public class NumberofNodesintheSubTreeWiththeSameLabel {
    public static void main(String[] args) {
        int[] ans = new NumberofNodesintheSubTreeWiththeSameLabel().countSubTrees(4,new int[][]{{0,1},{1,2},{0,3}},"bbbb");
    }
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] temp: edges){
            graph[temp[0]].add(temp[1]);
            graph[temp[1]].add(temp[0]);
        }
        boolean[] visited = new boolean[n];/**一直到这一行都是普通的建图*/
        int[] ans = new int[n];
        dfs(graph,0,labels,ans,new boolean[n]);
        return ans;
    }

    public int[] dfs(ArrayList<Integer>[] graph, int node, String labels, int[] ans, boolean[]seen){
        int[] cnt = new int[26];/**记录字母出现次数的工具array*/
        if(!seen[node]){
            seen[node] = true;/**因为这玩意儿是无向图，防回头用的*/
            char c = labels.charAt(node);
            for(int child: graph[node]){
                int sub[] = dfs(graph,child,labels,ans,seen);/**这个dfs是用来干嘛的呢？它是用来记录每个node的subtree到底包含了几个这个node的label的*/
                for (int i = 0; i < 26; ++i) {/**估计我自己是写不出这里的forloop的吧*/
                    cnt[i] += sub[i];/**不知道我理解得对不对，这里反而类似于于层次遍历了，你到达一个node的时候，把它所有的child的subtree包含的label都记录下来*/
                }
            }
            ++cnt[c - 'a'];
            ans[node] = cnt[c - 'a'];
        }
        return cnt;
    }
}
