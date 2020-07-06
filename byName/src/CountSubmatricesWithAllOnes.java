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
                /**这里感觉跟你的姐妹题很像，都是用dp来存到i，j这个点长方形最大的边长以及有多少个，所以一旦断掉了（有个0）那就不是连续的，那么0那个位置就是0*/
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;   // horizontal height of histogram;
                for (int k = j, min = height[j]; k >= 0 && min > 0; k--) {
                    min = Math.min(min, height[k]);/**精髓，跟姐妹题超像，只能以最短的边为标准，不然就爆开来了，其实我也不是彻底的懂，没事多看看姐妹题吧*/
                    res += min;//凡是这种求总数量的应该都要有这一步吧。。。。。。。
                }
            }
        }
        return res;
    }
}
