import java.util.HashMap;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<inorder.length; i++){
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length-1,inorder,0, inorder.length-1, map);
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, HashMap<Integer,Integer> map){
        if(inStart>inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = map.get(root.val);
        int leftcount = index - inStart;
        root.left = buildTree(preorder, preStart+1, preStart+leftcount, inorder, inStart, index-1,map);
        root.right = buildTree(preorder, /**preEnd-(inEnd-index)+1*/preStart+leftcount+1, preEnd, inorder, index+1, inEnd, map);
        return root;
    }
}
