import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans,root,0);
        return ans;
    }
    public void dfs(List<List<Integer>> ans, TreeNode root, int level){
        if(root == null) return;
        //当前层数还没有元素，先 new 一个空的列表
        if(level >= ans.size()){
            ans.add(new ArrayList<>());//为什么不会多new 呢，因为你会返回上一层，而层数是从0开始算的，所以你在的层数肯定比你size少一位。
        }
        ans.get(level).add(root.val);
        dfs(ans,root.left,level+1);
        dfs(ans,root.right,level+1);
    }
}
