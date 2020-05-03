import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * Example:
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 */
public class ArrayFindAllNumbersDisappearedin {

    public static void main(String[] args) {
        System.out.println(new ArrayFindAllNumbersDisappearedin().findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i = 0; i<nums.length; i++){
            int moveto = Math.abs(nums[i]);
            if(nums[moveto-1]<0) continue;
            nums[moveto-1] *= -1;/***把数组的数跟index联系起来然后变负， 细品，下次估计还是做不出来。。。。。*/
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 1; i <=nums.length; i++){
            if(nums[i-1]>0) ans.add(i);
        }
        return ans;
    }
}
