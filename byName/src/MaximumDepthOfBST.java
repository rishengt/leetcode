import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its depth = 3.
 */
public class MaximumDepthOfBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new MaximumDepthOfBST().BFS(root));
    }
    public int BFS(TreeNode root){
        int ans = 0;
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            ans++;
            for(int i = 0; i< size; i++){
                TreeNode temp = queue.poll();
                if(temp.right!=null){
                    queue.offer(temp.right);
                }
                if(temp.left!=null){
                    queue.offer(temp.left);
                }
            }
        }
        return ans;
    }
}
