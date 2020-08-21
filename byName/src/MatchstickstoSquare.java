import java.util.Arrays;

/**
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 *
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 *
 *
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 *
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 */
public class MatchstickstoSquare {
    public static void main(String[] args) {
        System.out.println(new MatchstickstoSquare().makesquare(new int[]{1,1,2,2,2}));
        System.out.println(new MatchstickstoSquare().makesquare(new int[]{3,3,3,3,4}));
    }
    public boolean makesquare(int[] nums) {
        int target = 0;
        for(int i : nums) target+=i;
        if(target%4 != 0) return false;
        target /=4;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        return backtrack(nums,0,0,target,4,visited);
    }

    boolean backtrack(int[] nums, int start, int sum, int target, int sides, boolean[] visited){
        if(sides == 0) return true;
        if(sum == target) return backtrack(nums, 0,0,target, sides-1,visited);
        if(start == nums.length) return false;
        if(sum > target) return false;
        for(int i = start; i<nums.length; i++){
            if(visited[i]) continue;
            if(i>0 && nums[i] == nums[i-1] && visited[i-1] == false) continue;
            visited[i] = true;
            if(backtrack(nums,i+1, sum+nums[i],target,sides,visited)) return true;
            visited[i] = false;
        }
        return false;
    }
}
