import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Combination {
    public static void main(String[] args) {
        System.out.println(new Combination().combine(2,4));
    }
    public List<List<Integer>> combine(int k, int n){
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), k,n,1);
        return ans;
    }
    public void backtrack(List<List<Integer>> ans, List<Integer> temp, int k, int n, int start){/**要是你不传这个start它每次都会从头开始backtrack，细品*/
        if(temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i<= n; i++){
            temp.add(i);
            backtrack(ans,temp,k,n,i+1);//这里i+1就是防止回头
            temp.remove(temp.size()-1);
        }
    }
}
