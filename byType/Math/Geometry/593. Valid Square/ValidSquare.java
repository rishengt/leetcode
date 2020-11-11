import java.util.*;
/**
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 *
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 *
 * Example:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 *
 *
 * Note:
 *
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 */
public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        double dis1_2 = Math.sqrt(Math.pow(p1[0]-p2[0],2) + Math.pow(p1[1]-p2[1],2));
        double dis1_3 = Math.sqrt(Math.pow(p1[0]-p3[0],2) + Math.pow(p1[1]-p3[1],2));
        double dis1_4 = Math.sqrt(Math.pow(p1[0]-p4[0],2) + Math.pow(p1[1]-p4[1],2));
        double dis2_3 = Math.sqrt(Math.pow(p2[0]-p3[0],2) + Math.pow(p2[1]-p3[1],2));
        double dis2_4 = Math.sqrt(Math.pow(p2[0]-p4[0],2) + Math.pow(p2[1]-p4[1],2));
        double dis3_4 = Math.sqrt(Math.pow(p3[0]-p4[0],2) + Math.pow(p3[1]-p4[1],2));
        double list[]  = new double[6];
        list[0] = dis1_2;
        list[1] = dis1_3;
        list[2] = dis1_4;
        list[3] = dis2_3;
        list[4] = dis2_4;
        list[5] = dis3_4;
        Arrays.sort(list);
        for(int i = 0; i<3; i++){
            if(list[i]!=list[i+1] || list[i]>= list[4] || list[i] == 0 || list[4] == 0) return false;
        }
        if(list[4] != list[5]) return false;
        return true;
    }
}
