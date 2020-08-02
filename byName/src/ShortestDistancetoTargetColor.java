import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 *
 * You are also given some queries. Each query consists of two integers i and c,
 * return the shortest distance between the given index i and the target color c. If there is no solution return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
 * Output: [3,0,3]
 * Explanation:
 * The nearest 3 from index 1 is at index 4 (3 steps away).
 * The nearest 2 from index 2 is at index 2 itself (0 steps away).
 * The nearest 1 from index 6 is at index 3 (3 steps away).
 * Example 2:
 *
 * Input: colors = [1,2], queries = [[0,3]]
 * Output: [-1]
 * Explanation: There is no 3 in the array.
 *
 *
 * Constraints:
 *
 * 1 <= colors.length <= 5*10^4
 * 1 <= colors[i] <= 3
 * 1 <= queries.length <= 5*10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] < colors.length
 * 1 <= queries[i][1] <= 3
 * Difficulty:
 * Medium
 * Lock:
 * Prime
 * Company:
 * Google
 */
public class ShortestDistancetoTargetColor {
    public static void main(String[] args) {
        ShortestDistancetoTargetColor s = new ShortestDistancetoTargetColor();
        int[] k = s.shortestDistance(new int[]{1,1,2,1,3,2,2,3,3}, new int[][]{{1,3},{2,2},{6,1}});
        for (int j: k){
            System.out.println(j);
        }
        int[] fk = s.shortestDistance(new int[]{1,2}, new int[][]{{0,3}});
        for(int j: fk){
            System.out.println(j);
        }
    }

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int[][] colorCount = new int[4][colors.length]; // 前缀和
        colorCount[colors[0]][0]=1; // 将第一个颜色加入前缀和数组
        for(int i=1;i<colors.length;i++){ // 循环数组中每个颜色
            for(int j=1;j<=3;j++){ // 更新当前每种颜色的前缀和
                if(j==colors[i]){ // 如果i为当前颜色，
                    // 当前颜色个数加一
                    colorCount[j][i] = colorCount[j][i-1]+1;
                }else{
                    // 如果i不是当前颜色，当前颜色个数不变
                    colorCount[j][i] = colorCount[j][i-1];
                }
            }
        }
        // 返回结果
        List<Integer> res = new ArrayList<>();
        for(int[] query : queries){ // 循环每个query
            // 查看当前位置下存在多少个目标颜色
            int target = colorCount[query[1]][query[0]];
            // 距离左边目标颜色的长度
            int dis1=binarySearch(colorCount[query[1]],target,query[0]);
            // 距离右边目标颜色的长度
            int dis2=binarySearch(colorCount[query[1]],target+1,query[0]);
            // 最小距离
            int min = Math.min(dis1,dis2);
            // 如果最小距离非法，返回-1
            res.add(min==colors.length?-1:min);
        }
        return res;
    }
    // arr：该颜色出现个数的前缀和数组
    // target：出现第target颜色所在的位置
    // index：当前index
    // 返回值：出现第target颜色所在的位置与当前index间的距离
    int binarySearch(int[] arr, int target, int index){
        // 如果target为0或者target越界，返回一个非法值
        if(target>arr[arr.length-1] || target==0){
            return arr.length;
        }
        // 二分查找
        int left=0, right=arr.length-1;
        while(left<right){
            int mid=(left+right)/2;
            if(arr[mid]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return Math.abs(left-index);
    }


    /************************************************* LC 14/19, TLE **************************************************/
    int[] shortestDistance(int[] color, int[][] queries){
        HashMap<Integer, List<Integer>> map = buildMap(color);
        int[] ans = new int[queries.length];
        for(int i = 0; i< queries.length; i++){
            ans[i] = BinarySearch(color,queries[i],map);
        }
        return ans;
    }

    int BinarySearch(int[] color, int[] i, HashMap<Integer, List<Integer>> map){
            int distance = Integer.MAX_VALUE;
            List<Integer> list = map.get(i[1]);
            if(list == null || list.size() ==0) return -1;
            if(list.size() == 1 || i[0]<list.get(0)) return list.get(0)-i[0];
            if(i[0]> list.get(list.size()-1)) return i[0]-list.get(list.size()-1);
            for(int j : list){
                distance = Math.min(distance,Math.abs(j-i[0]));
            }
            return distance;
    }
    public HashMap<Integer, List<Integer>> buildMap(int[] color){
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, new ArrayList<>());
        map.put(2, new ArrayList<>());
        map.put(3, new ArrayList<>());
        for(int i = 0; i<color.length; i++){
            map.get(color[i]).add(i);
        }
        for(List k: map.values()){
            Collections.sort(k);
        }
        return map;
    }
}
