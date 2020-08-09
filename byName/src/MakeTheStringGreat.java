import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string s of lower and upper case English letters.
 *
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 *
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 * To make the string good, you can choose two adjacent characters that make the string bad and remove them.
 * You can keep doing this until the string becomes good.
 *
 * Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
 *
 * Notice that an empty string is also good.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leEeetcode"
 * Output: "leetcode"
 * Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
 * Example 2:
 *
 * Input: s = "abBAcC"
 * Output: ""
 * Explanation: We have many possible scenarios, and all lead to the same answer. For example:
 * "abBAcC" --> "aAcC" --> "cC" --> ""
 * "abBAcC" --> "abBA" --> "aA" --> ""
 * Example 3:
 *
 * Input: s = "s"
 * Output: "s"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only lower and upper case English letters.
 */
public class MakeTheStringGreat {
    public static void main(String[] args) {
        System.out.println(new MakeTheStringGreat().makeGood("leEeetcode"));
        System.out.println(new MakeTheStringGreat().makeGood("abBAcC"));
        System.out.println(new MakeTheStringGreat().makeGood("s"));
    }
    public String makeGood(String s){
        if(s.length()<=1) return s;
        Deque<Character> deque = new LinkedList<>();
        for(int i = 0; i< s.length(); i++){
            char b = s.charAt(i);
            if(!deque.isEmpty()){
                char a = deque.peekLast();
                if((b == Character.toUpperCase(a)&& a == Character.toLowerCase(b)) || (b == Character.toLowerCase(a) && a == Character.toUpperCase(b)) ){
                    deque.removeLast();
                }else{
                    deque.addLast(b);
                }
            }else{
                deque.addLast(b);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            sb.append(deque.removeFirst());
        }
        return sb.toString();
    }
}
