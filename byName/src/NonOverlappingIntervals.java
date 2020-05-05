import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * Example 2:
 *
 * Input: [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * Example 3:
 *
 * Input: [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {
    public static void main(String[] args) {
        System.out.println(new NonOverlappingIntervals().nonoverlapIntervals(new int[][]{{1,2},{2,3}}));
        System.out.println(new NonOverlappingIntervals().nonoverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}));
        System.out.println(new NonOverlappingIntervals().nonoverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
    }

    public int nonoverlapIntervals(int[][] intervals){
        if(intervals.length == 0) return 0;
        int ans = 1;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
            /**各种写法传comparator
             * (int[] a, int[] b) -> a[1]-b[1];
             * Comparator.comparingInt(int[] a) -> a[1];
             */
        });
        int end = intervals[0][1];
        for(int[] interval: intervals){
            int start = interval[0];
            if(start>=end){
                ans++;
                end = interval[1];
            }
        }
        return intervals.length-ans;
    }
}
