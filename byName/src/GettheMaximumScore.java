import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 *
 * A valid path is defined as follows:
 *
 * Choose array nums1 or nums2 to traverse (from index-0).
 * Traverse the current array from left to right.
 * If you are reading any value that is present in nums1 and nums2 you are allowed to change your path to the other array.
 * (Only one repeated value is considered in the valid path).
 * Score is defined as the sum of uniques values in a valid path.
 *
 * Return the maximum score you can obtain of all possible valid paths.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * Output: 30
 * Explanation: Valid paths:
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],  (starting from nums1)
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]    (starting from nums2)
 * The maximum is obtained with the path in green [2,4,6,8,10].
 * Example 2:
 *
 * Input: nums1 = [1,3,5,7,9], nums2 = [3,5,100]
 * Output: 109
 * Explanation: Maximum sum is obtained with the path [1,3,5,100].
 * Example 3:
 *
 * Input: nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
 * Output: 40
 * Explanation: There are no common elements between nums1 and nums2.
 * Maximum sum is obtained with the path [6,7,8,9,10].
 * Example 4:
 *
 * Input: nums1 = [1,4,5,8,9,11,19], nums2 = [2,3,4,11,12]
 * Output: 61
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length <= 10^5
 * 1 <= nums2.length <= 10^5
 * 1 <= nums1[i], nums2[i] <= 10^7
 * nums1 and nums2 are strictly increasing.
 */
public class GettheMaximumScore {
    public static void main(String[] args) {
        GettheMaximumScore g = new GettheMaximumScore();
        System.out.println(g.maxSumII(new int[]{2,4,5,8,10}, new int[]{3,6,8,9}));
//        System.out.println(g.maxSum(new int[]{1,2,3,4,5}, new int[]{6,7,8,9,10}));
    }

    /******************************************还能说什么呢， lee215 大神牛逼呗，话说我真的有看懂吗？？**************************/
    public int maxSum(int[] A, int[] B) {
        int i = 0, j = 0, n = A.length, m = B.length;
        long a = 0, b = 0, res = 0, mod = (long)1e9 + 7;
        while (i < n || j < m) {
            if (i < n && (j == m || A[i] < B[j])) { /**这种对比大小的写法是一种非常精妙的手段去把各自路径上不同的点分别加到各自在交汇点之前的和*/
                a += A[i++];
            } else if (j < m && (i == n || A[i] > B[j])) {
                b += B[j++];
            } else { /**这种情况是遇到交汇点了，你得把交汇点之前的路径对比一下然后加上交汇点，更新起点*/
                res += Math.max(a, b) + A[i];
                i++; j++;
                a = 0; b = 0;
            }
        }
        return (int)((res + Math.max(a, b)) % mod);
    }

    /******************************************* 这个方法也是妙不可言， 而且相对而言方便理解 ***********************************/
    public int maxSumII(int[] nums1, int[] nums2) {
        long mod = 1000000007;
        Map<Integer, Long> hm1 = new HashMap();
        Map<Integer, Long> hm2 = new HashMap();
        hm1.put(0, 0L);
        long cum = 0;
        long ans = 0;
        for(int i: nums1){
            cum += i;
            hm1.put(i, cum);
        }
        hm2.put(0, 0L);
        cum = 0;
        for(int i: nums2){
            cum += i;
            hm2.put(i, cum);
        }
        int prev = 0;
        for(int i: nums1){
            if(hm2.containsKey(i)){
                ans += Math.max(hm2.get(i) - hm2.get(prev) , hm1.get(i) - hm1.get(prev));
                prev = i;
            }
        }
        ans += Math.max(hm1.get(nums1[nums1.length-1]) - hm1.get(prev), hm2.get(nums2[nums2.length - 1]) - hm2.get(prev));
        return (int)(ans % mod);
    }
}
