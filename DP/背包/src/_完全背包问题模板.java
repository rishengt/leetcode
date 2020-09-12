/**
 * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
 *
 * 第 i 种物品的体积是 vi，价值是 wi。
 *
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 *
 * Example
 *  input:
 *      N:4
 *      V:5
 *      volume: [1,2,3,4]
 *      worth: [2,4,4,5]
 *  output:
 *      10
 */
public class _完全背包问题模板 {
    public static void main(String[] args) {
        System.out.println(new _完全背包问题模板().getMaxWorth(4,5,new int[]{1,2,3,4}, new int[]{2,4,4,5}));
    }
    public int getMaxWorth(int N, int V, int[] volume, int[] worth){
        int dp[] = new int[V+1];
        for(int i = 0; i<N; i++){
            for(int j = volume[i]; j<=V; j++){ /**这里是完全跟01唯一的区别了，完全是由当前volume开始向V遍历，01是反过来，由V向当前volume遍历。。*/
                dp[j] = Math.max(dp[j], dp[j-volume[i]]+worth[i]);
            }
        }
        return dp[V];
    }
}
