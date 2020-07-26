import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree and an integer distance.
 * A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.
 *
 * Return the number of good leaf node pairs in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,4], distance = 3
 * Output: 1
 * Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,5,6,7], distance = 3
 * Output: 2
 * Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2.
 * The pair [4,6] is not good because the length of ther shortest path between them is 4.
 * Example 3:
 *
 * Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
 * Output: 1
 * Explanation: The only good pair is [2,5].
 * Example 4:
 *
 * Input: root = [100], distance = 1
 * Output: 0
 * Example 5:
 *
 * Input: root = [1,1,1], distance = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 2^10].
 * Each node's value is between [1, 100].
 * 1 <= distance <= 10
 */
public class NumberofGoodLeafNodesPairs {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        System.out.println(new NumberofGoodLeafNodesPairs().countPairs(root,2));
    }
    int ans = 0;
    public int countPairs(TreeNode root, int distance) {
        ans = 0;
        dfs(root, distance);
        return ans;
    }

    List<Integer> dfs(TreeNode root, int distance)
    {
        if(root == null)return new ArrayList<Integer>();
        if(root.left == null && root.right == null){
            List<Integer> ret = new ArrayList<>();/**这条list到底代表了什么？ 它代表了你所有儿孙们（leaf nodes）到你这里的距离*/
            /**如果你是最下层的子节点，你离你上一层的parent距离为一，所以加一，记录下来*/
            ret.add(1);
//            ret.add(0); 加0⃣️也行，下面得加2⃣️补回来
            return ret;
        }

        List<Integer> L = dfs(root.left, distance);
        List<Integer> R = dfs(root.right, distance);
        for(int x : L){
            for(int y : R){
                if(x+y <= distance){
                    ans++;
                }
//                if(x + y + 2 <= distance){/**你把上面那个ret.add(0)改成ret.add(1),就可以不用+2。这其实是两种思维，add（1）代表你去比parent是距离一，add（0）代表你去你当前的点
//                 至于加二的话就是你想象两个点，中间隔了一个点，这两个点之间的距离要加二因为他们本身写作0了，你不加二就把他们距离给吞了。个人推荐第一种写法，直接记录由点去parent的距离*/
//                    ans++;
//                }
            }
        }
        List<Integer> ret = new ArrayList<>();
        for(int x : L)ret.add(x+1);/**层层往上推，你知道了你离你parent的距离是一，那么你离你parent的parent的距离就是1加上你离你parent的距离*/
        for(int x : R)ret.add(x+1);
        return ret;
    }


    /************************ 比起上面花里胡哨的dfs， 这是更接近本源的对问题的思考以及解法 *****************************************/
    public int countPairsII(TreeNode root, int distance) {
        // leaf node -> trail from root to leaf
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        List<TreeNode> leaves = new ArrayList<>();
        findLeaves(root, new ArrayList<>(), leaves, map);
        int res = 0;
        // count the distance of each leaf node pairs
        for(int i = 0; i < leaves.size(); i++) {/**这里为什么以leaves的size为遍历边界？？*/
            for(int j = i + 1; j < leaves.size(); j++) {
                List<TreeNode> list_i = map.get(leaves.get(i));
                List<TreeNode> list_j = map.get(leaves.get(j));
                for(int k = 0; k < Math.min(list_i.size(), list_j.size()); k++) {
                    // Same node in the trail means common ancestor
                    if(list_i.get(k) != list_j.get(k)) {
                        int dist = list_i.size() - k + list_j.size() - k;
                        if(dist <= distance) res++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    // DFS find all the leaf nodes.
    private void findLeaves(TreeNode node, List<TreeNode> trail, List<TreeNode> leaves, Map<TreeNode, List<TreeNode>> map) {
        if(node == null) return;
        List<TreeNode> tmp = new ArrayList<>(trail);
        tmp.add(node);
        if(node.left == null && node.right == null) {
            map.put(node, tmp);/**map里面存的是 当前节点（Treenode）跟它所有的子节点（list）*/
            leaves.add(node);/**leaves 有什么用啊？？好像只保存了最下面的子节点？？*/
            return;
        }
        findLeaves(node.left, tmp, leaves, map);
        findLeaves(node.right, tmp, leaves, map);
    }
}
