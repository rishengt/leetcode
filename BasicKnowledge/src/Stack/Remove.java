package Stack;

import java.util.Stack;

public class Remove {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        System.out.println(stack.push("a"));/**我艹，stack.push 是返回被push的东西的*/
        System.out.println(stack.push("b"));
        System.out.println(stack.push("c"));
//        System.out.println(stack.remove("a"));/**我艹，remove object是返回的boolean，还可以这样？？*/
        System.out.println(stack.remove(1));/**remove index 是返回被remove的东西，index由stack的最深处从零开始算起。。*/
//        System.out.println(stack.search("f"));/**search 返回你离栈顶的距离，由一算起，你要是栈顶就返回1，不存在就返回负一**/
//        while(!stack.isEmpty()) System.out.println(stack.pop());
    }
}
