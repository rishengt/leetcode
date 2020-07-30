/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 *
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(new ReverseLinkedListII().reverseBetween(head,1,5));
    }



    /************************************** 我自己绞尽脑汁才写出来的答案，太垃圾了 ********************************************/
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode tempHead = new ListNode(0);
        ListNode pointer = head;
        ListNode cur = head;
        if (m == n) return head;
        for(int i = 1; i < m; i++){ /**先找到你的断点处；*/
            head = head.next;
        }
        tempHead = head;
        int k = n-m+1;
        ListNode suck = null;
        while(k>0){                /** 把要反转的反转 */
            ListNode temp = tempHead.next;
            tempHead.next = suck;
            suck = tempHead;
            tempHead = temp;
            k--;
        }
        if(cur.next == null){ /**分两种情况，要是从头就要翻转，你现在的头就是反转过后的头*/
            cur = suck;
            if(tempHead != null){ /**判断尾巴是不是有剩余，有的话就接上。。。*/
                while(suck.next!=null)suck = suck.next;
                suck.next = tempHead;
            }
            return cur;
        }
        else{                /** 不然的话， 找到断点处的前一位然后接上*/
            for(int i = 1; i<m-1; i++){
                cur = cur.next;
            }
            cur.next = suck;
            if(tempHead!=null){ /**判断尾巴是不是有剩余，有的话就接上。。。*/
                while(cur.next!=null) cur = cur.next;
                cur.next = tempHead;
            }
        }
        return pointer;
    }
}
