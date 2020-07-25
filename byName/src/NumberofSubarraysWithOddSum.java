/**
 * Given an array of integers arr. Return the number of sub-arrays with odd sum.
 *
 * As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All sub-arrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 * Example 2:
 *
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All sub-arrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6,7]
 * Output: 16
 * Example 4:
 *
 * Input: arr = [100,100,99,99]
 * Output: 4
 * Example 5:
 *
 * Input: arr = [7]
 * Output: 1
 */
public class NumberofSubarraysWithOddSum {
    /**
     * If we know the number of even and odd subarrays that end at the previous element, we can figure out how many even and odd subarrays
     * we have for element n:
     *
     * If n is even, we increase the number of even subarrays; the number of odd subarrays does not change.
     * If n is odd, the number of odd subarrays is the previous number of even subarrays + 1.
     * The number of even subarrays is the previous number of odd subarrays.
     * Looking at this example:
     *
     * Array: [1, 1, 2, 1]  Total
     * Odd:    1  1  1  3     6
     * Even:   0  1  2  1
     */

    /***这没什么好说的，一道技巧题，暴力解决会TLE*/
    public int numOfSubarrays(int[] arr) {
        int sum = 0;
        for (int i = 0, odd = 0; i < arr.length; ++i) {
            if (arr[i] % 2 == 1)
                odd = (i - odd) + 1;
            sum = (sum + odd)  % 1000000007;
        }
        return sum;
    }

    /**
     * count odd and even prefix sums,
     * everytime we met a new prefix sum, we check previous prefix sums, and the difference will be the sum of subarrays.
     * For example, if we met an new even prefix sum, any previous odd prefix sum will form a new subarray with odd sum, and vice versa.
     *
     * 奇数+奇数 = 偶数
     * 偶数+偶数 = 偶数
     * 奇数+偶数 = 奇数
     *
     *  * Array: [1, 1, 2，1 ] Total
     *  * Odd:    1        2
     *  * Even:      2  3
     *    res ：  1  2  3  6
     */
    public int numOfSubarraysII(int[] arr) {
        int odd = 0, even = 1, res = 0, sum = 0, mod = 1_000_000_007; // treat empty subarray as even 把0当偶数
        for (int n : arr) {
            sum += n;
            if (sum % 2 == 0) {
                even++;
                res = (res + odd) % mod;
            } else {
                odd++;/**当你当前的prefix sum是 奇数时，奇数总数++，没什么好说的*/
                res = (res + even) % mod;/**但是为了确保你的答案是正确的，你要借助even，至于为什么，请看上面， 虽然看了也不懂，草！！！！*/
            }
        }
        return res;
    }
}
