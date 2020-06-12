/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FindFirstAndLastElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int[] ans = {-1,-1};
        if(nums.length == 0){
            return ans;
        }
        while(start <= end){
            int mid = (start + end ) / 2;
            if(target == nums[mid]){
                end = mid - 1;
            }
            else if(target < nums[mid]){
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if(start == nums.length || nums[start] != target){
            return ans;
        }else{
            ans[0] = start;
        }
        ans[0] = start;
        start = 0;
        end = nums.length -1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(target == nums[mid]) {
                start = mid + 1;
            }
            else if(target < nums[mid]){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        ans[1] = end;
        return ans;
    }

}
