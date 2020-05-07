import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 */
public class FindMedianFromDataStream {
    public static void main(String[] args) {
        FindMedianFromDataStream a = new FindMedianFromDataStream();
        a.addNum(1);
        a.addNum(2);
        System.out.println(a.findMedian());
        a.addNum(3);
        System.out.println(a.findMedian());
    }
    Queue<Integer> small;
    Queue<Integer> big;
    public FindMedianFromDataStream() {
        small = new PriorityQueue<Integer>((a,b)->(b-a));/**直接重写comparator就行了。。。。。。好菜啊你**/
                                          /**Collections.reverseOrder()也行**/
        big = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(small.size() == 0 || small.peek()>num){small.offer(num);}/**也是为了确保排序正确*/
        else big.offer(num); /**先怼到big里确保排序正确**/
        if(small.size()-big.size()>1) {big.offer(small.poll());}/**为了平分控制两个queue只差一个数*/
        if(small.size()<big.size()) {small.offer(big.poll());}/**这两步这么精髓忘了现场能敲出来吗你这个菜鸡。。。。*/
    }

    public double findMedian() {
        if((small.size()+big.size())%2 == 0) return ((double)small.peek()+big.peek())/2;
        else return (double)small.peek();
    }
}
