/**
 * Given a string num representing the digits of a very large integer and an integer k.
 *
 * You are allowed to swap any two adjacent digits of the integer at most k times.
 *
 * Return the minimum integer you can obtain also as a string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: num = "4321", k = 4
 * Output: "1342"
 * Explanation: The steps to obtain the minimum integer from 4321 with 4 adjacent swaps are shown.
 * Example 2:
 *
 * Input: num = "100", k = 1
 * Output: "010"
 * Explanation: It's ok for the output to have leading zeros, but the input is guaranteed not to have any leading zeros.
 * Example 3:
 *
 * Input: num = "36789", k = 1000
 * Output: "36789"
 * Explanation: We can keep the number without any swaps.
 * Example 4:
 *
 * Input: num = "22", k = 22
 * Output: "22"
 * Example 5:
 *
 * Input: num = "9438957234785635408", k = 23
 * Output: "0345989723478563548"
 */
public class MinimumPossibleIntegerAfteratMostKAdjacentSwapsOnDigits {
    public static void main(String[] args) {
        System.out.println(new MinimumPossibleIntegerAfteratMostKAdjacentSwapsOnDigits().minInteger("4321",4));
    }
    public String minInteger(String num, int k) {
        char[] ca = num.toCharArray();
        helper(ca, 0, k);
        return new String(ca);
    }
    public void helper(char[] ca, int I, int k){
        if (k==0 || I==ca.length) return;
        int min = ca[I], minIdx = I;
        for (int i = I+1; i<Math.min(I+k+1, ca.length); i++)/**向身后找，要是找到比较小的，就交换*/
            if (ca[i]<min){
                min=ca[i];/**保留下小的方便比较*/
                minIdx=i;
            }
        char temp = ca[minIdx];/**经过上一个forloop这个就是最小的，记下来然后换位*/
        for (int i = minIdx; i>I; i--) ca[i]=ca[i-1];/**因为小的那一位被抽走了所以每个都缩一位*/
        ca[I] = temp;/**换位*/
        /**重复该步骤知道k用完*/
        helper(ca, I+1, k-(minIdx-I));/**这个算剩下的k妈的精髓，要细品*/
    }
}
