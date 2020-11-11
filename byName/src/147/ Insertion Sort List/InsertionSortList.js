function ListNode(val, next){
    this.val = (val === undefined ? 0 : val)
    this.next = (next === undefined? null : next)
}
var intsertionSortList = function (head) {
    let pseudo = new ListNode(), preNode, preNext;
    let cur = head;
    while(cur!=null){
        preNode = pseudo;
        preNext = pseudo.next;
        while(preNext!= null){
            if(cur.val<preNext.val){
                break;
            }
            preNode = preNode.next;
            preNext = preNext.next;
        }
        let temp = cur.next;
        preNode.next = cur;
        cur.next = preNext;
        cur = temp;
    }
    return pseudo.next;
}

const head = new ListNode(4);
head.next = new ListNode(3);
head.next.next = new ListNode(5);
console.log(intsertionSortList(head));