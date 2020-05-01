import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example:
 *
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
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
 */

public class NQueensII {
    public static void main(String[] args) {
        System.out.println(new NQueensII().totalQueens(4));
    }

    public int totalQueens(int n){
        List<List<Integer>> ans = new ArrayList<>();
        totalQueens(n,ans,new ArrayList<>());
        return ans.size();
    }

    public void totalQueens(int n, List<List<Integer>> ans, List<Integer> currentQueen){
        if(currentQueen.size() == n){
            ans.add(new ArrayList<>(currentQueen));
            return;
        }
        for(int i = 0; i<n; i++){
            if(!currentQueen.contains(i)){
                if(isOnDiagnose(i,currentQueen)){
                    continue;
                }
                currentQueen.add(i);
                totalQueens(n,ans,currentQueen);
                currentQueen.remove(currentQueen.size()-1);
            }
        }
    }

    public boolean isOnDiagnose(int col, List<Integer> currentQueen){
        for(int i = 0; i< currentQueen.size(); i++){
            if(Math.abs(i-currentQueen.size()) == Math.abs(col-currentQueen.get(i))) return true;
        }
        return false;
    }
}
