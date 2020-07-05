import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given an array of numbers arr. A sequence of numbers is called an arithmetic progression if the difference between any two consecutive
 * elements is the same.
 *
 * Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,5,1]
 * Output: true
 * Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
 * Example 2:
 *
 * Input: arr = [1,2,4]
 * Output: false
 * Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
 */
public class CanMakeArithmeticProgressionFromSequence {
    public static void main(String[] args) {
        System.out.println(new CanMakeArithmeticProgressionFromSequence().canMakeArithmeticProgression(new int[]{3,5,1}));
        System.out.println(new CanMakeArithmeticProgressionFromSequence().canMakeArithmeticProgression(new int[]{1,2,4}));
    }
    public boolean canMakeArithmeticProgression(int[] arr) {
        if(arr.length<=1) return true;
        Arrays.sort(arr);
        return isFit(arr);
    }

    public boolean isFit(int[] arr){
        int difference = arr[0] - arr[1];
        for(int i = 0; i< arr.length-1; i++){
            if(arr[i]-arr[i+1] != difference) return false;
        }
        return true;
    }
}
