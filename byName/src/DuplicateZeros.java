/**
 * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written.
 *
 * Do the above modifications to the input array in place, do not return anything from your function.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,0,2,3,0,4,5,0]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * Example 2:
 *
 * Input: [1,2,3]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 *
 *
 * Note:
 *
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 */
public class DuplicateZeros {
    public static void main(String[] args) {
        new DuplicateZeros().duplicateZeros(new int[]{1,0,2,3,0,4,5,0});
    }
    /**两条for 你小子写出来了，但最优解你看完思路以后自己写写呗**/
    public void duplicateZeros(int[] arr) {
        if ( arr == null || arr.length == 0)
            return;
        int n = arr.length;
        // Step 1: Loop through the array
        int numZeroes = 0;

        for (int i =0; i  <= n-1-numZeroes; i++){/**我操这条for loop还能放个实时变化的变量进去，领教了领教了*/
            if ( arr[i] == 0 ){
                // boundary
                if ( i == n-1-numZeroes){
                    arr[n-1] = 0;
                    n--;
                    break;
                }
                numZeroes++;
            }
        }

        // Step 2: From last, fill two zeroes for every zero

        int i = n-1-numZeroes;
        int j = n-1;

        for ( ; i >= 0 ; i--){

            if ( arr[i] == 0){
                arr[j] = 0;
                j--;
                arr[j] = 0;
            } else {
                arr[j]= arr[i];
            }
            j--;
        }
    }
}
