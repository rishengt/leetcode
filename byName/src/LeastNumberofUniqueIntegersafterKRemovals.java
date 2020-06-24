import java.util.*;

/**
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 */
public class LeastNumberofUniqueIntegersafterKRemovals {
    public static void main(String[] args) {
        System.out.println(new LeastNumberofUniqueIntegersafterKRemovals().findLeastNumOfUniqueInts(new int[]{2,1,1,3,3,3},3));
    }
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : arr){
            map.put(i, map.getOrDefault(i,0)+1);
        }
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            list.add(entry);
        }
        Collections.sort(list,(a,b)->a.getValue()-b.getValue());
        while(true){
            int i = 0;
            int j = list.get(i).getValue();
            if(j<=k){
                k -= j;
                list.remove(i);
            }else{
                break;
            }
        }
        return list.size();
    }
}
