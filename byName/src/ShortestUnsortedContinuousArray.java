import java.util.Arrays;

/**
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 */
public class ShortestUnsortedContinuousArray {
    public static void main(String[] args) {
        System.out.println(new ShortestUnsortedContinuousArray().shortest(new int[]{1,2,3,4}));
    }




    /**********************想法很美好但是 [1,3，2，2，2] 过不了 *********************/
    public int shortest(int[] A){
        if(A.length<=1) return 0;
        int i = 1, j = A.length-2;
        while(i<A.length) {
            if (A[i] < A[i - 1]) break;
            i++;
        }
        if(i == A.length) return 0;
        while(j>=0){
            if(A[j]>A[j+1]) break;
            j--;
        }
        return j+1-(i-1)+1;
    }
}
