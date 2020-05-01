import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Example:
 *
 * MyStack stack = new MyStack();
 *
 * stack.push(1);
 * stack.push(2);
 * stack.top();   // returns 2
 * stack.pop();   // returns 2
 * stack.empty(); // returns false
 */

public class ImplementStackUsingQueue {
    private Queue<Integer> q1;
    private int top;

    public static void main(String[] args) {
        ImplementStackUsingQueue s = new ImplementStackUsingQueue();
        s.push(1);
        s.push(2);
        System.out.println(s.top());
        System.out.println(s.pop());
        System.out.println(s.empty());
    }
    public ImplementStackUsingQueue(){
        this.q1 = new LinkedList<>();
    }

    public void push(int x){
        q1.offer(x);
        this.top = x;
    }

    public int top(){
        return top;
    }

    public int pop(){
        while(q1.size()>1){
            top = q1.poll();
            q1.offer(top);
        }
        return q1.poll();
    }

    public boolean empty(){
        return q1.isEmpty();
    }
}
