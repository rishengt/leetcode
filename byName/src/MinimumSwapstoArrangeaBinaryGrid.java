import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
 *
 * A grid is said to be valid if all the cells above the main diagonal are zeros.
 *
 * Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
 *
 * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
 */
public class MinimumSwapstoArrangeaBinaryGrid {
    public static void main(String[] args) {
        MinimumSwapstoArrangeaBinaryGrid m = new MinimumSwapstoArrangeaBinaryGrid();
        System.out.println(m.minSwaps(new int[][]{{0,1,1,0},{0,1,1,0},{0,1,1,0},{0,1,1,0}}));
    }
    public int minSwaps(int[][] grid) {
        int n = grid.length, res=0;
        List<Integer> row = new LinkedList<>();
        for (int i=0; i<n; i++){
            int trailingZeroCnt=0;
            for (int j=n-1; j>-1 && grid[i][j]==0; j--) {
                trailingZeroCnt++; }/** 我艹，forloop还能这么写。。&&条件不符合直接跳出，我艹！！！*/
            row.add(trailingZeroCnt);
        }
        for (int curRowIdx=0, minTrailingZeros=n-1; curRowIdx<n; curRowIdx++, minTrailingZeros--){
            int satisfiedRowIdx =curRowIdx;
            /**注意了啊，你把while里面两个判断条件一对换，程序就会出错，因为sarisfiedRowIdx会变成n， 而你list.get(n)会爆掉，细品*/
            while (satisfiedRowIdx <n && row.get(satisfiedRowIdx)<minTrailingZeros) satisfiedRowIdx++;
            if (satisfiedRowIdx ==n) return -1;
            int toRemove = row.remove(satisfiedRowIdx);
            row.add(curRowIdx, toRemove);/**我艹，list还有这个功能？？*/
            res+=satisfiedRowIdx -curRowIdx;
        }
        return res;
    }
}
