import java.util.ArrayList;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 */
public class CourseSchedule {
    public static void main(String[] args) {
        System.out.println(new CourseSchedule().canFinish(3,new int[][]{{1,0},{2,0}}));
    }
    /*************哇塞！！！Topological sort是第一次见耶！！！！ 个人感觉用DFS是正统解决Topological sort 的解法，多做题来验证吧！ ****************/

    /**************************BFS***********************************/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] G = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        ArrayList<Integer> bfs = new ArrayList();
        for (int i = 0; i < numCourses; ++i) G[i] = new ArrayList<Integer>();
        for (int[] e : prerequisites) {
            G[e[1]].add(e[0]);/**这里表明你上e[1]之前要上e[0], 单向图，只加一次*/
            degree[e[0]]++;/**这个degree是什么？？degree代表了e[0]这个prerequistes被需要的次数*/
        }
        for (int i = 0; i < numCourses; ++i) {
            if (degree[i] == 0) bfs.add(i);/**你要是没有prerequisite就可以直接上了*/
        }
        for (int i = 0; i < bfs.size(); ++i)
            for (int j: G[bfs.get(i)])/**层次遍历每节课所需要的prerequisite*/
                if (--degree[j] == 0) bfs.add(j);/**都上完了就加进去*/
        return bfs.size() == numCourses;
    }

    /**************************DFS**********************************/
    public boolean canFinishII(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        boolean[] visited = new boolean[numCourses];
        for(int i=0; i<prerequisites.length;i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for(int i=0; i<numCourses; i++){
            if(!dfs(graph,visited,i))
                return false;
        }
        return true;
    }

    private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
        if(visited[course])
            return false;
        else
            visited[course] = true;;

        for(int i=0; i<graph[course].size();i++){
            if(!dfs(graph,visited,(int)graph[course].get(i)))
                return false;
        }
        visited[course] = false;
        return true;
    }
}
