import java.util.Arrays;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 */
public class MeetingRoom {

    public static void main(String[] args) {
        System.out.println(new MeetingRoom().canMeet(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(new MeetingRoom().canMeet(new int[][]{{7,10},{2,4}}));
    }

    public boolean canMeet(int[][] intervals){
        if(intervals.length <= 1) return true;
        Arrays.sort(intervals, (int[] a, int[] b) -> a[1]-b[1]);/***系列的精髓就在这一行排序上面了**/
        int end = intervals[0][1];
        for(int i = 1; i< intervals.length; i++){
            if(intervals[i][0]<end) return false;
        }
        return true;
    }
}
