import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */

public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2};
        new NextPermutation().nextPermutation(nums);
        for(int i = 0; i<nums.length; i++){
            System.out.println(nums[i]);
        }
    }

    public void nextPermutation(int[] nums){
        int j = 0;
        int k = 0;
        for(int i = nums.length-1; i>0; i--){
            if(nums[i-1]<nums[i]){
                j = i-1;
                break;
            }
        }
        if(j == 0){
            reverse(nums,j,nums.length-1);
            return;
        }
        for(int i = j; i<nums.length-1;i++){
            if(nums[i+1]>nums[i]){
                k = i+1;
                break;
            }
        }
        swap(nums,j,k);
        reverse(nums,j+1, nums.length-1);
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums,int i, int j){
        while(i<j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }
}
