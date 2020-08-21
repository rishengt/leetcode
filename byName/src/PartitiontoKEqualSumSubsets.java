import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k,
 * find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 *
 * Note:
 *
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */
public class PartitiontoKEqualSumSubsets {
    public static void main(String[] args) {
        System.out.println(new PartitiontoKEqualSumSubsets().canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int target = 0;
        for(int i: nums) target+=i;
        if(target%k != 0) return false;
        target /=k;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        return backtrack(nums,k,0,0,target,visited,0);
    }

    public boolean backtrack(int[] nums, int k, int sum, int group, int target, boolean[] visited, int start){
        if(group == k) return true;
        if(sum == target ) return backtrack(nums,k,0,group+1,target,visited,0);/**这里为什么start可以是0啊？因为visited过的index不会再录入了，妙啊*/
        if(sum > target) return false;
        if(start == nums.length) return false;

        for(int i = start; i<nums.length; i++){
            if(visited[i])continue;
            if(i>0 && nums[i] == nums[i-1] && visited[i-1] == false) continue;
            visited[i] = true;
            if(backtrack(nums,k,sum+nums[i],group,target,visited,i+1)) return true;
            visited[i] = false;
        }
        return false;
    }

    public boolean canPartitionKSubsetsII(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return false;

        int n = nums.length;
        //result array
        boolean dp[] = new boolean[1<<n];
        int total[] = new int[1<<n];
        dp[0] = true;

        int sum = 0;
        for(int num : nums)
            sum += num;
        Arrays.sort(nums);

        if(sum%k != 0)
            return false;
        sum /= k;
        if(nums[n-1] > sum)
            return false;
        // Loop over power set
        for(int i = 0;i < (1<<n);i++) {
            if(dp[i]) {
                // Loop over each element to find subset
                for(int j = 0;j < n;j++) {
                    // set the jth bit
                    int temp = i | (1 << j);
                    if(temp != i) {
                        // if total sum is less than target store in dp and total array
                        if(nums[j] <= (sum- (total[i]%sum))) {
                            dp[temp] = true;
                            total[temp] = nums[j] + total[i];
                        } else
                            break;
                    }
                }
            }
        }
        return dp[(1<<n) - 1];
    }
}
