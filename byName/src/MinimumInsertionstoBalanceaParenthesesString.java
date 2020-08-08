/**
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:
 *
 * Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
 * Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
 * For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
 *
 * You can insert the characters '(' and ')' at any position of the string to balance it if needed.
 *
 * Return the minimum number of insertions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to to add one more ')'
 * at the end of the string to be "(())))" which is balanced.
 * Example 2:
 *
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 * Example 3:
 *
 * Input: s = "))())("
 * Output: 3
 * Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 * Example 4:
 *
 * Input: s = "(((((("
 * Output: 12
 * Explanation: Add 12 ')' to balance the string.
 * Example 5:
 *
 * Input: s = ")))))))"
 * Output: 5
 * Explanation: Add 4 '(' at the beginning of the string and one ')' at the end. The string becomes "(((())))))))".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of '(' and ')' only.
 */
public class MinimumInsertionstoBalanceaParenthesesString {
    public static void main(String[] args) {
        System.out.println(new MinimumInsertionstoBalanceaParenthesesString().minInsertions("()("));
    }
    public int minInsertions(String s) {
        int ans=0;/**这个将要添加的扩号（包括左括号和右括号）*/
        int cnt=0;/**这个是需要的右括号*/
        for(char ch:s.toCharArray()){
            if(ch=='(') {
                cnt+=2; /**如果是（，那么它需要两个）来抵消*/
                if(cnt%2!=0){/**如果我们积累了odd的 ），我们要把它变成双数*/
                    ans++;/**所以我们又添加了一个 ）*/
                    cnt--;/**既然我们添加了一个 ）了，那么需要的 ）自然就减少一个啦*/
                }
            }
            else{
                cnt-=1;/**既然自带 ），那么需要 ）自然就要减少一个咯*/
                if(cnt<0){/**妈的如果一开头就是 ）；那么我们需要insert一个（ 去补救*/
                    ans++;/**手动添加（，补救*/
                    cnt+=2;/**补救完了要两个 ） 来填*/
                }
            }
        }
        return ans+cnt;/**手动添加的加上需要的 ），自然就是总数了，妙啊操！！！！*/
    }
}
