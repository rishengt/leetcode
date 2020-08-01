import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string that might have multiple occurrences of the same character, return the closest same character of any indicated character in the string.
 * You are given the string s and n number of queries. In each query, you are given an index a (where 0<=a<=lsl) of the a character, and you need to print
 * the index of the closest same character. If ther are multiple answers, print the smallest one, or if there is no such index print -1 instead.
 *
 * For example, for the string s = babab, with a given query 2, there are two catching characters at indices 0 and 4, each 2 away, so we choose the lower
 * of the two: 0.
 *
 * Function Description
 * Complete the function closest in the editor below. The function must return an integer vector of size n that denotes the answer of each query.
 *
 * closest has the following parameters:
 *      s: the original string
 *      queries: an array of n integers, where the value of each element queries[i] is an index of a character whose closest same character's index needs
 *            to be found
 *
 * Example
 *      input: hackerrank, [4,1,6,8]
 *      print: -1, 7, 5, -1(int new line);
 */
public class WhoIsTheClosest {
    public static void main(String[] args) {
        WhoIsTheClosest a = new WhoIsTheClosest();
        a.closest("abaaa", new int[]{0, 2, 3, 4}); /**should be [2,3,2,3];*/
        a.closest("hackerrank", new int[]{4,1,6,8});
    }

    public void closest(String s, int[] queries){
        HashMap<Character, List<Integer>> map = buildMap(s);
        for(int i: queries){
            List<Integer> temp = map.getOrDefault(s.charAt(i),Collections.EMPTY_LIST);
            if(temp.size() <= 1) System.out.println(-1);
            else{
                Collections.sort(temp);
                int k = binarysearch(temp,i);
//                if(temp.get(0)!=i) System.out.println(temp.get(0));
                System.out.println(k);
            }
        }
    }

    public int binarysearch(List<Integer> list, int target){
        int left = 0, right = list.size()-1;
        while(left<=right){
            int mid = (left+right) >>> 1;
            if(list.get(mid) == target){
                if(mid+1>=list.size()) return list.get(mid-1);
                else if(mid-1<0) return list.get(mid+1);
                else return Math.abs(list.get(mid)-list.get(mid-1))>Math.abs(list.get(mid)-list.get(mid+1))? list.get(mid+1) : list.get(mid-1);
            }
            else if(mid>target){
                right = mid -1;
            }
            else{
                left = mid + 1;
            }
        }
        return -1;
    }

    public HashMap<Character, List<Integer>> buildMap(String s){
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0; i< s.length(); i++){
            map.putIfAbsent(s.charAt(i), new ArrayList<Integer>());
            map.get(s.charAt(i)).add(i);
        }
        return map;
    }
}
