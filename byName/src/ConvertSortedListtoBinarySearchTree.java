/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ
 * by more than 1.
 *
 * Example:
 *
 * Given the sorted linked list: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedListtoBinarySearchTree {
    /**你个弱智当然可以把linkedList转成array然后用建树圣经来做，但是！！！！！（你先把圣经记住啊！！！！）*/

    /***为了显得你个垃圾高端一点，用一个逆inorder的写法来写*/
    ListNode cur = null;
    public TreeNode sortedListToBST(ListNode head){
        cur = head;
        int end = 0;
        while (head != null) {
            end++;
            head = head.next;
        }
        return sortedArrayToBSTHelper(0, end);
    }

    private TreeNode sortedArrayToBSTHelper(int start, int end) {
        if (start == end) {
            return null;
        }
        int mid = (start + end) >>> 1;
        //遍历左子树并且将根节点返回
        TreeNode left = sortedArrayToBSTHelper(start, mid);
        //遍历当前根节点并进行赋值
        TreeNode root = new TreeNode(cur.val);
        root.left = left;
        cur = cur.next; //指针后移，进行下一次的赋值
        //遍历右子树并且将根节点返回
        TreeNode right = sortedArrayToBSTHelper(mid + 1, end);
        root.right = right;
        return root;
    }
}
