/**
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 *
 *
 *
 * Example 1:
 * Input: "())"
 * Output: 1
 *
 * Example 2:
 * Input: "((("
 * Output: 3
 *
 * Example 3:
 * Input: "()"
 * Output: 0
 *
 * Example 4:
 * Input: "()))(("
 * Output: 4
 *
 *
 * Note:
 *
 * S.length <= 1000
 * S only consists of '(' and ')' characters.
 */
public class MinimumAddtoMakeParenthesesValid {
    public static void main(String[] args) {
        System.out.println(new MinimumAddtoMakeParenthesesValid().minAddToMakeValid("())"));
        System.out.println(new MinimumAddtoMakeParenthesesValid().minAddToMakeValid("((("));
        System.out.println(new MinimumAddtoMakeParenthesesValid().minAddToMakeValid("()"));
        System.out.println(new MinimumAddtoMakeParenthesesValid().minAddToMakeValid("()))(("));
    }
    public int minAddToMakeValid(String S) {
        if(S.length() == 0 || S == null) return 0;
        /**把参数理解为积分和必须的右括号可能会帮助你愚蠢的脑袋更好的理解这种题目，操你大爷！*/
        int cnt = 0, mandatoryLeft = 0;
        for(int i = 0; i< S.length(); i++){
            if(S.charAt(i) == '('){
                cnt +=1;
            }
            else if(S.charAt(i) == ')'){
                cnt -=1;
                if(cnt<0){
                    mandatoryLeft++;
                    cnt++;
                }
            }
        }
        return cnt + mandatoryLeft;
    }
}
