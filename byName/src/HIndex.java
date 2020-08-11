/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 *
 * Example:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 *              received 3, 0, 6, 1, 5 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
import java.util.*;
public class HIndex {
    public static void main(String[] args) {
        System.out.println(new HIndex().hIndex(new int[]{2,1,2,6,1,8,10,10}));
    }

    /** 精简而优雅，啥时候才能写出这种code啊。。。。*/
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int index = 1;
        int N = citations.length;

        while(index <= N) {
            if(citations[N - index] >= index) {/**这个仔细一想是往后便利的，确实这样能最快的找到合适的答案啊！！！*/
                index++;
            } else {
                break;
            }
        }

        return index - 1;
    }

    /**虽然看着提示做出来了。。。。但是这道题目的本质是什么？？*/
    public int hIndexII(int[] citations) {
        if(citations.length == 0 || citations == null) return 0;
        Queue<Integer> queue = new PriorityQueue<>();
        int n = citations.length;
        Arrays.sort(citations);
        for(int i = 0; i<citations.length; i++){
            if(citations[i] >=n){
                queue.offer(n-i);
            }
            else if(n-i<=citations[i]){
                queue.offer(n-i);
            }
            while(queue.size()>1)queue.poll();
        }
        if(queue.size()<=0) return 0;
        return queue.poll();
    }
}
