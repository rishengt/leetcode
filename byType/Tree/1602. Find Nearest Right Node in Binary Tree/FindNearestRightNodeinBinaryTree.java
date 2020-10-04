import java.util.*;
/**
 * Given the root of a binary tree and a node u in the tree, return the nearest node on the same level that is to the right of u, or return null if u is the rightmost node in its level.
 *
 *
 *
 * Example 1:
 *         1
 *        / \
 *       2   3
 *       \  / \
 *       4 5   6
 * Input: root = [1,2,3,null,4,5,6], u = 4
 * Output: 5
 * Explanation: The nearest node on the same level to the right of node 4 is node 5.
 * Example 2:
 *
 *
 *
 * Input: root = [3,null,4,2], u = 2
 * Output: null
 * Explanation: There are no nodes to the right of 2.
 * Example 3:
 *
 * Input: root = [1], u = 1
 * Output: null
 * Example 4:
 *
 * Input: root = [3,4,2,null,null,null,1], u = 4
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 105
 * All values in the tree are distinct.
 * u is a node in the binary tree rooted at root.
 */
public class FindNearestRightNodeinBinaryTree {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    public TreeNode findNeartestRightNode(TreeNode root, TreeNode u) {
        if (root == null) return null;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = deque.removeFirst();
                if (temp == u && i!=size-1) {
                    return deque.removeFirst();
                }
                if (temp.left != null) {
                    deque.addLast(temp.left);
                }
                if (temp.right != null) {
                    deque.addLast(temp.right);
                }
            }
        }
        return null;
    }
}
