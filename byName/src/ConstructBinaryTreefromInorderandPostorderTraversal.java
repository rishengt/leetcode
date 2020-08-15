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
        System.out.println(new ConstructBinaryTreefromInorderandPostorderTraversal().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}));
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<inorder.length; i++){
            map.put(inorder[i],i);
        }
        return helper(inorder,0,inorder.length,postorder,0,postorder.length,map);
    }

    public TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer,Integer> map /**题目说了没有重复所以才能用hash*/){
        if(postStart == postEnd) return null;/**建树圣经第一条，当前后指针相撞的时候，返回null*/
        TreeNode root = new TreeNode(postorder[postEnd-1]);/**建树圣经第二条，确定root的值*/
        int inOrderIndex = map.get(root.val);
        int numOfLeftNodes = inOrderIndex - inStart;
    //    int numOfRightNodes = inorder.length-1-inOrderIndex;           /**你这里用减法去找边界的话很可能是负数，所有算法能用加法最好还是用加法*/
    //    root.left= helper(inorder,inStart,inOrderIndex,postorder,postStart,postEnd-numOfRightNodes,map);/**建树圣经第三条之一，指针包含左指针，不包含右指针，仔细确定好指针再递归*/
    //    root.right = helper(inorder,inOrderIndex+1,inEnd,postorder,postEnd-numOfRightNodes,postEnd-1,map);/**建树圣经第三条之二，排除掉已经搞过的root*/
        root.left= helper(inorder,inStart,inOrderIndex,postorder,postStart,postStart+numOfLeftNodes,map);/**建树圣经第三条之一，指针包含左指针，不包含右指针，仔细确定好指针再递归*/
        root.right = helper(inorder,inOrderIndex+1,inEnd,postorder,postStart+numOfLeftNodes,postEnd-1,map);/**建树圣经第三条之二，排除掉已经搞过的root*/
        return root;/**建树圣经第四条，记得返回root*/
    }
}
