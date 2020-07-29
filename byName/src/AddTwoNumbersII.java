/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode ans = add(l1,l2);
        ans = reverse(ans);
        return ans;
    }

    public ListNode reverse(ListNode l1){
        ListNode dummy = null;
        while(l1!=null){
            ListNode temp = l1.next;
            l1.next = dummy;
            dummy = l1;
            l1 = temp;
        }
        return dummy;
    }

    public ListNode add(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode pointer = dummy;
        int carry = 0;
        while(l1!=null || l2!=null){
            int sum = carry;
            if(l1!=null){
                sum +=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                sum+=l2.val;
                l2 = l2.next;
            }
            ListNode cur = new ListNode(sum%10);
            dummy.next = cur;
            dummy = cur;
            carry = sum/10;
        }
        if(carry!=0) dummy.next = new ListNode(carry);
        return pointer.next;
    }
}
