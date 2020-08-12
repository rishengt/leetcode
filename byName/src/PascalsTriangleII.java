/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 */
import java.util.*;
public class PascalsTriangleII {
    /**
     *  当然这道题你也可以先建一个Pascal's triangle 然后再取相对应的row，但难免显得有点捞，这里就不赘述了，忘了怎么写自己去看上一题，菜🐶！
     */

    /******************** 品品，什么叫优雅，什么叫代码。干！***************************************/
    public List<Integer> getRow(int rowIndex) {
        Integer[] result = new Integer[rowIndex + 1];
        Arrays.fill(result, 0);
        result[0] = 1;
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                result[j] += result[j - 1];
            }
        }
        return Arrays.asList(result);
    }
}
