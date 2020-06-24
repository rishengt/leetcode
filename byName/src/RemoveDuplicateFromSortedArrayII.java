/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 *
 * Given nums = [1,1,1,2,2,3],
 *
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 *
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 *
 * Given nums = [0,0,1,1,1,1,2,3,3],
 *
 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 *
 * It doesn't matter what values are set beyond the returned length.
 */
public class RemoveDuplicateFromSortedArrayII {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};
        int k = new RemoveDuplicateFromSortedArrayII().removeDuplicates(nums);
        for(int i = 0; i< k; i++){
            System.out.println(nums[i]);
        }
    }
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        int count = 1;
        int max = 2;
        for (; fast < nums.length; fast++) {
            //当前遍历的数字和慢指针末尾数字不同，就加到慢指针的末尾
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
                count = 1; //当前数字置为 1 个
                //和末尾数字相同，考虑当前数字的个数，小于 2 的话，就加到慢指针的末尾
            } else {
                if (count < max) {
                    slow++;
                    nums[slow] = nums[fast];
                    count++; //当前数字加 1
                }
            }
        }
        return slow + 1;
    }
}
