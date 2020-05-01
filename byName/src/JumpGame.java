/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {
    public static void main(String[] args) {
        System.out.println(new JumpGame().greedy(new int[]{2,3,1,1,4}));
        System.out.println(new JumpGame().greedy(new int[]{3,2,1,0,4}));
    }

    public boolean jump(int[] nums){
        return backtrack(nums,0);
    }

    public boolean backtrack(int[] nums, int index){
        if(index == nums.length-1) return true;
        int furthestjump = Math.min(index+nums[index], nums.length-1);/**这里贼他妈关键*/
        for(int nextstep = index+1; nextstep<=furthestjump; nextstep++){/**nextstep = index+1 估计会记不住， greedy更符合逻辑*/
            if(backtrack(nums,nextstep)) return true;
        }
        return false;
    }
   /**记这个吧！！！！ *****/
    public boolean greedy(int[] nums){
        int lastPosition = nums.length-1;
        for(int i = nums.length-1; i>=0; i--){
            if(nums[i]+i>=lastPosition) lastPosition = i;
        }
        return lastPosition == 0;
    }

    public boolean jumpGameII(int[] nums){/**正向贪心*/
        int end = 0;
        int maxPosition = 0;
        for(int i = 0; i<nums.length-1; i++){
            if(end<i) return false; /**end 没有更新证明出现了0，返回fasle*/
            maxPosition = Math.max(maxPosition, nums[i]+i);
            if( i == end){
                end = maxPosition;
            }
        }
        return maxPosition>=nums.length-1;
    }
}
