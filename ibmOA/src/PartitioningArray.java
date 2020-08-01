import java.util.HashMap;

/**
 * Given an array of numbers, you are required to check if it is possible to partition the array into some subsequences of length k each, such that:
 *      Each element in the array occurs in exactly one subsequence
 *      All the numbers in a subsequence are distinct
 *      Elements in the array having the same value must be in different subsequences
 * Is possible to partition the array satisfying the above condition? If it is possible, return "Yes", else return "No".
 *
 * For example, suppose:
 *      There are n = 4 numbers in the array
 *      The length of each subsequence needs to be k = 2.
 *      The given array is {1,2,3,4}
 *      Then one possible way is to choose the first 2 elements of the array {1,2} as the first subsequence, the next 2 elements {3,4} as the next subsequence
 *      So the answer is Yes.
 *
 * Consider another example:
 *      There are n = 4 numbers in the array.
 *      The length of each subsequence needs to be k = 3.
 *      The given array is {1,2,2,3}.
 *      Here there is no way to partition the array into subsequences such that all subsequences are of length 3 and each element in the array occurs
 *      in exactly one subsequence. Hence the answer is No.
 */
public class PartitioningArray {

    /**I suppose that's a fancy way of saying "make sure numbers.length is divisible by k and no element appears more than numbers.length/k times".*/
    public static boolean partitionArrayUnique(int[] nums, int k){
        if(nums.length % k != 0){ /**要是根本不能整除k的话, there is no way to partition the array into subsequences such that all subsequences
         are of length 3 and each element in the array occurs
         in exactly one subsequence. Hence the answer is No.*/
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
            if(map.get(num) > max){
                max = map.get(num);
            }
        }

        return max <= (nums.length / k); /**然后要是每个数字出现的次数都比 nums截成的段数多的话，证明它会出现多次*/
    }
}
