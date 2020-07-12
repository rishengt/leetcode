/**
 * Given a binary string s (a string consisting only of '0' and '1's).
 *
 * Return the number of substrings with all characters 1's.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "0110111"
 * Output: 9
 * Explanation: There are 9 substring in total with only 1's characters.
 * "1" -> 5 times.
 * "11" -> 3 times.
 * "111" -> 1 time.
 * Example 2:
 *
 * Input: s = "101"
 * Output: 2
 * Explanation: Substring "1" is shown 2 times in s.
 * Example 3:
 *
 * Input: s = "111111"
 * Output: 21
 * Explanation: Each substring contains only 1's characters.
 * Example 4:
 *
 * Input: s = "000"
 * Output: 0
 *
 *
 * Constraints:
 *
 * s[i] == '0' or s[i] == '1'
 * 1 <= s.length <= 10^5
 */
public class NumberofSubstringsWithOnly1s {
    public static void main(String[] args) {
//        System.out.println(new NumberofSubstringsWithOnly1s().numSub("0110111"));
//        System.out.println(new NumberofSubstringsWithOnly1s().numSub("101"));
        System.out.println(new NumberofSubstringsWithOnly1s().numSub("111111"));
    }
    public int numSub(String s) {
        int ans = 0;
        int mod = 1000000007;
        if(s.length() == 0 || s == null) return ans;
        int temp = 0;
        for(int i = 0; i< s.length(); i++){
            if(s.charAt(i) == '0') temp = 0;
            else{
                temp++;
                ans += temp;
                ans %=mod;
            }
        }
        return ans;
    }
}
