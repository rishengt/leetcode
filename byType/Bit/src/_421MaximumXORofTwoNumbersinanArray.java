/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 *
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 *
 * Could you do this in O(n) runtime?
 *
 * Example:
 *
 * Input: [3, 10, 5, 25, 2, 8]
 *
 * Output: 28
 *
 * Explanation: The maximum result is 5 ^ 25 = 28.
 */

import java.util.HashSet;
import java.util.Set;

public class _421MaximumXORofTwoNumbersinanArray {
    public static void main(String[] args) {
        System.out.println(new _421MaximumXORofTwoNumbersinanArray().findMaximumXOR(new int[]{3,10,5,25,2,8}));
    }
    /**这道神仙题总体思路就是 a ^ b = c, a ^ c = b,如果我们知道最大的a 跟 c，而数组中又有b， 那么我们就找到了答案，可以直接返回c*/
    /**问题在于我们不知道c，所以我们要从头开始一位一位地找，
     *
     */
    public int findMaximumXOR(int[] nums) {
       int mask = 0, max = 0;
       for(int i = 5; i>=0; i--){
           /**这是很神奇的一个命令，他会把你的1一位一位的累加，想想一下*/
           mask |= (1<<i);
           String k = Integer.toBinaryString(mask);
           Set<Integer> set = new HashSet<>();
           for(int num: nums){
               /**这是什么东西，东西的含义就是我们要从自身情况出发，来找到最大的面具？？？*/
               num &= mask;
               String b = Integer.toBinaryString(num);
               set.add(num);
           }
           int greedyTry =  max|(1<<i);
           String g = Integer.toBinaryString(greedyTry);
           for(int realMask: set){
               int another = realMask ^ greedyTry;
               String a = Integer.toBinaryString(another);
               if(set.contains(another)){
                   max = greedyTry;
                   break;
               }
           }
       }
       return max;
    }
}
