/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Follow up:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is less than 6000.
 * -100 <= node.val <= 100
 */
public class PopulatingNextRightPointersinEachNodeII {
    public static void main(String[] args) {

    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    Node leftmost, head;

    public Node connect(Node root){
        if(root == null) return root;
        Node dummy = root;
        leftmost = new Node(0);
        head = leftmost;/** 这里他妈的精髓，此时他们是指向同一个对象的，下面head的next也会带动leftmost的next，精妙*/
        while(root!=null){
            if(root.left!=null){
                head.next = root.left;
                head = head.next;
            }
            if(root.right!=null){
                head.next = root.right;
                head = head.next;
            }
            root = root.next;
            if(root == null){
               head = leftmost;
               root = leftmost.next;
               leftmost.next = null;
            }
        }
        return dummy;
    }

}
