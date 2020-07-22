import java.util.HashMap;
import java.util.Map;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there are n stones in a pile.  On each player's turn,
 * that player makes a move consisting of removing any non-zero square number of stones in the pile.
 *
 * Also, if a player cannot make a move, he/she loses the game.
 *
 * Given a positive integer n. Return True if and only if Alice wins the game otherwise return False, assuming both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 * Example 3:
 *
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 * Example 4:
 *
 * Input: n = 7
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 * If Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and finally Bob removes the last one (7 -> 3 -> 2 -> 1 -> 0).
 * If Alice starts removing 1 stone, Bob will remove 4 stones then Alice only can remove 1 stone and finally Bob removes the last one (7 -> 6 -> 2 -> 1 -> 0).
 * Example 5:
 *
 * Input: n = 17
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 */
public class StoneGameIV {

    public static void main(String[] args) {
        System.out.println(new StoneGameIV().winnerSquareGame(1));
        System.out.println(new StoneGameIV().winnerSquareGame(2));
        System.out.println(new StoneGameIV().winnerSquareGame(4));
        System.out.println(new StoneGameIV().winnerSquareGame(7));
    }

    /*********************************  遍历所有可能，只要有一条路是通向胜利的，那么我们就赢了 ************************************/
    /***********                                妥妥的      TLE                       ************************************/
    public boolean winnerSquareGame(int n){
        boolean winner = false;
        if(n == 0) return false;
        if(n == 1) return true;
        for(int i = 1; i*i<=n; i++){
            /**而用n-i*i是应为必须要拿走non-zero square number of stones，我们要啊尝试所有可能，只要有一条路是通向胜利的，那么我们就赢了 **/
            if(!winnerSquareGame(n-i*i)){/**这里写了非代表你alice拿完以后 bob能不能赢，要是bob能赢，那么alice就输，反之则alice赢*/
                winner = true;
                break;
            }
        }
        return winner;
    }

    /** so， 用 hashmap 优化 */
    Map<Integer, Boolean> map = new HashMap<>();
    public boolean winnerSquareGameII(int n) {
        if (n == 0) return false;
        if (map.containsKey(n)) return map.get(n);
        boolean res = false;
        for (int i = 1; i * i <= n; i++) {
            if (!winnerSquareGame(n - i * i)) {
                res = true;
                break;
            }
        }
        map.put(n, res);
        return res;
    }

    /**
     * dp[i] means the result for n = i.
     *
     * if there is any k that dp[i - k * k] == false,
     * it means we can make the other lose the game,
     * so we can win the game an dp[i] = true.
     * @param n
     * @return
     */
    public boolean winnerSquareGameIII(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int k = 1; k * k <= i; ++k) {/**理解不了的话请看上面的注解傻叼*/
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

}
