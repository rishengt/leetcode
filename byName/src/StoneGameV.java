/**
 * There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 *
 * In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row),
 * then Bob calculates the value of each row which is the sum of the values of all the stones in this row.
 * Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row.
 * If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.
 *
 * The game ends when there is only one stone remaining. Alice's is initially zero.
 *
 * Return the maximum score that Alice can obtain.
 *
 *
 *
 * Example 1:
 * Input: stoneValue = [6,2,3,4,5,5]
 * Output: 18
 * Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14.
 * Bob throws away the right row and Alice's score is now 11.
 * In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
 * The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2).
 * The game ends because only one stone is remaining in the row.
 *
 * Example 2:
 * Input: stoneValue = [7,7,7,7,7,7,7]
 * Output: 28
 *
 * Example 3:
 * Input: stoneValue = [4]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= stoneValue.length <= 500
 * 1 <= stoneValue[i] <= 10^6
 */
public class StoneGameV {
    public static void main(String[] args) {
        System.out.println(new StoneGameV().stoneGameV(new int[]{6,2,3,4,5,5}));
        System.out.println(new StoneGameV().stoneGameV(new int[]{7,7,7,7,7,7,7}));
        System.out.println(new StoneGameV().stoneGameV(new int[]{4}));
    }
    public int stoneGameV(int[] stoneValue) {
        int[] prefixSum = new int[stoneValue.length+1];
        for(int i = 0; i<stoneValue.length; i++){
            prefixSum[i+1] = prefixSum[i]+stoneValue[i];
        }
        int[][] dp = new int[stoneValue.length][stoneValue.length];
        return dfsWithMemoization(stoneValue,0,stoneValue.length-1,dp,prefixSum);
    }

    public int dfsWithMemoization(int[] stoneValue, int left, int right, int[][] dp, int[] prefixSum){
        /**如果左右指针碰头代表数组剩下一个，我没得选，返回虚无，0*/
        if(left == right) return 0;
        /**若果剩下两个，返回较小的那个*/
        if(left == right -1) return Math.min(stoneValue[left],stoneValue[right]);
        /**memoization常规操作*/
        if(dp[left][right] != 0) return dp[left][right];
        /**工具数，dp待会儿指向它*/
        int res = 0;
        /**把数组所有的分割可能都遍历一遍*/
        /**left[left,i], right[i+1,right]*/
        for(int i = left; i<right; i++){
            /**这里可以细品一下，为什么右边界要加一而左不用*/
            int l = prefixSum[i+1] - prefixSum[left];
            int r = prefixSum[right+1] - prefixSum[i+1];
            if(l<r){/**如果l小，选l*/
                res = Math.max(res, l+dfsWithMemoization(stoneValue,left,i,dp,prefixSum));
            }else if(l>r){
                res = Math.max(res,r+dfsWithMemoization(stoneValue,i+1,right,dp,prefixSum));
            }else{/**两边一样，选下一次赚得多的一边。。。妙啊！！！！*/
                res = Math.max(res, Math.max(l+dfsWithMemoization(stoneValue,left,i,dp,prefixSum), r+dfsWithMemoization(stoneValue,i+1,right,dp,prefixSum)));
            }
        }
        dp[left][right] = res;
        return res;
    }
}
