import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree. Return the maximum average value.(变种，return的root of the maximum average subtree)
 *
 *      1
 *    /   \
 *  -1    11
 *  / \   / \
 * 2   5 -2  4
 */
public class MaximumAverageSubtree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-1);
        root.left.right = new TreeNode(-5);
        root.left.left = new TreeNode(-2);
        root.right = new TreeNode(11);
        root.right.left = new TreeNode(-2);
        root.right.right = new TreeNode(4);
        System.out.println(new MaximumAverageSubtree().maximumAverage(root));
        System.out.println(new MaximumAverageSubtree().maximumAverageRoot(root).val);
    }

    double max = Double.MIN_VALUE;
    double compare = Double.MIN_VALUE;
    TreeNode subRoot = null;
    public TreeNode maximumAverageRoot(TreeNode root){
        TreeTraverse(root);
        return subRoot;
    }
    public double maximumAverage(TreeNode root){
        TreeTraverse(root);
        return max;
    }
    public int[] TreeTraverse(TreeNode root){
        int[] num = new int[2];
        num[0] = 1;
        num[1] = root.val;
        if(root.right!=null){
            int[] temp = TreeTraverse(root.right);
            num[0] += temp[0];
            num[1] += temp[1];
        }
        if(root.left!=null){
            int[] temp = TreeTraverse(root.left);
            num[0] += temp[0];
            num[1] += temp[1];
        }
        max = Math.max(max, (double)num[1]/num[0]);
        if(max>compare){
            subRoot = root;
            compare = max;
        }
        return num;
    }
}
