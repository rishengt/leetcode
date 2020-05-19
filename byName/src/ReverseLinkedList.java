/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = null;
        ListNode newHead = new ReverseLinkedList().reverse(head);
        while(newHead !=null){
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
    public ListNode reverse(ListNode head){
        ListNode pre = null;
        while(head !=null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }



/*******************************************花里胡哨的递归写法**************************************************************/
    public ListNode RecusiveReverse(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode p = RecusiveReverse(head);
        head.next.next = head;
        head.next = null;
        return p;
    }
}

class ListNode{
    int val;
    ListNode next;
    public ListNode(){};
    public ListNode(int x){
        this.val = x;
    }
}
