package Math.排列组合.Combination;

/**
 *  求从a个数里面选b个数进行组合的方案数， note： a个不同的数
 */
public class CombinationI {
    public static void main(String[] args) {
        long[][] c = new CombinationI().getTriangle(4);
        System.out.println(c[4][4]);
    }

    private long[][] getTriangle(int n) {
        // Yang Hui (Pascle) triangle
        // 4C2 = triangle[4][2] = 6
        long[][] triangle = new long[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            triangle[i][0] = triangle[i][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                triangle[i][j] = (triangle[i - 1][j] + triangle[i - 1][j - 1]);
            }
        }
        return triangle;
    }
}
