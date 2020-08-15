/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Example 1:
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 *
 * Example 2:
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
import java.util.*;


/**
 *                  x                                                          x
 *              x                                       x
 *          x                       变换后                       x
 *      x                                                               x            然后你细品代码。。。
 *   x                                             x
 */
public class BSTRecover {
    TreeNode lastSeen = null;
    TreeNode first = null;
    TreeNode second = null;
    public void recoverTree(TreeNode root) {
        inorderTraverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public void inorderTraverse(TreeNode root){
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(lastSeen!= null && lastSeen.val>root.val){
                if(first== null)first = lastSeen;
                else{
                    second = root;
                }
            }
            lastSeen = root;
            root = root.right;
        }
    }
}
