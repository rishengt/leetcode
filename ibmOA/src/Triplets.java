import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of n distinct integers, and an integer threshold, t, how many (a,b,c) index triplets exist that satisfy both of the following conditions?
 *      d[a]<d[b]<d[c]
 *      d[a]<d[b]+d[c] <= t
 *
 * Example
 *      d = [1,2,3,4,5]
 *      t = 8
 *      The following 4 triplets satisfy the constraints:
 *          (1,2,3) -> 1+2+3 = 6 <= 8
 *          (1,2,4) -> 1+2+4 = 7 <= 8
 *          (1,2,5) -> 1+2+5 = 8 <= 8
 *          (1,3,4) -> 1+3+4 = 8 <= 8
 *
 * Function Description
 * Complete the function triplets in the editor below.
 *
 * triplets has the following parameter(s):
 *      int t: an integer threshold
 *      int d[n]: an array of integers
 *
 * Returns:
 *      long: a long integer that denotes the number of (a,b,c) triplets that satisfy the given conditions
 */
public class Triplets {
    public static void main(String[] args) {
        Triplets t = new Triplets();
        System.out.println(t.triplets(8, new int[]{1,2,3,4,5}));
    }
    public long triplets(int t, int[] array){
        long ans = 0;
        Arrays.sort(array);
        List<List<Integer>> fk = new ArrayList<>();
        backtrack(t, array,new ArrayList<>(), ans, new boolean[array.length], fk,0);
        return (long)fk.size();
    }

    public void backtrack(int n, int[] array, List<Integer> temp, long ans, boolean[] used, List<List<Integer>> fk, int start){
        if(temp.size()==3 && n>=0){
            fk.add(new ArrayList<>(temp));
            ans++;
            return;
        }
        for(int i = start; i< array.length; i++){
            if(used[i]) continue;
            used[i] = true;
            temp.add(array[i]);
            backtrack(n-array[i], array, temp, ans, used, fk,i+1);
            used[i] = false;
            temp.remove(temp.size()-1);
        }
    }
}
