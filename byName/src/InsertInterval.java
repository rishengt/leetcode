/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
import java.util.*;
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> intervalList = new ArrayList<>();
        for(int[] interval: intervals) intervalList.add(interval);
        intervalList.add(newInterval);
        Collections.sort(intervalList, (a,b) -> a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        List<int[]> merged = new ArrayList<>();
        for(int[] interval: intervalList){
            if(merged.isEmpty() || merged.get(merged.size()-1)[1]<interval[0]){
                merged.add(interval);
            }else{
                merged.get(merged.size()-1)[1] = Math.max(interval[1],merged.get(merged.size()-1)[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**这种高级写法面试的时候写不出来的吧。。。。。。。*/
    public int[][] insertII(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<int[]>();
        if (intervals == null || intervals.length == 0) {
            int[][] ans = new int[1][2];
            ans[0] = newInterval;
            return ans;
        }
        int index = 0;
        while (index < intervals.length && newInterval[0] > intervals[index][1]) {
            res.add(intervals[index]);
            index++;
        }
        while (index < intervals.length && newInterval[1] >= intervals[index][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            index++;
        }
        res.add(newInterval);
        while (index < intervals.length) {
            res.add(intervals[index]);
            index++;
        }
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
