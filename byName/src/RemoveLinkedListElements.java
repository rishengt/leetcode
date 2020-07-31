/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        System.out.println(new RemoveLinkedListElements().removeElements(head,6));
    }
    public ListNode removeElements(ListNode head, int val) {
        ListNode pointer = head;
        while(head.next != null){
            ListNode temp = head.next;
            if(temp.val == val){
                if(temp.next == null){
                    head.next = null;
                    break;
                }
                else {
                    head.next = temp.next;
                }
            }
            head = head.next;
        }
        return pointer;
    }
}
