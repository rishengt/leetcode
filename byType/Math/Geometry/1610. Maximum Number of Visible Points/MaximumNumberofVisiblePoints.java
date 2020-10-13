import java.util.*;
/**
 * You are given an array points, an integer angle, and your location, where location = [posx, posy]
 * and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.
 *
 * Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate.
 * In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle,
 * determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise.
 * Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
 *
 *
 * You can see some set of points if, for each point, the angle formed by the point, your position,
 * and the immediate east direction from your position is in your field of view.
 *
 * There can be multiple points at one coordinate. There may be points at your location,
 * and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.
 *
 * Return the maximum number of points you can see.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * Output: 3
 * Explanation: The shaded region represents your field of view. All points can be made visible in your field of view,
 * including [3,3] even though [2,2] is in front and in the same line of sight.
 * Example 2:
 *
 * Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * Output: 4
 * Explanation: All points can be made visible in your field of view, including the one at your location.
 * Example 3:
 *
 *
 * Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * Output: 1
 * Explanation: You can only see one of the two points, as shown above.
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 105
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 109
 */
public class MaximumNumberofVisiblePoints {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int count = 0;
        for(List<Integer> list: points){
            if(list.get(0) == location.get(0) && list.get(1) == location.get(1)){
                count++;
                continue;/**这个continue就关键了，因为已经跟观测点重合所以就不用往arctan里面夹了，不管什么角度都能观测到*/
            }
            double dx = list.get(0) - location.get(0);
            double dy = list.get(1) - location.get(1);
            /**这里是整个算法的精华了。这样可以得到一条以观测点为原点连接其他点与观测点的x轴形成的较小的夹角,乘以180/PI转为double的弧度*/
            double radius = Math.atan2(dx, dy) * (180/Math.PI);
            angles.add(radius);
        }
        int ans = count;
        /**
         * 本题的第二个考点是“首尾相接”。因为视野角度接近360度的目标点，与视野角度接近0度的目标点，其真实角度差范围并不大。
         * 那么我们如果寻找一个滑窗使得能够同时覆盖这两部分的点呢？处理的方法很常见，那就是将所有目标点的视野角度复制一遍、
         * 加上2pi、并拼接在angles数组后面。
         */
        List<Double> temp = angles;
        for(double d: angles){
            temp.add(d+360);
        }
        Collections.sort(temp);
        for(int i = 0, j = 0; i< temp.size(); i++){
            while(temp.get(j)-temp.get(i)<=angle){
                j++;
            }
            ans = Math.max(ans, count+j-i);
        }
        return ans;
    }
}
