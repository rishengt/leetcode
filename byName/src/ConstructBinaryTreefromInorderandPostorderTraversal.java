import java.util.HashMap;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreefromInorderandPostorderTraversal {
    public static void main(String[] args) {
        System.out.println(new ConstructBinaryTreefromInorderandPostorderTraversal().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,10,3}).val);
    }
    int[] inorder;
    int[] postorder;
    HashMap<Integer,Integer> idx_map = new HashMap<Integer,Integer>();
    int post_idx;


    public TreeNode helper(int left, int right){

        if(left==right) return null;

        int value = postorder[post_idx];
        int idx = this.idx_map.get(value);
        post_idx--;

        TreeNode root = new TreeNode(value);

        root.right = helper(idx+1,right);
        root.left = helper(left,idx);
        return root;


    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int index = 0;
        this.inorder = inorder;
        this.postorder = postorder;
        this.post_idx = this.postorder.length-1;
        for(int i : this.inorder){
            this.idx_map.put(i,index++);
        }
        return helper(0,this.inorder.length);


    }
}
