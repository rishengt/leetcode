import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements that appear twice in this array.
 *
 * Could you do it without extra space and in O(n) runtime?
 *
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [2,3]
 */
public class FindAllDuplicatesinanArray {
    /** 这题用hashmap 当然也能解决，但是显得稍微有点捞 */
    /** 所以，注意审题， 题目有个条件，1 ≤ a[i] ≤ n (n = size of array)， 只要有这个条件，下面这种骚写法就可以使用*/
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<nums.length; i++){
            int index = Math.abs(nums[i]) - 1; /** 精髓来了，因为上述条件，我们永远可以在array里找到 array【i】代表的index，众所周知，index是要减一的*/
            if(nums[index] <0) list.add(index+1); /** 刚刚减一现在要加回来，不然答案数列永远少一位*/
            nums[index] *= -1; /**我们把狗日的变成负数，通过上面的if 判断要是有过负数那么代表这个数已经出现过一次了，要加入 list， 秒啊 */
        }
        return list;
    }
}
