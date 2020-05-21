import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B.
 * The integer B denotes that from any place (suppose the index is i) in the array A,
 * you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to.
 * Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.
 *
 * Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins.
 * You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.
 *
 * If there are multiple paths with the same cost, return the lexicographically smallest such path.
 *
 * If it's not possible to reach the place indexed N then you need to return an empty array.
 */

public class CoinPath {
    /**记九章的吧，不过面试要是考这道题当场死就完事了。。。。。。。。。*/
    public static void main(String[] args) {
        System.out.println(new CoinPath().CheapestJump(new int[]{1,2,4,-1,2}, 2));
        System.out.println(new CoinPath().CheapestJump(new int[]{1,2,4,-1,2}, 1));
        System.out.println(new CoinPath().CheapestJump(new int[]{1,4,2,2,0}, 2));
        System.out.println(new CoinPath().cheapestJump(new int[]{1,2,4,-1,2}, 2));
        System.out.println(new CoinPath().cheapestJump(new int[]{1,2,4,-1,2}, 1));
        System.out.println(new CoinPath().cheapestJump(new int[]{1,4,2,2,0}, 2));
    }

    /**反正这道题记住一点， 如果有两条路线都能花同样的最少价钱去到目的地，那么路程较长的就是 lexicograohically smaller*/
    /**
     * 数组cost[i] 表示以第i枚硬币结尾时的最小开销
     * 数组path[i]表示以第i枚硬币时的最佳方案
     * 若  cost[i] > cost[j] + A[i] 或者 cost[i] == cost[j] + A[i] && path[i] > path[j] + [i]
     * 则令cost[i] = cost[j] + A[i], path[i] = path[j] + [i]
     */
    public List<Integer> CheapestJump(int[] A, int B){
        int n = A.length;
        int[] c = new int[n]; //cost
        int[] p = new int[n]; //previous index
        int[] l = new int[n]; //length
        Arrays.fill(c, Integer.MAX_VALUE);
        Arrays.fill(p, -1);
        c[0] = 0;
        for(int i = 0; i<n; i++){
            if(A[i] == -1) continue;/**A[i] 如果等于 -1， 代表你不能跳到这个点，所以continue*/
            for(int j = Math.max(0,i-B); j<i; j++){/**math.max 看不懂*/
                if(A[j] == -1) continue;
                int alt = c[j] +A[i];/**跳到index i时的价钱*/
                if(alt< c[i] || alt == c[i] && l[i] < l[j] + 1){/**逻辑看上面*/
                    c[i] = alt;
                    p[i] = j;
                    l[i] = l[j] +1;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        for(int cur = n-1; cur >= 0; cur = p[cur]) path.add(0,cur+1);
        return path.get(0) !=1 ? Collections.emptyList() : path;
    }

    /*********************************这是九章里找到的答案********************************************/
    public List<Integer> cheapestJump(int[] A, int B) {
        // write your code here
        int n = A.length;
        int[] dp = new int[n];
        int[] nxt = new int[n];
        List<Integer> path = new ArrayList<>();
        for(int i = 0; i < n; ++i){
            dp[i] = nxt[i] = -1;
        }
        dp[n - 1] = A[n - 1];
        for(int i = n - 2; i >= 0; --i) {
            if (A[i] != -1) {//逆序枚举i
                for (int j = i + 1; j <= i + B && j < n; ++j) {
                    if (dp[j] != -1) {  //枚举i位置后的B范围内的j位置
                        if (dp[i] == -1 || dp[j] + A[i] < dp[i]) {/**递归公式还是差不多的*/
                            dp[i] = dp[j] + A[i];
                            nxt[i] = j;                                //将j作为i的下一个节点
                        }
                    }
                }
            }
        }
        if(dp[0] == -1)return path;
        int now = 0;
        while(now != -1){									//输出路径
            path.add(now + 1);
            now = nxt[now];
        }
        return path;
    }
}
