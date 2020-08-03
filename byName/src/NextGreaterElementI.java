import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 *
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 *     For number 1 in the first array, the next greater number for it in the second array is 3.
 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * Example 2:
 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * Output: [3,-1]
 * Explanation:
 *     For number 2 in the first array, the next greater number for it in the second array is 3.
 *     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * Note:
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 */
public class NextGreaterElementI {
    public static void main(String[] args) {
        NextGreaterElementI n = new NextGreaterElementI();
        int[] k = n.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
        for (int j: k){
            System.out.println(j);
        }
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2){
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i<nums2.length; i++){
            while(!stack.isEmpty() && nums2[i]>stack.peek()){
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        for(int i = 0; i < nums1.length; i++){
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }

    /******************************* 甚至觉得有点弱智，我这个写法***********************************************************/
    public int[] nextGreaterElementII(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Arrays.fill(ans,-1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<nums2.length; i++){
            map.put(nums2[i],i);
        }
        for(int i = 0; i< nums1.length; i++){
            int j = map.get(nums1[i]);
            for(int k = j+1; k<nums2.length; k++){
                if(nums2[k]>nums1[i]) {
                    ans[i] = nums2[k];
                    break;
                }
            }
        }
        return ans;
    }
}
