/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left == null){
                root = root.right;
            }else{
                TreeNode temp = root.left;
                while(temp.right!=null){
                    temp = temp.right;
                }
                temp.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }
}
