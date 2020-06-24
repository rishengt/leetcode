import java.util.*;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 *
 *
 *
 *
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 *
 *
 *
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 *
 *
 *
 *
 * Explanation
 * Sort the jobs by endTime.
 *
 * dp[time] = profit means that within the first time duration,
 * we cam make at most profit money.
 * Intial dp[0] = 0, as we make profit = 0 at time = 0.
 *
 * For each job = [s, e, p], where s,e,p are its start time, end time and profit,
 * Then the logic is similar to the knapsack problem.
 * If we don't do this job, nothing will be changed.
 * If we do this job, binary search in the dp to find the largest profit we can make before start time s.
 * So we also know the maximum cuurent profit that we can make doing this job.
 *
 * Compare with last element in the dp,
 * we make more money,
 * it worth doing this job,
 * then we add the pair of [e, cur] to the back of dp.
 * Otherwise, we'd like not to do this job.
 *
 *
 * Complexity
 * Time O(NlogN) for sorting
 * Time O(NlogN) for binary search for each job
 * Space O(N)
 */
public class MaximumProfitOfJobScheduling {

    public static void main(String[] args) {
        System.out.println(new MaximumProfitOfJobScheduling().jobSchedulingII(new int[]{1,2,3,4,6}, new int[]{3,5,10,6,9}, new int[]{20,40,100,70,60}));
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
// sort by endTime
        int[][] items = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            items[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(items, (a1, a2)->a1[1] - a2[1]);
        List<Integer> dpEndTime = new ArrayList<>();
        List<Integer> dpProfit = new ArrayList<>();
        // init value to avoid IndexOutBoundExp
        dpEndTime.add(0);
        dpProfit.add(0);
        for (int[] item : items) {
            int s = item[0], e = item[1], p = item[2];
            // find previous endTime index
            int prevIdx = Collections.binarySearch(dpEndTime, s + 1);
            if (prevIdx < 0) {
                prevIdx = -prevIdx - 1;
            }
            prevIdx--;
            int currProfit = dpProfit.get(prevIdx) + p, maxProfit = dpProfit.get(dpProfit.size() - 1);
            if (currProfit > maxProfit) {
                dpProfit.add(currProfit);
                dpEndTime.add(e);
            }
        }
        return dpProfit.get(dpProfit.size() - 1);
    }

/*******************************************Using HashMap*******************************************************************/
public int jobSchedulingII(int[] startTime, int[] endTime, int[] profit) {
    int n = startTime.length;
    int[][] jobs = new int[n][3];
    for (int i = 0; i < n; i++) {
        jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
    }
    Arrays.sort(jobs, (a, b)->a[1] - b[1]);
    TreeMap<Integer, Integer> dp = new TreeMap<>();
    dp.put(0, 0);/**这里给初始值，凡是dp大概如此。。。逻辑代表请看上面解析。。。。*/
    for (int[] job : jobs) {
        int cur = dp.floorEntry(job[0]).getValue() + job[2];/***这个floorEntry是非常有讲究的，细品！！！应为有了初始值所以不会返回null，
         找不到这个key的时候就returns the entry for the greatest key less than the specified
         key，也就是说要是刚好有首尾相接的两个time period它会帮你找出来*/
        if (cur > dp.lastEntry().getValue())
            dp.put(job[1], cur);
        System.out.println(dp.size());
    }
    return dp.lastEntry().getValue();
}
}
