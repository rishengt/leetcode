import java.util.Arrays;

/**
 * In universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket.
 * Rick has n empty baskets, the ith basket is at position[i],
 * Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
 *
 * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
 *
 * Given the integer array position and the integer m. Return the required force.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: position = [1,2,3,4,7], m = 3
 * Output: 3
 * Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6].
 * The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
 * Example 2:
 *
 * Input: position = [5,4,3,2,1,1000000000], m = 2
 * Output: 999999999
 * Explanation: We can use baskets 1 and 1000000000.
 *
 *
 * Constraints:
 *
 * n == position.length
 * 2 <= n <= 10^5
 * 1 <= position[i] <= 10^9
 * All integers in position are distinct.
 * 2 <= m <= position.length
 */
public class MagneticForceBetweenTwoBalls {
    public static void main(String[] args) {
        System.out.println(new MagneticForceBetweenTwoBalls().maxDistance(new int[]{1,2,3,4,7},3));
        System.out.println(new MagneticForceBetweenTwoBalls().maxDistance(new int[]{5,4,3,2,1,1000000000},2));
    }
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int start = 0, end = position[position.length-1], max = 0;
        while(start<=end){
            int mid = (start+end)>>>1;
            if(canPut(position,m,mid)){
                max = mid;
                start = mid+1;
            }else{
                end = mid -1;
            }
        }
        return max;
    }
    
    public boolean canPut(int[] position, int m, int distance){
        int last = position[0];
        int count = 1;/**???*/
        for(int i = 0 ;i<position.length; i++){
            if(position[i]-last >=distance){
                count++;
                last = position[i];
            }
        }
        return count>=m;
    }
}
