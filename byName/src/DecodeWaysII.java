/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 *
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 *
 * Also, since the answer may be very large, you should return the output mod 10pow(9) + 7.
 *
 * Example 1:
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 * Note:
 * The length of the input string will fit in range [1, 105].
 * The input string will only contain the character '*' and digits '0' - '9'.
 */

public class DecodeWaysII {
    public static void main(String[] args) {
        System.out.println(new DecodeWaysII().ways("*10*1"));//99
        System.out.println(new DecodeWaysII().ways("*1")); //11
    }
    public int ways(String s){
        int mod = 1000000007; //Math.pow(a,b) return a double 菜🐔
        long[] dp = new long[s.length()+1];
        dp[0] = 1; /**只有一种方法decode空string*/
        dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0'? 0 : 1;/**花里胡哨*/
        for(int i = 1; i< s.length(); i++){
            if(s.charAt(i)=='*'){/**要是目前位置是'*'的话 */
                dp[i+1] = 9*dp[i]; /**1-9， 在i位上有9种可能，所以乘以9*/
                if(s.charAt(i-1) == '1' ){
                    //要打模，是题目要求，注意审题。。。。。。。
                    dp[i+1] = (dp[i+1] + 9*dp[i-1])%mod; /**要是前一位是1的话，可以组成11-19，还是九种可能，乘以9*/
                }else if(s.charAt(i-1) == '2'){
                    dp[i+1] = (dp[i+1] + 6*dp[i-1])%mod; /**要是前一位是2的话，可以组成21-26， 六种可能， 乘以6*/
                }else if(s.charAt(i-1) == '*'){
                    dp[i+1] = (dp[i+1] + 15*dp[i-1])%mod; /**要是前面是*的话，可以组成11-19+21-26， 15种可能， 乘以15*/
                }
            }else{/**目前位置是'*'以外的正常数*/
                dp[i+1] = s.charAt(i) == '0'? 0: dp[i];
                /**if(s.charAt(i-1) <= '2' && s.charAt(i) <='6') 只写这个判断条件的话 "*1" 就不行*/
                if(s.charAt(i-1) == '1'){
                    dp[i+1] = (dp[i+1] + dp[i-1])%mod;
                }else if(s.charAt(i-1) == '2' && s.charAt(i)<='6'){
                    dp[i+1] = (dp[i+1] + dp[i-1])%mod;
                }else if(s.charAt(i-1) == '*'){
                    dp[i+1] = (dp[i+1] + (s.charAt(i) <= '6'? 2: 1)*dp[i-1])%mod;
                }
            }
        }
        return (int)dp[s.length()];
    }
}
