/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */

public class RegularExpressionMatching {
    public static void main(String[] args) {
//        System.out.println(new RegularExpressionMatching().isMatch("mississippi","mis*is*p*."));
//        System.out.println(new RegularExpressionMatching().isMatchII("mississippi","mis*is*p*."));
//        System.out.println(new RegularExpressionMatching().isMatchII("aab","c*a*b"));
        System.out.println(new RegularExpressionMatching().isMatchII("aa","a"));
    }

    public boolean isMatchII(String s, String pattern){/**由下至上的递归*/
        boolean[][] dp = new boolean[s.length()+1][pattern.length()+1];
        dp[s.length()][pattern.length()] = true;
        for(int i = s.length(); i>=0; i--){
            for(int j = pattern.length();j>=0; j--){
                /**因为上面已经把这种情况初始化了， 直接continue*/
                if(i == s.length() && j == pattern.length()) continue;
                /**记住一定要给i和j一个index判断不然会index out of bound*/
                boolean first_match = i<s.length() && j< pattern.length() && (s.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.');
                if(j+1< pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = first_match && dp[i+1][j] || dp[i][j+2];/**忘了first_match就错了*/
                }else{
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

/**************************************************递归写法好理解一点******************************************************************************/

    public boolean isMatch(String s, String pattern){
        if(pattern.length() == 0) return s.length() == 0;
        boolean first_match = s.length()!=0/**这个判断条件细啊， 防止index out of bound*/ && (s.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');
        if(pattern.length()>=2 && pattern.charAt(1) == '*'){
            return (first_match && isMatch(s.substring(1)/**两种情况，1。第一位能匹对上且后面一位能跟'*'配对上，所以s往后移一位， pattern停在原地'*'位*/, pattern))
                    || isMatch(s,pattern.substring(2)/**2。配对不上，pattern跳过*，所以往后移两位，s停在原地*/);
        } else{
            return first_match && isMatch(s.substring(1), pattern.substring(1));
        }
    }
}
