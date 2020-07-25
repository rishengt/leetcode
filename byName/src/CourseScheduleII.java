import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseScheduleII {
    public static void main(String[] args) {
        int[] k = new CourseScheduleII().findOrder(2, new int[][]{{1,0}});
        for(int i: k){
            System.out.println(i);
        }
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] G = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        ArrayList<Integer> bfs = new ArrayList();
        for (int i = 0; i < numCourses; ++i) G[i] = new ArrayList<Integer>();
        for (int[] e : prerequisites) {
            G[e[1]].add(e[0]);/**这里表明你上e[0]之前要上e[1], 单向图，只加一次*/
            degree[e[0]]++;/**这个degree是什么？？degree代表了e[0]这个prerequistes被需要的次数*/
        }
        for (int i = 0; i < numCourses; ++i) {
            if (degree[i] == 0) bfs.add(i);/**你要是没有prerequisite就可以直接上了*/
        }
        for (int i = 0; i < bfs.size(); ++i)
            for (int j: G[bfs.get(i)])/**层次遍历每节课所需要的prerequisite*/
                if (--degree[j] == 0) bfs.add(j);/**都上完了就加进去*/
        if (bfs.size() != numCourses){
            return new int[]{};
        }
        int[] ans = new int[numCourses];
        for(int i = 0; i< bfs.size(); i++){
            ans[i] = bfs.get(i);
        }
        return ans;
    }
}
