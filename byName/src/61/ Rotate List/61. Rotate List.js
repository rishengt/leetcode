/**
 Given a linked list, rotate the list to the right by k places, where k is non-negative.

 Example 1:

 Input: 1->2->3->4->5->NULL, k = 2
 Output: 4->5->1->2->3->NULL
 Explanation:
 rotate 1 steps to the right: 5->1->2->3->4->NULL
 rotate 2 steps to the right: 4->5->1->2->3->NULL
 Example 2:

 Input: 0->1->2->NULL, k = 4
 Output: 2->0->1->NULL
 Explanation:
 rotate 1 steps to the right: 2->0->1->NULL
 rotate 2 steps to the right: 1->2->0->NULL
 rotate 3 steps to the right: 0->1->2->NULL
 rotate 4 steps to the right: 2->0->1->NULL
 **/
function ListNode(val,next) {
    this.val = (val === undefined? 0:val);
    this.next = (next === undefined? null: next);
}
var rotateRight = function(head, k) {
    if(head === null || head.next === null) return head;
    let len = 1;
    let tail = head;
    while(head.next!= null){
        head = head.next;
        len++;
    }
    k %= len;
    head.next = tail;
    head = head.next;
    for(let i = 0; i<len-k-1; i++){
        head = head.next;
    }
    let ans = head.next;
    head.next = null;
    return ans;
};

const head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
head.next.next = new ListNode(4);
head.next.next.next = new ListNode(5);

console.log(rotateRight(head,2));