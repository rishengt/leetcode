import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You need to ensure that a binary search tree can be serialized to a string,
 * and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The input tree is guaranteed to be a binary search tree.
 */
public class SerializeandDeserializeBST {
    private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
       StringBuilder sb = new StringBuilder();
       serialize(root,sb);
       return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb){
      if(root == null){
        sb.append("#").append(",");
      }
      else{
        sb.append(root.val).append(",");
        serialize(root.left,sb);
        serialize(root.right,sb);
      }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      return deserialize(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    public TreeNode deserialize(Queue<String> queue){
        String k = queue.poll();
        if(k.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(k));
        root.left = deserialize(queue);
        root.right = deserialize(queue);
        return root;
    }
}
