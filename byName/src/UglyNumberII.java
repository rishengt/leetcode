import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Example:
 *
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note:
 *
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 */
public class UglyNumberII {
    public static void main(String[] args) {
        System.out.println(new UglyNumberII().nthUglyNumber(20));
    }
    public int nthUglyNumber(int n) {
            Queue<Long> queue = new PriorityQueue<>();
            HashSet<Long> set = new HashSet<>();
            if(n == 1) return 1;
            queue.offer(1L);
            set.add(1L);
            for(int i = 0; i<n-1; i++){
                Long temp = queue.poll();
                Long temp1 = temp*2;
                Long temp2 = temp*3;
                Long temp3 = temp*5;
                if(set.add(temp1))queue.offer(temp1);
                if(set.add(temp2))queue.offer(temp2);
                if(set.add(temp3))queue.offer(temp3);
            }
            return queue.poll().intValue();
    }

    public int nthUglyNumberII(int n) {
        TreeSet<Long> ans = new TreeSet<>();
        ans.add(1L);
        for (int i = 0; i < n - 1; ++i) {
            long first = ans.pollFirst();
            ans.add(first * 2);
            ans.add(first * 3);
            ans.add(first * 5);
        }
        return ans.first().intValue();
    }
}
