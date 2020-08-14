import java.util.*;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int ans[] = new SlidingWindowMaximum().maxSlidingWindow(new int[]{1 , 3 , -1, -3 , 5 , 3 , 6 , 7}, 3);
        for(int k: ans) System.out.println(k);
    }

    /*******这种有重复element的虽然已经没有傻逼到用hashmap去存但是还是没能come up存index然后通过index去查原数的想法，还是菜🐶一条，草****/
//    public int[] maxSlidingWindow(int[] nums, int k){
//        Deque<Integer> deque = new LinkedList<>();
//        int[] ans = new int[nums.length-k+1];
//        for(int i = 0; i<nums.length; i++){
//            while(!deque.isEmpty() && i-deque.peekFirst()>k){
//                deque.removeFirst();
//            }
//            while(!deque.isEmpty() && nums[deque.getFirst()])
//            deque.addLast(i);
//        }
//    }


    /*****************这玩意儿在得到了queue是可以remove（E）的情况下还是勉强可以写出来的，虽然比下面的快一丢丢但是还是很捞******************************/
    public int[] maxSlidingWindowII(int[] nums, int k){
        int[] ans = new int[nums.length-k+1];
        Queue<Integer> queue = new PriorityQueue<>((a,b) -> b-a);
        for(int i = 0; i<k; i++){
            queue.offer(nums[i]);
        }
        ans[0] = queue.peek();
        for(int i = k; i<nums.length; i++){
            int left = nums[i-k];
            queue.remove(left);
            queue.add(nums[i]);
            ans[i-k+1] = queue.peek();
        }
        return ans;
    }


    /*******************这东西应该是行的但是TLE，必须优化************************************/
    public int[] maxSlidingWindowIII(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
//        int ans[] = new int[nums.length-k+1];
        Queue<Integer> queue = new PriorityQueue<>((a,b)->b-a);
        for(int i = 0; i<=nums.length-k; i++){
            for(int j = i; j<i+k;j++){
                queue.offer(nums[j]);
            }
            list.add(queue.poll());
            while(!queue.isEmpty())queue.poll();
            queue.remove(i);
        }
        int ans[] = new int[list.size()];
        for(int i = 0; i<list.size(); i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
}
