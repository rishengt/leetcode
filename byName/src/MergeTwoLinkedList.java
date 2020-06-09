import java.util.PriorityQueue;
import java.util.Queue;

public class MergeTwoLinkedList {
    public static void main(String[] args) {
        ListNode L1 = new ListNode(1);
        L1.next = new ListNode(2);
        L1.next.next = new ListNode(4);
        ListNode L2 = new ListNode(1);
        L2.next = new ListNode(3);
        L2.next.next = new ListNode(4);
        ListNode head = new MergeTwoLinkedList().merge(L1,L2);
        while (head!= null){
            System.out.println(head.val);
            head = head.next;
        }
    }
    public ListNode merge(ListNode L1, ListNode L2){
        if(L1 == null && L2 == null) return null;
        Queue<ListNode> queue = new PriorityQueue<>((a,b) -> a.val-b.val);
        if (L1!=null){
            queue.offer(L1);
        }
        if (L2!=null){
            queue.offer(L2);
        }
        ListNode head = new ListNode(0);
        ListNode dummy = head;
        while(!queue.isEmpty()){
            head.next = queue.poll();
            head = head.next;
            if(head.next!=null){
                queue.offer(head.next);
            }
        }
        return dummy.next;
    }

    /*********用pointer跳来跳去，挺精妙的。我是码农之耻*/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pointer = new ListNode(-1);
        ListNode head = pointer;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pointer.next = l1;/**先把next建起来是关键*/
                l1 = l1.next;
            } else {
                pointer.next = l2;
                l2 = l2.next;
            }
            pointer = pointer.next;
        }

        if (l1 != null) {
            pointer.next = l1;
        }
        if (l2 != null) {
            pointer.next = l2;
        }

        return head.next;
    }
}

