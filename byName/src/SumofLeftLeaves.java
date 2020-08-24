/**
 * Find the sum of all left leaves in a given binary tree.
 *
 * Example:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumofLeftLeaves {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new SumofLeftLeaves().sumOfLeftLeaves(root));
    }


    /***************** 这种不用全局变量的题好好体会体会 **********************/
    public int sumOfLeftLeaves(TreeNode root) {
        return helper(root, false);
    }

    private int helper(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        if (root.left == null && root.right == null && isLeft) {
            return root.val;
        }
        return helper(root.left, true) + helper(root.right, false);
    }

    /****我自己写tree通常都偏爱用全局变量，功力还不到家。。。。。。。。。********/
    int ans = 0;
    public int sumOfLeftLeavesII(TreeNode root) {
        boolean isLeft = false;
        if(root == null) return ans;
        dfs(root,isLeft);
        return ans;
    }
    public void dfs(TreeNode root, boolean isLeft){
        if(root.right == null && root.left == null && isLeft){
            this.ans+=root.val;
            return;
        }
        if(root.left!=null){
            dfs(root.left,true);
        }
        if(root.right!=null){
            dfs(root.right, false);
        }
    }
}
