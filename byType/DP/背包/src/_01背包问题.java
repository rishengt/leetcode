/**
 * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
 *
 * 第 i 件物品的体积是 vi，价值是 wi。
 *
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 *
 * Example
 *  input:
 *      N: 4
 *      V: 5
 *      Volume: [1,2,3,4]
 *      Worth: [2,4,4,5]
 * Output:
 *      8
 */
public class _01背包问题 {
    public static void main(String[] args) {
        System.out.println(new _01背包问题().getMaxValue(4,5,new int[]{1,2,3,4},new int[]{2,4,4,5}));
        System.out.println(new _01背包问题().getMaxValueII(4,5,new int[]{1,2,3,4},new int[]{2,4,4,5}));
    }

    /**
     * 背包问题的基本模板了
     * dp[i][j] 表示选前ith件物品体积为j的时候的value
     * 初始化细节：
     *  要求恰好装满背包，那么在初始化时除了F[0] 为0，其它
     *  F[1::V ] 均设为􀀀1，这样就可以保证最终得到的F[V ] 是一种恰好装满背包的最优解。
     *  如果并没有要求必须把背包装满，而是只希望价格尽量大，初始化时应该将F[0::V ]
     *  全部设为0。
     * 递推公式自己想，妈的
     */

    /**
        定义一个二阶矩阵dp[N+1][V+1],
        这里之所以要N+1和V+1，是因为第0行表示只能选择第0个物品的时候，即没有物品的时候
        第0列表示背包的体积为0的时候，即不能装任何东西的时候

        dp[i][j]表示在 只能选择前i个物品，背包容量为j的情况下，背包中物品的最大价值
        对于dp[i][j]有两种情况：
        1. 不选择当前的第i件物品/第i件物品比背包容量要大，则dp[i][j] = dp[i-1][j]
        2. 选择当前的第i件物品（潜在要求第i件物品体积小于等于背包总容量），则能装入的物品最大价值为：
            当前物品的价值 加上 背包剩余容量在只能选前i-1件物品的情况下的最大价值
            dp[i][j] = dp[i-1][j-v[i]] + w[i]
        dp[i][j]在两种情况中选择比较大的情况作为当前的最优解；
        即：
        if(j >= v[i]):
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-v[i]] + w[i])
        else:
            dp[i][j] = dp[i-1][j]
        */
    public int getMaxValue(int N, int V, int[] volume, int[] worth){
        int dp[][] = new int[N+1][V+1];
        for(int i = 1; i<=N; i++){ /**这里由1开始就很鸡贼，不会爆界*/
            for(int j = 0; j<=V; j++){
                if(j>=volume[i-1]){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-volume[i-1]]+worth[i-1]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[N][V];
    }

    public int getMaxValueII(int N, int V, int[] volume, int[] worth){
        int dp[] = new int[V+1];
        for(int i = 0; i<N; i++){
            for(int j = V; j>=volume[i]; j--){/**这里是完全跟01唯一的区别了，完全是由当前volume开始向V遍历，01是反过来，由V向当前volume遍历。。*/
                if(j-volume[i]>=0)
                    dp[j] = Math.max(dp[j], dp[j-volume[i]]+worth[i]);
            }
        }
        return dp[V];
    }
}
