import com.sun.tools.corba.se.idl.toJavaPortable.Helper;

import java.util.*;

/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.
 *
 * A subtree must include all of its descendants.
 * 样例
 * Example 1:
 *
 * Input:
 * {10,5,15,1,8,#,7}
 * Output：
 * 3
 *
 * Explanation:
 *     10
 *     / \
 *    5  15
 *   / \   \
 *  1   8   7
 * The Largest BST Subtree in this case is :
 *    5
 *   / \
 *  1   8.
 * The return value is the subtree's size, which is 3.
 * Example 2:
 *
 * Input:
 * {1}
 * Output：
 * 1
 * 挑战
 * Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBSTSubtree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(5);
        root.right = new TreeNode(4);
        root.right.right = new TreeNode(3);
        root.right.right.left = new TreeNode(1);
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(1);
//        System.out.println(new LargestBSTSubtree().largestBSTSubtree(root));//2  why???
        System.out.println(new LargestBSTSubtree().largestBSTSubtreeII(root));//1
    }
    int max = 0;
    public int largestBSTSubtree(TreeNode root) {
        helper(root);
        return max;
    }

    private int[] helper(TreeNode root){
        int[] res = new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        if(root == null) return res;
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        if(left[0] == -1 || right[0] == -1 || root.val <= left[2] || root.val >= right[1]){
            res[0] = -1; res[1] = 0; res[2] = 0;
            return res;
        }
        res[0] = left[0] + 1 + right[0];
        max = Math.max(res[0], max);
        res[1] = Math.min(left[1], root.val);
        res[2] = Math.max(right[2], root.val);
        return res;
    }

    public int largestBSTSubtree(TreeNode root){
        if(root == null) return 0;
        int max = Integer.MIN_VALUE;
        List<TreeNode> list = new ArrayList<>();
        FindBST(root,list);
        Queue<int[]> queue = new PriorityQueue<>((a,b)->b[0]-a[0]);
        for(TreeNode node: list){
            queue.offer(getSizeAndMax(node,max));
        }
        return queue.poll()[0];
    }

    public int[] getSizeAndMax(TreeNode root, int max){
        int ans = 0;
        if(root == null) return new int[]{ans,0};
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while(!deque.isEmpty()){
            int size = deque.size();
            ans += size;
            for(int i = 0; i<size; i++){
                TreeNode temp = deque.poll();
                if(temp.val>max) max = temp.val;
                if(temp.left!=null)deque.offer(temp.left);
                if(temp.right!=null)deque.offer(temp.right);
            }
        }
        return new int[]{ans,max};
     }

    public void FindBST(TreeNode root, List<TreeNode> list){
        if(root == null) return;
        if(isBST(root)){
            list.add(root);
        }
        FindBST(root.left,list);
        FindBST(root.right,list);
    }

    public boolean isBST(TreeNode root){
        return isBST(root,null,null);
    }
    public boolean isBST(TreeNode root, TreeNode max, TreeNode min){
        if(root == null) return true;
        if( max!=null && root.val>=max.val) return false;
        if( min!=null && root.val<=min.val) return false;
        return isBST(root.left,root,min) && isBST(root.right,max,root) ;
    }
}
