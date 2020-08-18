/**
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 *
 * Note that every number in the answer must not have leading zeros except for the number 0 itself.
 * For example, 01 has one leading zero and is invalid, but 0 is valid.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * Example 2:
 *
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 *
 * Note:
 *
 * 1 <= N <= 9
 * 0 <= K <= 9
 */
import java.util.*;
public class NumbersWithSameConsecutiveDifferences {
    public static void main(String[] args) {
//        System.out.println(new NumbersWithSameConsecutiveDifferences().numsSameConsecDiff(3,7));
        System.out.println(new NumbersWithSameConsecutiveDifferences().numsSameConsecDiff(2,1));
    }
    public int[] numsSameConsecDiff(int N, int K) {
        if(N == 1) return new int[]{0,1,2,3,4,5,6,7,8,9};
        HashSet<Integer> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        for(int i = 1; i<=9; i++){
            makeNumber(i,N,K,ans,"",set);
        }
        int[] array = new int[ans.size()];
        for(int i = 0; i<array.length;i++){
            array[i] = ans.get(i);
        }
        return array;
//        return ans;
    }
    public void makeNumber(int i, int N, int K, List<Integer> ans, String temp, HashSet<Integer> set){
        temp += String.valueOf(i);
        if(temp.length() == N && set.add(Integer.valueOf(temp))){
            ans.add(Integer.valueOf(temp));
            return;
        }
        for(int j = 0; j<=9; j++){
            if(Math.abs(i-j) == K){
                makeNumber(j,N,K,ans,temp,set);
            }
        }
        temp = temp.substring(0,temp.length()-1);
    }
}
