import java.util.*;
/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 *
 * Buildings Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the Date.format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
public class TheSkylineProblem {
    public static void main(String[] args) {
        System.out.println(new TheSkylineProblem().getSkyline(new int[][]{{0,2,3},{2,5,3}}));
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> height = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for(int[] b: buildings){
            height.add(new int[]{b[0],-b[2]});
            height.add(new int[]{b[1],b[2]});
        }
        TreeMap<Integer, Integer> map = new TreeMap<>((a,b)-> b-a);
        Collections.sort(height, (a,b)-> a[0] == b[0]? a[1]-b[1]: a[0]-b[0]);
        map.put(0,1);
        int preheight = 0, curheight = 0;
        for(int[] i: height){
            if(i[1]<0){
                map.put(-i[1], map.getOrDefault(-i[1],0)+1);
            }else{
                if(map.get(i[1]) == 1){
                    map.remove(i[1]);
                }else{
                    map.put(i[1],map.get(i[1])-1);
                }
            }
            curheight = map.firstKey();
            if(curheight!=preheight){
                List<Integer> temp = new ArrayList<>();
                temp.add(i[0]);
                temp.add(curheight);
                ans.add(temp);
                preheight = curheight;
            }
        }
        return ans;
    }
}
