import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 *     1            <---
 *   /   \
 *  2     3         <---
 *   \
 *    5            <---
 *  output[1,3,5]
 */

public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        //root.right.right = new TreeNode(4);
        BinaryTreeRightSideView b = new BinaryTreeRightSideView();
        List<Integer> list = b.RightSideView(root);
        for(int i: list){
            System.out.println(i);
        }
    }
    public List<Integer> RightSideView(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode temp = null;//这一步是精华卧槽！
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i< size; i++){
                temp = queue.poll();
                if(temp.left!=null){
                    queue.offer(temp.left);
                }
                if(temp.right!=null) { /** because we put right in queue after left, so temp will always point to right in the end of the for loop*/
                    queue.offer(temp.right);
                }
            }
            list.add(temp.val);
        }
        return list;
    }

    /**这种写法第二个example pass不了
    public List<Integer> RightSideView(TreeNode root){
        if(root == null) return null;
        List<Integer> ans = new ArrayList<>();
        RightSideNodes(root,ans);
        return ans;
    }
    public void RightSideNodes(TreeNode root, List<Integer> list){
        if(root == null) return;
        list.add(root.val);
        RightSideNodes(root.right,list);
    }*/
}
