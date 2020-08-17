/**
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 *
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 *
 * Return the length of a maximum size turbulent subarray of A.
 *
 *
 *
 * Example 1:
 * Input: [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
 *
 * Example 2:
 * Input: [4,8,12,16]
 * Output: 2
 *
 * Example 3:
 * Input: [100]
 * Output: 1
 *
 *
 * Note:
 *
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 */

/***** 在得知你可以用Integer.compare(a,b) 这个情报以后，你还写不出来那你就是弱智了啊兄弟*/
public class LongestTurbulentSubarray {
    public static void main(String[] args) {
        System.out.println(new LongestTurbulentSubarray().maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}));
        System.out.println(new LongestTurbulentSubarray().maxTurbulenceSize(new int[]{4,8,12,16}));
    }

    /**
     * For each A[i]
     * inc: The length of current valid sequence which ends with two increasing numbers
     * dec: The length of current valid sequence which ends with two decreasing numbers
     */

    /**我艹，这个神一般的写法让我想起了那道数学题。NumberOfSubarraysWithOddSum...... */
    public int maxTurbulenceSize(int[] A) {
        int inc = 1, dec = 1, result = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                dec = inc + 1;
                inc = 1;
            } else if (A[i] > A[i - 1]) {
                inc = dec + 1;
                dec = 1;
            } else {
                inc = 1;
                dec = 1;
            }
            result = Math.max(result, Math.max(dec, inc));
        }
        return result;
    }


    /******************* 虽然勉强写出来了但是太捞了，优化优化呗傻叼。。。。。。 *****************************************/
    public int maxTurbulenceSizeII(int[] A) {
        int ans = 0;
        for(int i = 0; i<A.length-1; i++) {
            int sign = 0;
            int temp = 1;
            int pre = A[i];
            for (int j = i + 1; j < A.length; j++) {
                int c = Integer.compare(pre, A[j]);
                if (c != 0 && c != sign) {
                    temp++;
                    ans = Math.max(ans,temp);
                    pre = A[j];
                    sign = c;
                }
                else{
                    break;
                }
            }
        }
        return ans;
    }
}
