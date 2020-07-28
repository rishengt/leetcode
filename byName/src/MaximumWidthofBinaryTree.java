import java.util.*;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of 32-bit signed integer.
 *
 * Example 1:
 *
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 *
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 *
 *
 * Constraints:
 *
 * The given binary tree will have between 1 and 3000 nodes.
 */


/***   当你level order去遍历一棵树然后把它转化成array的时候，parent跟child在array里面对应的index就是n, 2n, 2n+1.这跟以前的preorder，inorder之类的不一样，两种代表方法*/
public class MaximumWidthofBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left=new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
//        root.left.left.left = new TreeNode(6);
//        root.right.right.right = new TreeNode(7);
        System.out.println(new MaximumWidthofBinaryTree().widthOfBinaryTree(root));
    }

    /**
     * The idea is to traverse all the node in the tree in level order(Here I use one Queue to store each level's nodes.
     * The time I traverse each level is the queue's size(the number of nodes from upper level)).
     * Each time a node is traversed, the position of the node(as it is in a full binary tree) is stored in the HashMap.
     * If the position of the parent node is 'n', then the left child is '2 * n' and the right child is '2 * n + 1'.
     * The width of each level is the last node's position in this level subtracts the first node's position in this level plus 1.
     */

    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> qIndex = new LinkedList<>();
        q.add(root);
        qIndex.add(1); //store index, assuming root's index is 1
        int max = 0;
        while(!q.isEmpty())
        {
            int size = q.size();
            int start = 0, end = 0;
            for(int i=0; i<size; i++)
            {
                TreeNode node = q.remove();
                int index = qIndex.remove();
                if(i==0) start = index; //start and end index for each level
                if(i==size-1) end = index;
                if(node.left!=null)
                {
                    q.add(node.left);
                    qIndex.add(2*index);
                }

                if(node.right!=null)
                {
                    q.add(node.right);
                    qIndex.add(2*index+1);
                }
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }




    /*****************************************我真的好想掌握dfs啊！！！！！！！！！！！！！*************************************/

    /**
     * We know that a binary tree can be represented by an array (assume the root begins from the position with index 1 in the array).
     * If the index of a node is i, the indices of its two children are 2*i and 2*i + 1.
     * The idea is to use two arrays (start[] and end[]) to record the the indices of the leftmost node and rightmost node in each level, respectively.
     * For each level of the tree, the width is end[level] - start[level] + 1. Then, we just need to find the maximum width.
     */
    public int widthOfBinaryTreeII(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
    }

    public int dfs(TreeNode root, int level, int order, List<Integer> start, List<Integer> end){
        if(root == null)return 0;
        if(start.size() == level){
            start.add(order); end.add(order);
        }
        else end.set(level, order);
        int cur = end.get(level) - start.get(level) + 1;
        int left = dfs(root.left, level + 1, 2*order, start, end);
        int right = dfs(root.right, level + 1, 2*order + 1, start, end);
        return Math.max(cur, Math.max(left, right));
    }
}
