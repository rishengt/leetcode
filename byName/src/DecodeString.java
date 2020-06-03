import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {

    public static void main(String[] args) {
        System.out.println(new DecodeString().decodeString("3[a]2[bc]"));
        System.out.println(new DecodeString().decodeString("3[a2[c]]"));
        System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));
    }


/********************************************什么鸡巴天才解法，我吐了。。。****************************************************/
    public String decodeString(String s) {
        Deque<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) queue.offer(c);
        return helper(queue);
    }

    public String helper(Deque<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            char c= queue.poll();
            if (Character.isDigit(c)) {
                num = c-'0';
            } else if (c == '[') {
                String sub = helper(queue);
                for (int i = 0; i < num; i++) sb.append(sub);
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }



/**********************************************用stack的解法，思想差不多的****************************************************/
public String decodeStringII(String s) {

    Stack<Character> stack = new Stack<>();

    for(char c : s.toCharArray())
    {
        if(c != ']')
            stack.push(c); //push everything but ]

        else
        {
            //step 1:
            //if you find a closing ] then
            //retrieve the string it encapsulates

            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty() && Character.isLetter(stack.peek()))
                sb.insert(0, stack.pop());

            String sub = sb.toString(); //this is the string contained in [ ]
            stack.pop(); //Discard the '[';


            //step 2:
            //after that get the number of
            // times it should repeat from stack

            sb = new StringBuilder();
            while(!stack.isEmpty() && Character.isDigit(stack.peek()))
                sb.insert(0, stack.pop());

            int count = Integer.valueOf(sb.toString()); //this is the number


            //step 3:
            //repeat the string within the [ ] count
            //number of times and push it back into stack

            while(count > 0)
            {
                for(char ch : sub.toCharArray())
                    stack.push(ch);
                count--;
            }
        }
    }

    //final fetching and returning the value in stack
    StringBuilder retv = new StringBuilder();
    while(!stack.isEmpty())
        retv.insert(0, stack.pop());

    return retv.toString();
}
}
