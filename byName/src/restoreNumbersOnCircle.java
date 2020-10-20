import java.util.*;
/**
 * /**一些数字构成一个环，比如
 *  * 5-3-4-2-3-(5 in head)
 *  * 这个环可以表示为(5,3),(3,4),(4,2),(2,3),(3,5). 环中每个pair是distinct得
 *  * 现在这些pair被随机shuffle， start，end可能被对调，比如shuffle后得到
 *  * (3,4),(5,3),(2,4),(3,5),(3,2)
 *  * 求重构这个环
 *  *
 *  * Input:[(3,4),(5,3),(2,4),(3,5),(3,2)]
 *  * Output:[5,3,4,2,3]
 *  * @param numbers
 *  */
public class restoreNumbersOnCircle {
    public static void main(String[] args) {
        System.out.println(new restoreNumbersOnCircle().restoreNumbersOnCircle(new int[][]{{3,4},{5,3},{2,4},{3,5},{3,2}}));
    }
    private class ListNode{
        int val;
        ListNode next;
        ListNode pre;
        public ListNode(int val){
            this.val = val;
        }
    }
    public List<Integer> restoreNumbersOnCircle(int[][] numbers){
        ListNode head  = new ListNode(numbers[0][0]);
        ListNode tail = new ListNode(numbers[0][1]);
        head.next = tail;
        tail.pre = head;
        boolean visited[] = new boolean[numbers.length];
        visited[0] = true;
        int count = numbers.length-1;
        while(count>0) {
            for (int i = 1; i < numbers.length; i++) {
                if (!visited[i] && numbers[i][0] == tail.val) {
                    tail.next = new ListNode(numbers[i][1]);
                    tail = tail.next;
                    visited[i] = true;
                    break;
                }else if(!visited[i] && numbers[i][1] == tail.val){
                    tail.next = new ListNode(numbers[i][0]);
                    tail = tail.next;
                    visited[i] = true;
                    break;
                }
            }
            count--;
        }
        List<Integer> ans = new ArrayList<>();
        while(head.next!=null){
            ans.add(head.val);
            head = head.next;
        }
        return ans;
    }
}
