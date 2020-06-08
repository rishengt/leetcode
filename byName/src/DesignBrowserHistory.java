import java.util.ArrayDeque;

/**
 * Design Browser History
 * User Accepted:3648
 * User Tried:4128
 * Total Accepted:3698
 * Total Submissions:5608
 * Difficulty:Medium
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.
 *
 * Implement the BrowserHistory class:
 *
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * void visit(string url) visits url from the current page. It clears up all the forward history.
 * string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
 * string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 *
 *
 * Example:
 *
 * Input:
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 * Output:
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 *
 * Explanation:
 * BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
 * browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
 * browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
 * browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
 * browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
 * browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
 * browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
 * browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
 * browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
 * browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
 *
 *
 * Constraints:
 *
 * 1 <= homepage.length <= 20
 * 1 <= url.length <= 20
 * 1 <= steps <= 100
 * homepage and url consist of  '.' or lower case English letters.
 * At most 5000 calls will be made to visit, back, and forward.
 */

public class DesignBrowserHistory {
    private ArrayDeque<String> back = new ArrayDeque<>(), forward = new ArrayDeque<>();
    private String current;

    public DesignBrowserHistory(String homepage) {
        current = homepage;
    }

    public void visit(String url) {
        back.add(current);
        current = url;
        forward.clear();
    }

    public String back(int steps) {
        for (int i = 0; i < steps; i++) {
            if (back.isEmpty()) {
                break;
            }
            forward.addFirst(current);
            current = back.removeLast();
        }
        return current;
    }

    public String forward(int steps) {
        for (int i = 0; i < steps; i++) {
            if (forward.isEmpty()) {
                break;
            }
            back.add(current);
            current = forward.removeFirst();
        }
        return current;
    }


/************************************************doubly linked list 也是非常好的解决方案*******************************************************/
public class Node{
    String url;
    Node next, prev;
    public Node(String url) {
        this.url = url;
        next = null;
        prev = null;
    }
}

    Node head, curr;
//    public BrowserHistory(String homepage) {
//        head = new Node(homepage);
//        curr = head;
//    }

    public void visitII(String url) {
        Node node = new Node(url);
        curr.next = node;
        node.prev = curr;
        curr = node;
    }

    public String backII(int steps) {
        while (curr.prev != null && steps-- > 0) {
            curr = curr.prev;
        }
        return curr.url;
    }

    public String forwardII(int steps) {
        while (curr.next != null && steps-- > 0) {
            curr = curr.next;
        }
        return curr.url;
    }
}
