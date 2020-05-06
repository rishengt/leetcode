import java.util.Arrays;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 */

public class MeetingRoomII {
    public static void main(String[] args) {
        System.out.println(new MeetingRoomII().minRoon(new int[][]{{0, 30},{5, 10},{15, 20}}));
        System.out.println(new MeetingRoomII().minRoon(new int[][]{{7,10},{2,4}}));
    }
    public int minRoon(int[][] intervals){
        if(intervals.length == 0) return 0;
        int ans = 1;/**至少一间房*/
        Arrays.sort(intervals, (int[] a, int[] b) -> a[1]-b[1]);/***勿忘精髓*/
        int end = intervals[0][1];
        for(int i = 1; i< intervals.length; i++){
            if(intervals[i][0]<end){
                ans++;
                end = intervals[i][1];/**勿忘迭代*/
            }
        }
        return ans;
    }
}
