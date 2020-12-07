/**
 * given a string, return the minimum move the make that string palindromic
 * requirement: 要用区间II的套路；
 */
public class MimumMoveToMakeStringPalindromic {
    public static void main(String[] args) {
        System.out.println(new MimumMoveToMakeStringPalindromic().minimumMove("aabbc"));
    }
    int minimumMove(String s ){
        /**区间II套路 ：
         * "无后效性"不成立，即相对于 index i， 不能设计出 dp[i] 单独依赖于 dp[j] (j<i), 但大区间的解可以由小区间的解得到
         *
         * 状态表达式：dp[i][j] - index i到j直接的最优解
         *           想方设法把dp[i][j]跟 dp[i'][j']联系起来；
         * 套路， 第一层遍历区间的长度
         *       第二层遍历区间的起始点
         *       最终结果 dp[1][N]
         */
        int n = s.length();
        int dp[][] = new int[n+1][n+1];
        s = "$" +s;
        for(int i = 0; i<=n; i++){
            /**单独截取 1 个字母的时候， 不用做动作，它本身就是palindrome*/
            dp[i][i] = 0;
        }
        for(int len = 2; len<=n; len++){
            /**思考一下起始点怎么找。。。。*/
            for(int j = 1; j+len-1<=n; j++){
                int end = j+len-1;
                if(s.charAt(j) != s.charAt(end)){
                    dp[j][end] = dp[j+1][end-1] +1;
                }else{
                    dp[j][end] = dp[j+1][end-1];
                }
            }
        }
        return dp[1][n];
    }
}
