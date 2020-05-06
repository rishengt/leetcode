import java.util.Arrays;

/**
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon,
 * provided input is the start and end coordinates of the horizontal diameter.
 * Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice.
 * Start is always smaller than end. There will be at most 104 balloons.
 *
 * An arrow can be shot up exactly vertically from different points along the x-axis.
 * A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend.
 * There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely.
 * The problem is to find the minimum number of arrows that must be shot to burst all balloons.
 *
 * Example:
 *
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */

public class MinimumNumberofArrowstoBurstBalloons {
    public static void main(String[] args) {
        System.out.println(new MinimumNumberofArrowstoBurstBalloons().minArrow(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
    }

    public int minArrow(int[][] ballons){
        if(ballons.length == 0) return 0;
        Arrays.sort(ballons, (int[] a, int[] b) -> a[1]-b[1]);
        int end = ballons[0][1];
        int ans = 1;  /***妈的关键啊，至少有一个气球，要射一发，dp的base case啊！！！！！！****/
        for(int i = 0; i< ballons.length; i++){
            if(ballons[i][0]>end){
                ans++;
                end = ballons[i][1];
            }
        }
        return ans;
    }
}
