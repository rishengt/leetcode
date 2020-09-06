/**
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Return the length of the shortest subarray to remove.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,10,4,2,3,5]
 * Output: 3
 * Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
 * Another correct solution is to remove the subarray [3,10,4].
 * Example 2:
 *
 * Input: arr = [5,4,3,2,1]
 * Output: 4
 * Explanation: Since the array is strictly decreasing, we can only keep a single element.
 * Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
 * Example 3:
 *
 * Input: arr = [1,2,3]
 * Output: 0
 * Explanation: The array is already non-decreasing. We do not need to remove any elements.
 * Example 4:
 *
 * Input: arr = [1]
 * Output: 0
 */
public class ShortestSubarraytobeRemovedtoMakeArraySorted {
    public int findLengthOfShortestSubarray(int[] arr) {
        if(arr.length<=1) return 0;
        int p = 0;
        while(p<arr.length-1 && arr[p]<=arr[p+1]){
            p++;
        }
        if(p == arr.length-1) return 0;
        int ans = 0;
        int end = arr.length-1;
        while(end>p && arr[end]>= arr[end-1]){
            end--;
        }
        /**这里的意义是要么把后面删了，要么把前面删了**/
        ans = Math.min(arr.length-p-1, end);
        int i = 0, j= end;
        while(i<=p && j<arr.length){
            /**这里就是拼接了，但是吧i从新设置成0非常的精妙，菜🐔还是差远了。哎**/
            if(arr[j] >= arr[i]){
                ans = Math.min(ans, j-i+1);
                i++;
            }else{
                j++;
            }
        }
        return ans;
    }
}
