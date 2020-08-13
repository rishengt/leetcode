/**
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 *
 * If there is no non-empty subarray with sum at least K, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1], K = 1
 * Output: 1
 * Example 2:
 *
 * Input: A = [1,2], K = 4
 * Output: -1
 * Example 3:
 *
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 *
 *
 * Note:
 *
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 */
import java.util.*;
public class MinimumSizeSubarraySumII_ShortestSubarraywithSumatLeastK {
    public static void main(String[] args) {
        System.out.println(new MinimumSizeSubarraySumII_ShortestSubarraywithSumatLeastK().shortestSubarray(new int[]{2,-1,2},3));
    }
    /**这道题跟上一道题最大的区别就是这道题有负数！！ 负数！！！！！！！！！！！*/
    /**这道题目本质是单调que， monoq，让我想起LargestRectangleinHistogram（84）单调栈了。。都他妈好难我日*/
    public int shortestSubarray(int[] A, int K) {
        Deque<Integer> q = new LinkedList<>();
        int prefixSum[] = new int[A.length+1];
        for(int i = 0; i<A.length; i++){
            prefixSum[i+1] = A[i] + prefixSum[i];
        }
        int ans = A.length+1;/**这么设置就是因为不可能达到，要是不可能达到那么就返回-1；*/
        for(int i = 0; i<prefixSum.length; i++){
            /**这里弹出的是sum比现在的大的东西，这个可以理解因为你加进去以后是要被后来的sum减掉的，如果你越大，那么减掉你以后得到的数就越小，满足大于等于K
             * 的可能性就为之减少，所以可以去掉。
             * 还有一个原因就是，你先我一步进去，证明你的index比我小，后面的index减你这个index得到的长度就比我长，不行。年老又色衰。
             * 一直弹到有一个比我小的为止。
             */
            while(!q.isEmpty() && prefixSum[i]<=prefixSum[q.getLast()]){
                q.removeLast();
            }
            /**这里的话就是要更新了，先判断现在进来的能不能满足条件，要是满足就更新一波长度。
             * 然后看下一个，弹到不满足为止。
             */
            while(!q.isEmpty() && prefixSum[i]>=prefixSum[q.getFirst()]+K){
                ans = Math.min(i-q.removeFirst(),ans);
            }
            q.addLast(i);
        }
        return ans<A.length+1?ans:-1;
    }

    /**
     * PrefixSum[0...i] <= PrefixSum[0....j] - K
     * i as big as possible/ j as small as possible
     *
     * deque[ PrefixSum(a), PrefixSum(b),PrefixSum(c) || PrefixSum(d), PrefixSum(e),PrefixSum(f)]    PrefixSum(j)
     */
}
