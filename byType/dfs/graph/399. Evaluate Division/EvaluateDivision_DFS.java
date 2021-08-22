import java.util.*;

/**
 * You are given equations in the Date.format A / B = k, where A and B are variables represented as strings, and k is a real number (floating-point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * Constraints:
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= equations[i][0], equations[i][1] <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= queries[i][0], queries[i][1] <= 5
 * equations[i][0], equations[i][1], queries[i][0], queries[i][1] consist of lower case English letters and digits.
 */
public class EvaluateDivision_DFS {
    class pair{
        String s;
        double d;
        public pair(String s, double d){
            this.s = s;
            this.d = d;
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = values.length;
        double ans[] = new double[n];
        Map<String, List<pair>> graph = new HashMap<>();
        for(int i = 0; i<n; i++){
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            pair p1 = new pair(b,values[i]);
            pair p2 = new pair(a, 1.0/values[i]);
            graph.putIfAbsent(a,new ArrayList<>());
            graph.putIfAbsent(b,new ArrayList<>());
            graph.get(a).add(p1);
            graph.get(b).add(p2);
        }
        for(int i = 0; i<queries.size(); i++){
            Set<String> visited = new HashSet<>();
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if(!graph.containsKey(a) || !graph.containsKey(b)){
                ans[i] = -1.0;
                continue;
            }
            double k = dfs(a,b,visited,graph);
            ans[i] = k;
        }
        return ans;
    }

    public double dfs(String a, String b, Set<String> visited, Map<String, List<pair>> graph){
        if(a.equals(b)) return 1.0;
        for(pair c : graph.get(a)){
            if(visited.contains(c.s))continue;
            visited.add(c.s);
            double val1 = c.d;
            double val2 = dfs(c.s,b,visited,graph);
            if(val2 != -1.0) return val1 * val2;
        }
        return -1.0;
    }
}

