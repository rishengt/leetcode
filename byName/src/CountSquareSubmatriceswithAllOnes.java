/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 *
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 */
public class CountSquareSubmatriceswithAllOnes {
    public static void main(String[] args) {
        System.out.println(new CountSquareSubmatriceswithAllOnes().countSquares(new int[][]{{1,0,1},{1,1,0},{1,1,0}}));
    }
    /**
     * dp[i][j] means the size of biggest square with matrix[i][j] as bottom-right corner.
     * dp[i][j] also means the number of squares with matrix[i][j] as bottom-right corner.
     *
     * If matrix[i][j] == 0, no possible square.
     * If matrix[i][j] == 1,
     * we compare the size of square dp[i-1][j-1], dp[i-1][j] and dp[i][j-1].
     * min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1 is the maximum size of square that we can find.
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int res = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] > 0 && i > 0 && j > 0) {
                    matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
                }
                res += matrix[i][j];
            }
        }
        return res;
    }

    public int countSquaresII(int[][] matrix) {
        int mat[][] = new int[matrix.length + 1][matrix[0].length + 1];
        int sum = 0;

        /**  We can do Dynamic Programming by saving how many
            Squares can be formed using the bottom right corner
            element.
        */

        for(int i = 1; i <= matrix.length; i++)
            for(int j = 1; j <= matrix[0].length; j++)
                if(matrix[i - 1][j - 1] != 0)
                    sum += (mat[i][j] = Math.min(Math.min(mat[i - 1][j], mat[i][j - 1]), mat[i - 1][j - 1]) + 1);


        /**
        Workin on the first example:
        ===========================
        Matrix =
        [0,1,1,1],
        [1,1,1,1],
        [0,1,1,1]
        ===========================
        mat after algorithm =
        [0,0,0,0,0],
        [0,0,1,1,1],
        [0,1,1,2,2],
        [0,0,1,2,3]
        ===========================
        After summing all indicies, now we get the correct answer!
        */

        return sum;
    }
}
