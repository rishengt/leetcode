import java.util.*;
/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(new BasicCalculator().calculate("3/2"));
        System.out.println(new BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"));
    }
    public int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i)!=' '){
                queue.offer(s.charAt(i));
            }
        }
        queue.offer('+');
        return cal(queue);
    }
    public int cal(Queue<Character> queue){
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        int ans = 0;
        while(!queue.isEmpty()){
            char c = queue.poll();
            if(Character.isDigit(c)){
                num = num*10+(c-'0');
            }else if(c == '('){
                num = cal(queue);
            }else{
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = c;
                if (c == ')') {
                    break;
                }
            }
        }
        while(!stack.isEmpty()){
            ans += stack.pop();
        }
        return ans;
    }
}
