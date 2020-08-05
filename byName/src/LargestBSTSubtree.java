import com.sun.tools.corba.se.idl.toJavaPortable.Helper;

/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 *
 * A subtree must include all of its descendants.
 * 样例
 * Example 1:
 *
 * Input:
 * {10,5,15,1,8,#,7}
 * Output：
 * 3
 *
 * Explanation:
 *     10
 *     / \
 *    5  15
 *   / \   \
 *  1   8   7
 * The Largest BST Subtree in this case is :
 *    5
 *   / \
 *  1   8.
 * The return value is the subtree's size, which is 3.
 * Example 2:
 *
 * Input:
 * {1}
 * Output：
 * 1
 * 挑战
 * Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBSTSubtree {
    int max = 0;
    public int largestBSTSubtree(TreeNode root) {
        helper(root);
        return max;
    }

    private int[] helper(TreeNode root){
        int[] res = new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        if(root == null) return res;
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        if(left[0] == -1 || right[0] == -1 || root.val <= left[2] || root.val >= right[1]){
            res[0] = -1; res[1] = 0; res[2] = 0;
            return res;
        }
        res[0] = left[0] + 1 + right[0];
        max = Math.max(res[0], max);
        res[1] = Math.min(left[1], root.val);
        res[2] = Math.max(right[2], root.val);
        return res;
    }

    public boolean isBST(TreeNode root){

    }
    public boolean helper(TreeNode root, int max, int min){
        if(root == null) return true;
        if(root.val>max || root.val<min) return false;
        return helper(root.left,root.val,Integer.MIN_VALUE) && helper(root.right,Integer.MAX_VALUE,root.val) ;
    }
}
