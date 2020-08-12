/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
import java.util.*;
public class PascalsTriangle {
    public static void main(String[] args) {
        new PascalsTriangle().generate(5);
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i<numRows; i++){
            List<Integer> temp = new ArrayList<>();
            for(int j = 0; j<=i; j++){ /**这里的边界条件也是需要细细思考的。。。你还是想不出来。太菜了*/
                if(j==0 || j==i){/**这里太他妈精彩了，直接把一二两条都加进去了，完美避开下面所以会爆的坑*/
                    temp.add(1);
                }else{
                    List<Integer> last = list.get(i-1);/**这里完全不用担心会爆因为长度为一的时候已经加过了。。。*/
                    temp.add(last.get(j-1)+last.get(j));/**这里的规律确实需要思考， 你个傻逼肯定自己想不出来*/
                }
            }
            list.add(temp);
        }
        return list;
    }
}
