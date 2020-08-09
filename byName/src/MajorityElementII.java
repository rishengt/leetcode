import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElementII {
    /**作为一个配套问题我觉得这题最大的启发性意义就是让你想明白n/3 最多能找到两个合适的答案。。*/
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if(nums.length==0 || nums == null) return ans;
        int cnt1 = 0, cnt2 = 0, num1 = 0, num2 = 0;
        for(int num: nums){
            if(cnt1 == 0){
                num1 = num;
                cnt1++;
            }else if(cnt2 == 0){
                num2 = num;
                cnt2++;
            }else if(num == num1){
                cnt1++;
            }else if(num == num2){
                cnt2++;
            }else{
                cnt1--;
                cnt2--;
            }
        }
        cnt1=0;
        cnt2=0;
        for(int num: nums){
            if(num == num1) cnt1++;
            else if(num == num2) cnt2++;/**这里防止了重复出现的现象*/
        }
        if(cnt1>nums.length/3) ans.add(num1);
        if(cnt2>nums.length/3) ans.add(num2);
        return ans;
    }
}
