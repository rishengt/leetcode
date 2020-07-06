import java.util.Arrays;

/**
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,0,1],
 *               [1,1,0],
 *               [1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 * Example 2:
 *
 * Input: mat = [[0,1,1,0],
 *               [0,1,1,1],
 *               [1,1,1,0]]
 * Output: 24
 * Explanation:
 * There are 8 rectangles of side 1x1.
 * There are 5 rectangles of side 1x2.
 * There are 2 rectangles of side 1x3.
 * There are 4 rectangles of side 2x1.
 * There are 2 rectangles of side 2x2.
 * There are 2 rectangles of side 3x1.
 * There is 1 rectangle of side 3x2.
 * Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
 * Example 3:
 *
 * Input: mat = [[1,1,1,1,1,1]]
 * Output: 21
 * Example 4:
 *
 * Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
 * Output: 5
 *
 */






public class CountSubmatricesWithAllOnes {
    public static void main(String[] args) {
        System.out.println(new CountSubmatricesWithAllOnes().numSubmatDP(new int[][]{{1,1},{1,1}}));
        System.out.println(new CountSubmatricesWithAllOnes().numSubmatDP(new int[][]{{1,1},{1,0}}));
        System.out.println(new CountSubmatricesWithAllOnes().numSubmatDP(new int[][]{{1,0,1},{0,1,0},{1,0,1}}));
        System.out.println(new CountSubmatricesWithAllOnes().numSubmatDP(new int[][]{{1,1,1,1,1}}));
    }

    /**Now, Let's solve 2D metrics by finding all 1 submetrics from row "up" to row "down". And apply above 1D helper function.
     Note: the array h[k] == 1 means all values in column k from row "up" to "down" are 1 (that's why we use &).
     So overall, the idea is to "compress" the 2D array to the 1D array, and apply 1D array method on it, while trying all heights up to down.*/

    public int numSubmat(int[][] mat) {

        int M = mat.length, N = mat[0].length;

        int res = 0;
        for (int up = 0; up < M; ++up) {
            int[] h = new int[N];
            Arrays.fill(h, 1);
            for (int down = up; down < M; ++down) {
                for (int k = 0; k < N; ++k) h[k] &= mat[down][k];
                res += countOneRow(h);
            }
        }

        return res;
    }

    private int countOneRow(int[] A) { /**细品，给你一条一维matrix，让你求带1的submatrix，怎么求*/

        int res = 0, length = 0;
        for (int i = 0; i < A.length; ++i) {
            length = (A[i] == 0 ? 0 : length + 1);
            res += length;
        }
        return res;
    }


    /*********************************************** dp 解 *****************************************************************/

    /**
     * loop each node, where count all the rectangular that use this node as bottom-right corner:
     * treat all 1s in the left or above as histogram,（直方图）；
     * counting towards left and just keep decreasing the histogram height;
     * keep adding the height to result;
     * height[i][j] means the current height of histogram with mat[i][j] as bottom;
     */
    public int numSubmatDP(int[][] mat) {
        int m = mat.length, n = mat[0].length, height[] = new int[n], res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;   // horizontal height of histogram;
                for (int k = j, min = height[j]; k >= 0 && min > 0; k--) {
                    min = Math.min(min, height[k]);
                    res += min;
                }
            }
        }
        return res;
    }
}
