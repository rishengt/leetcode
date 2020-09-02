import java.util.TreeSet;

/**
 * Given an array of integers,
 * find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t
 * and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 */
public class ContainsDuplicateIII {
    public static void main(String[] args) {
        System.out.println(new ContainsDuplicateIII().containsNearbyAlmostDuplicate(new int[]{1,2,3,1},3,0));
        System.out.println(new ContainsDuplicateIII().containsNearbyAlmostDuplicate(new int[]{1,0,1,1},1,2));
        System.out.println(new ContainsDuplicateIII().containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9},2,3));
    }
    /**用O(n^2)的解法肯定超时了。。。。。。*/
    /**所以题目的hint给出的解法是O(n log k), 啥意思，仔细看题，其实题目给定了一个滑动窗口为k的范围，我们先确定一个j，再在排好序的k番位里找有没有合适的数**/
    /**根据小学数学公式可得
     * ｜nums[i]-nums[j]| <= t --> -t <= nums[i] - nums[j] <=t  ---->nums[j] - t <= nums[i] <= nums[j] + t
     * 当我们确定一个 j 的时候， 怎样才能 找到符合规矩的 i 呢
     * ceiling 是啥？ >=
     * floor 是舍？ <=
     * 那么 nums[i] 自然就得出结论了。 nums[i] = ceiling(nums[j]-t), nums[i] = floor(nums[j]+t)....
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for(int j = 0; j<nums.length; j++){
            Long l = (long)nums[j];
            Long floor = set.floor(l +t);
            Long ceiling = set.ceiling(l-t);
            if((floor!=null&& floor>=l-t )|| (ceiling!=null && ceiling <= l+t))
                return true;
            set.add(l);
            if(j>=k){
                set.remove((long)nums[j-k]);
            }
        }
        return false;
    }
}
