package Stack;

import java.util.Stack;

public class CanPeekPeek {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());/**只能peek一下。。。你个傻叼*/
    }
}
