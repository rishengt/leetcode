import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively.
 * You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 *
 * Return True if the path crosses itself at any point, that is, if at any time you are on a location you've previously visited.
 * Return False otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: path = "NES"
 * Output: false
 * Explanation: Notice that the path doesn't cross any point more than once.
 * Example 2:
 *
 *
 *
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 */
public class PathCrossing {
    public static void main(String[] args) {
        //System.out.println(new PathCrossing().isPathCrossing("NES"));
        System.out.println(new PathCrossing().isPathCrossing("NESWW"));
    }

    public boolean isPathCrossing(String path) {
        Set<String> seen = new HashSet<>();
        seen.add(0 + ", " + 0);
        for (int i = 0, x = 0, y = 0; i < path.length(); ++i) {
            char c = path.charAt(i);
            if (c == 'N') {
                y += 1;
            }else if (c == 'S') {
                y -= 1;
            }else if (c == 'E') {
                x -= 1;
            }else {
                x += 1;
            }
            if (!seen.add(x + ", " + y)) {
                return true;
            }
        }
        return false;
    }

    /**凡是new的一个新对象都是新开辟的一块heap内存，hashCode不重复，所以set检测不到
    public boolean isPathCrossingII(String path) {
        Map<Character, int[]> map = new HashMap<>();
        map.put('N', new int[]{0,1});
        map.put('S', new int[]{0,-1});
        map.put('E',new int[]{1,0});
        map.put('W', new int[]{-1,0});
        int[] origin = new int[]{0,0};
        Set<int[]> set = new HashSet<>();
        set.add(origin);
        int[] pointer = origin;
        for(int i = 0; i < path.length(); i++ ){
            int[] temp = new int[2];
            temp[0] = pointer[0] + map.get(path.charAt(i))[0];
            temp[1] = pointer[1] + map.get(path.charAt(i))[1];
            pointer = temp;
            if(set.contains(pointer)){
                return true;
            }else{
                set.add(pointer);
            }
        }
        return false;
    }
     */
}
