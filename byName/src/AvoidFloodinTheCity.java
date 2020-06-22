import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake,
 * the nth lake becomes full of water. If it rains over a lake which is full of water, there will be a flood.
 * Your goal is to avoid the flood in any lake.
 *
 * Given an integer array rains where:
 *
 * rains[i] > 0 means there will be rains over the rains[i] lake.
 * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
 * Return an array ans where:
 *
 * ans.length == rains.length
 * ans[i] == -1 if rains[i] > 0.
 * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
 * If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
 *
 * Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes. (see example 4)
 *
 *
 *
 * Example 1:
 *
 * Input: rains = [1,2,3,4]
 * Output: [-1,-1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day full lakes are [1,2,3]
 * After the fourth day full lakes are [1,2,3,4]
 * There's no day to dry any lake and there is no flood in any lake.
 * Example 2:
 *
 * Input: rains = [1,2,0,0,2,1]
 * Output: [-1,-1,2,1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day, we dry lake 2. Full lakes are [1]
 * After the fourth day, we dry lake 1. There is no full lakes.
 * After the fifth day, full lakes are [2].
 * After the sixth day, full lakes are [1,2].
 * It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
 * Example 3:
 *
 * Input: rains = [1,2,0,1,2]
 * Output: []
 * Explanation: After the second day, full lakes are  [1,2]. We have to dry one lake in the third day.
 * After that, it will rain over lakes [1,2]. It's easy to prove that no matter which lake you choose to dry in the 3rd day,
 * the other one will flood.
 * Example 4:
 *
 * Input: rains = [69,0,0,0,69]
 * Output: [-1,69,1,1,-1]
 *
 * Explanation: Any solution on one of the forms [-1,69,x,y,-1], [-1,x,69,y,-1] or [-1,x,y,69,-1] is acceptable where 1 <= x,y <= 10^9
 * Example 5:
 *
 * Input: rains = [10,20,20]
 * Output: []
 * Explanation: It will rain over lake 20 two consecutive days. There is no chance to dry any lake.
 */
public class AvoidFloodinTheCity {
    public static void main(String[] args) {
        new AvoidFloodinTheCity().avoidFlood(new int[]{1,2,0,1,2});
    }
    public int[] avoidFlood(int[] rains) {
        // the previous appeared idx of rains[i]
        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> zeros = new TreeSet<>();/**至于为什么要用TreeSet这个数据结构我功力不够想不明白*/
        int[] res = new int[rains.length];
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) {
                zeros.add(i);/**把下雨为零的天数存到set里*/
            } else {
                if (map.containsKey(rains[i])) {
                    // find the location of zero that can be used to empty rains[i]
                    Integer next = zeros.ceiling(map.get(rains[i]));/**精髓，找到不下雨的那天然后排水*/
                    if (next == null) return new int[0];/**要是不可能把水排干，那就返回空的array*/
                    res[next] = rains[i];
                    zeros.remove(next);/**用过一次不下雨的天数记得把它remove掉*/
                }
                res[i] = -1;/**凡事当天下雨，答案里的那天就变-1*/
                map.put(rains[i], i);/**把下雨以及对应的天数存到map里*/
            }
        }
        for (int i : zeros) res[i] = 1;
        return res;
    }
}
