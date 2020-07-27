import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new BinaryTreeLevelOrderTraversalII().levelOrderBottom(root));
    }

    /***********************************是时候领悟一下DFS了，草！**********************************************************/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if(root == null) return;
        if(level >= list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        list.get(list.size()-level-1).add(root.val);
        levelMaker(list, root.left, level+1);
        levelMaker(list, root.right, level+1);
    }


    /**************************************这题 easy 自己的做法比较僵硬，做🌲的目的是为了学DFS， 不太行********************/
    public List<List<Integer>> levelOrderBottomII(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        Deque<TreeNode> queue2 = new LinkedList<>();
//        Deque<TreeNode> queue3 = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            list.add(size);
            for(int i = 0; i< size; i++){
                TreeNode temp = queue.poll();
                queue2.offer(temp);
                if(temp.right !=null) {
                    queue.offer(temp.right);
                }
                if(temp.left !=null) {
                    queue.offer(temp.left);
                }
            }
        }
        for(int i=list.size()-1; i>=0; i--){
            List<Integer> tmlist = new ArrayList<>();
            int temp = list.get(i);
            while(temp>0){
                tmlist.add(queue2.removeLast().val);
                temp--;
            }
            ans.add(tmlist);
        }
        return ans;
    }
}
