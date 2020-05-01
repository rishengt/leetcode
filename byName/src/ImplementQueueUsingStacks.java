import java.util.Stack;

/**
 * Implement the following operations of a queue using stacks.
 *
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Example:
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // returns 1
 * queue.pop();   // returns 1
 * queue.empty(); // returns false
 */

public class ImplementQueueUsingStacks {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public static void main(String[] args) {
        ImplementQueueUsingStacks queue = new ImplementQueueUsingStacks();
         queue.push(1);
         queue.push(2);
        System.out.println(queue.peek());;  // returns 1
        System.out.println(queue.pop());;   // returns 1
        System.out.println(queue.empty());; // returns false
    }

    /** Initialize your data structure here. */
    public ImplementQueueUsingStacks() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if(s1.empty()){
            s1.push(x);
        }else{
            while(!s1.isEmpty()){
                int temp = s1.pop();
                s2.push(temp);
            }
            if(s1.isEmpty()){
                s1.push(x);
                while(!s2.isEmpty()){
                    int temp = s2.pop();
                    s1.push(temp);
                }
            }
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s1.pop();
    }

    /** Get the front element. */
    public int peek() {
        return s1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }
}
