import java.util.*;
/**
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 * input: [1,-2,-3,1,3,-2,null,-1]
 *        -1
 * output: 4
 *
 */
public class PathSumIII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(-2);
//        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-1);
//        root.left.right.right = new TreeNode(1);
        System.out.println(new PathSumIII().pathSumII(root,-1));
    }
    /**************************** 最优解！！！！！！还要多说什么吗？？？？ ****************************************************/
    /**
     So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum,value : how many ways get to this prefix sum) ,
     and whenever reach a node, we check if prefix sum - target exists in hashmap or not,
     if it does, we added up the ways of prefix sum - target into res.
     For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, let's say we want to find target sum is 2,
     then we will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways.
     */
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }

    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

    /**精简而优雅的写法，思路跟我本质上一样但代码太好看的了***/
    public int pathSumII(TreeNode root, int sum){
        if(root == null) return 0;
        return dfsII(root,sum) + pathSumII(root.left,sum) + pathSumII(root.right, sum);
    }
    public int dfsII(TreeNode root,int sum){
        if(root == null) return 0;
        return(root.val == sum ? 1 : 0 ) + dfsII(root.left,sum-root.val) + dfsII(root.right, sum-root.val);
    }


    /***************************** 我自己写的捞逼答案，但还是对🌲的感悟有了一点点的进步了，哎，菜🐔*******************************/
    int ans = 0;/**这个局外的声明也很关键，感觉要是你在dfs里面声明了一旦返回上一层你保存的结果就没了*/
    public int pathSumIII(TreeNode root, int sum) {
        if(root == null) return 0;
        int k = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i< size; i++){
                TreeNode temp = queue.poll();
                dfs(temp,sum);
                if(temp.left!=null)queue.offer(temp.left);
                if(temp.right!=null)queue.offer(temp.right);
            }
        }
        return ans;
    }

    public void dfs(TreeNode root, int sum){
        if(sum == root.val){/**这里也是挺值得一品的。。。*/
            ans++;
            /**return; 这里太他妈关键了，你要是return了，去不到最底下那层，会算少一些路径*/
        }
        if(root.left!=null){
            dfs(root.left,sum-root.val);
        }
        if(root.right!=null){
            dfs(root.right,sum-root.val);
        }
        if(root.right == null && root.left == null) return;/**这里确保你去到最低层才返回**/
    }
}
