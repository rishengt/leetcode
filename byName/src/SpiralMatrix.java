import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        System.out.println(new SpiralMatrix().spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }
    /**这种写法妈的真的精简而优美*/
    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;/**余数一直悟不了，难啊*/
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }

    /**但是wang大神的强硬模拟还是非常亲民好理解且能够独立实现的****/
    public List<Integer> spiralOrderII(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if(matrix.length == 0){
            return ans;
        }
        int start_x = 0,
                start_y = 0,
                direction = 0,
                top_border = -1,  //上边界
                right_border = matrix[0].length,  //右边界
                bottom_border = matrix.length, //下边界
                left_border = -1; //左边界
        while(true){
            //全部遍历完结束
            if (ans.size() == matrix.length * matrix[0].length) {
                return ans;
            }
            //注意 y 方向写在前边，x 方向写在后边
            ans.add(matrix[start_y][start_x]);
            switch (direction) {
                //当前向右
                case 0:
                    //继续向右是否到达边界
                    //到达边界就改变方向，并且更新上边界
                    if (start_x + 1 == right_border) {
                        direction = 1;
                        start_y += 1;
                        top_border += 1;
                    } else {
                        start_x += 1;
                    }
                    break;
                //当前向下
                case 1:
                    //继续向下是否到达边界
                    //到达边界就改变方向，并且更新右边界
                    if (start_y + 1 == bottom_border) {
                        direction = 2;
                        start_x -= 1;
                        right_border -= 1;
                    } else {
                        start_y += 1;
                    }
                    break;
                case 2:
                    if (start_x - 1 == left_border) {
                        direction = 3;
                        start_y -= 1;
                        bottom_border -= 1;
                    } else {
                        start_x -= 1;
                    }
                    break;
                case 3:
                    if (start_y - 1 == top_border) {
                        direction = 0;
                        start_x += 1;
                        left_border += 1;
                    } else {
                        start_y -= 1;
                    }
                    break;
            }
        }

    }

}
