import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

public class NQueens {

    public List<List<String>> solveNQueens(int n ){
        List<List<String>> ans = new ArrayList<>();
        backtrack(new ArrayList<Integer>(), ans, n);
        return ans;
    }

    public void backtrack(List<Integer> currentQueen, List<List<String>> ans, int n){
        /**currentQueen按行遍历，index代表所在棋盘行数，index上的值代表所在行数放Queen的列数，妙啊！！！*/
        if(currentQueen.size() == n) {
            List<String> temp = new ArrayList<>();
            for(int i = 0; i<n; i++){
                char[] t = new char[n];
                Arrays.fill(t, '.');
                t[currentQueen.get(i)] = 'Q';
                temp.add(new String(t));
            }
            ans.add(temp);
            return;
        }
        for(int col = 0; col<n; col++){
            if(!currentQueen.contains(col)){
                if(isDiagonalAttack(currentQueen, col)){
                    continue;
                }
                currentQueen.add(col);
                backtrack(currentQueen,ans,n);
                currentQueen.remove(currentQueen.size()-1);
            }
        }
    }

     boolean isDiagonalAttack(List<Integer> currentQueen, int i ){
        int current_row = currentQueen.size();
        int current_col = i;
        for(int row = 0; row < currentQueen.size(); row++){
            /**如果是在对角线上行数差和列数差的绝对值要相等，不然就不是在对角线上，细品*/
            if(Math.abs(current_row-row) == Math.abs(current_col- currentQueen.get(row))){
                return true;
            }
        }
        return false;
    }
}
