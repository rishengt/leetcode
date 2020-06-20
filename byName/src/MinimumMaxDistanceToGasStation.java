/**
 *On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
 *
 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
 *
 * Return the smallest possible value of D.
 *
 * 1.stations.length will be an integer in range [10, 2000].
 * 2.stations[i] will be an integer in range [0, 10^8].
 * 3.K will be an integer in range [1, 10^6]. 4.Answers within 10^-6 of the true value will be accepted as correct.
 * 样例
 *
 * Example 1:
 * Input：stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]，K = 9
 * Output：0.50
 * Explanation：The distance between adjacent gas stations is 0.50
 *
 * Example 2:
 * Input：stations = [3,6,12,19,33,44,67,72,89,95]，K = 2
 * Output：14.00
 * Explanation：construction of gas stations at 86 locations
 */
public class MinimumMaxDistanceToGasStation {
    public static void main(String[] args) {
        System.out.println(new MinimumMaxDistanceToGasStation().minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},9));
    }
    public double minmaxGasDist(int[] stations, int k) {
        // Write your code here
        int count, N = stations.length;
        double left = 0, right = stations[N - 1] - stations[0]; //初始化左右边界

        while (right - left > 1e-6) {
            double mid = (left + right) / 2;
            count = 0;
            for (int i = 0; i < N - 1; i++)
                count += Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;  //统计加油站数量
            if (count > k) {			//加油站距离增大
                left = mid;
            }
            else {						//加油站距离减小
                right = mid;
            }
        }
        return right;
    }
}
