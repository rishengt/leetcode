/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedArraytoBinarySearchTree {
    public static void main(String[] args) {
        ConvertSortedArraytoBinarySearchTree cnm = new ConvertSortedArraytoBinarySearchTree();
        TreeNode root = cnm.convertSortedArrayToBST(new int[]{-10,-3,0,5,9});
        cnm.preorderTraverse(root);
    }
    public TreeNode convertSortedArrayToBST(int[] array){
        return helper(array, 0, array.length);
    }
    public TreeNode helper(int[] array, int start, int end){
        if(start == end){
            return null;
        }
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(array[mid]);
        root.left = helper(array, start, mid);
        root.right = helper(array, mid+1, end);
        return root;
    }

    public void inorderTraverse(TreeNode root){
        if(root == null) return;
        inorderTraverse(root.left);
        System.out.println(root.val);
        inorderTraverse(root.right);
    }

    public void preorderTraverse(TreeNode root){
        if(root == null) return;
        System.out.println(root.val);
        preorderTraverse(root.left);
        preorderTraverse(root.right);
    }
}
