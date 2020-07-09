/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        //保存以当前数字结尾的连续 1 的个数
        int[][] width = new int[matrix.length][matrix[0].length];
        int maxArea = 0;
        //遍历每一行
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                //更新 width
                if (matrix[row][col] == '1') {
                    if (col == 0) {
                        width[row][col] = 1;
                    } else {
                        width[row][col] = width[row][col - 1] + 1;
                    }
                } else {
                    width[row][col] = 0;
                }
                //工具minWidth，先赋值，下面会记录当前列最小的数
                int minWidth = width[row][col];
                //向上扩展行
                for (int up_row = row; up_row >= 0; up_row--) {
                    int height = row - up_row + 1;
                    //找最小的数作为矩阵的宽
                    /**凡是在矩阵里跟长方形搞，取每一列（col）的min，跟正方形搞，取所有的min，以后要是忘了就细品那两道countsubmatrix*/
                    minWidth = Math.min(minWidth, width[up_row][col]);
                    //更新面积
                    maxArea = Math.max(maxArea, height * minWidth);
                }
            }
        }
        return maxArea;
    }
}
