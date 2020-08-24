/**
 * Given an array arr that represents a permutation of numbers from 1 to n. You have a binary string of size n that initially has all its bits set to zero.
 *
 * At each step i (assuming both the binary string and arr are 1-indexed) from 1 to n, the bit at position arr[i] is set to 1.
 * You are given an integer m and you need to find the latest step at which there exists a group of ones of length m.
 * A group of ones is a contiguous substring of 1s such that it cannot be extended in either direction.
 *
 * Return the latest step at which there exists a group of ones of length exactly m. If no such group exists, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,5,1,2,4], m = 1
 * Output: 4
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "00101", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "11101", groups: ["111", "1"]
 * Step 5: "11111", groups: ["11111"]
 * The latest step at which there exists a group of size 1 is step 4.
 * Example 2:
 *
 * Input: arr = [3,1,5,4,2], m = 2
 * Output: -1
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "10100", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "10111", groups: ["1", "111"]
 * Step 5: "11111", groups: ["11111"]
 * No group of size 2 exists during any step.
 * Example 3:
 *
 * Input: arr = [1], m = 1
 * Output: 1
 * Example 4:
 *
 * Input: arr = [2,1], m = 2
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == arr.length
 * 1 <= n <= 10^5
 * 1 <= arr[i] <= n
 * All integers in arr are distinct.
 * 1 <= m <= arr.length
 */
import java.util.*;
public class FindLatestGroupofSizeM {

    public static void main(String[] args) {
        System.out.println(new FindLatestGroupofSizeM().findLatestStep(new int[]{3,5,1,2,4},1));
        System.out.println(new FindLatestGroupofSizeM().findLatestStep(new int[]{3,5,1,2,4},2));
    }
    /**
     count[i] means the length of the group.
     When we set bit a, where a = A[i],
     we check the length of group on the left length[a - 1]
     also the length of group on the right length[a + 1].
     Then we update length[a], length[a - left], length[a + right] to left + right + 1.

     Note that the length value is updated on the leftmost and the rightmost bit of the group.
     The length value inside the group may be out dated.

     As we do this, we also update the count of length.
     If count[m] > 0, we update res to current step index i + 1.


     Complexity
     Time O(N)
     Space O(N)
     */
    public int findLatestStep(int[] arr, int m) {
        int res = -1, n = arr.length;
        /**length用来模拟bit的变化*/      /**count【i】代表group长度为i出现的次数*/
        int[] length = new int[n + 2], count = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            /**这一行勉强可以理解，左看右看嘛*/
            int a = arr[i], left = length[a - 1], right = length[a + 1];
            /**这里就很精髓了，过了几个小时就忘了，重点照顾一下。Note that the length value is updated on the leftmost and the rightmost bit of the group.
             The length value inside the group may be out dated. 嘛其lee215大神已经说的很清楚明白了*/
            length[a] = length[a - left] = length[a + right] = left + right + 1;
            count[left]--;
            count[right]--;
            count[length[a]]++;
            if (count[m] > 0)
                res = i + 1;
        }
        return res;
    }

    public int findLatestStepII(int[] arr, int m) {
        int res = -1, n = arr.length;
        Map<Integer, Integer> cl = new HashMap<>(), fl = new HashMap<>();  // length of ceiling and floor to glue
        int[] ls = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int pos = arr[i], ceil = cl.getOrDefault(pos + 1, 0), floor = fl.getOrDefault(pos - 1, 0), newLen = ceil + floor + 1;
            if (ceil > 0) ls[ceil]--;
            if (floor > 0) ls[floor]--;
            cl.put(pos - floor, newLen);  // new range;
            fl.put(pos + ceil, newLen);
            ls[newLen]++;  // new range length;
            if (ls[m] > 0) res = i + 1;  // last
        }
        return res;
    }
}
