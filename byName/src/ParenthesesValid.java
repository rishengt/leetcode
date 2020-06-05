import java.util.HashMap;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class ParenthesesValid {
    public static void main(String[] args) {
        System.out.println(new ParenthesesValid().isValid("{[]}"));
        System.out.println(new ParenthesesValid().isValid("([)]"));
        System.out.println(new ParenthesesValid().isValid("()[]{}"));
    }
    public boolean isValid(String s){
        HashMap<Character, Character> map = new HashMap<>();
        map.put('}','{');
        map.put(')','(');
        map.put(']','[');
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i< s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                char c = stack.isEmpty()? '*':stack.peek();
                if(map.get(s.charAt(i)) == c) stack.pop();
                else return false;
            }else{
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}
