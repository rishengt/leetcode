import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBST {
    public static void main(String[] args) {
        ValidateBST bst = new ValidateBST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        System.out.println(bst.isBst(root));
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        System.out.println(bst.isBst(root2));
    }
    public boolean isBST(TreeNode root){
        return isBST(root, null,null);
    }
    public boolean isBST(TreeNode root, TreeNode min, TreeNode max){
        if(root == null) return true;
        if(min!=null && root.val <=min.val) return false;
        if(max!=null && root.val >=max.val) return false;
        return isBST(root.left,min,root)//because in BST if you enter the root's left child then the root becomes the child's max limit, nothing in
                                        //the left can surpass the root; so pass root in as the max limit;
                &&isBST(root.right,root,max);//because in BST if you enter the root's right then the root becomes the child's min limit, nothing in
                                        //the right tree will be smaller then the root; so pass root in as the min limit;
    }
/************************************************************************************************************************/
    public boolean isBst(TreeNode root){
        List<Integer> inorderlist = new ArrayList();
        inorder(root, inorderlist);
        for(int i = 0; i<inorderlist.size()-2;i++){
            if(inorderlist.get(i)>inorderlist.get(i+1))return false;
        }
        return true;
    }

    public void inorder(TreeNode root, List<Integer> inorderlist){
        if(root==null){
            return;
        }
        inorder(root.left, inorderlist);
        inorderlist.add(root.val);
        inorder(root.right, inorderlist);
    }
}

class TreeNode{
    int val;
    TreeNode right;
    TreeNode left;
    public TreeNode(int x){
        this.val = x;
        right = null;
        left = null;
    }
}
