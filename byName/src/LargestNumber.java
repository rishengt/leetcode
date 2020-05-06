import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 *
 * Example 1:
 *
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {
    public static void main(String[] args) {
        System.out.println(new LargestNumber().largestNumber(new int[]{3,30,34,5,9}));
        System.out.println(new LargestNumber().largestNumber(new int[]{0,00,0,0,0}));
    }
    public String largestNumber(int[] nums){
        String[] ans = new String[nums.length];
        for(int i = 0; i<nums.length; i++){
            ans[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(ans, (String a, String b) -> (b+a).compareTo(a+b));/**妈的这么天才的sort法谁想出来的。。。。奇技淫巧啊！！*/
        if(ans[0].equals("0")) return "0"; /**你要在String.equals栽多少跟斗才能记住见到String就用equels？？？*/
        String largestNum = "";
        for(int i = 0; i<ans.length; i++){
            largestNum += ans[i];
        }
        return largestNum;
    }
}
