import java.util.HashMap;

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters.
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * It's guaranteed the answer fits on a 32-bit signed integer.
 *
 * Example 1:
 *
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * Example 2:
 *
 * Input: S = "babgbag", T = "bag"
 * Output: 5
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 */
public class DistinctSubsequences {
    public static void main(String[] args) {
        System.out.println(new DistinctSubsequences().numDistinct("rabbbit", "rabbit"));
    }

    /**
     * 整个代码的进化思路是这样子的
     **/
    public int numDistinctRecursive(String s, String t) {
        return numDistinctHelper(s, 0, t, 0);
    }

    private int numDistinctHelper(String s, int s_start, String t, int t_start) {
        //T 是空串，选法就是 1 种
        if (t_start == t.length()) {
            return 1;
        }
        //S 是空串，选法是 0 种
        if (s_start == s.length()) {
            return 0;
        }
        int count = 0;
        //当前字母相等
        if (s.charAt(s_start) == t.charAt(t_start)) {
            //从 S 选择当前的字母，此时 S 跳过这个字母, T 也跳过一个字母。
            count = numDistinctHelper(s, s_start + 1, t, t_start + 1)
                    //S 不选当前的字母，此时 S 跳过这个字母，T 不跳过字母。
                    + numDistinctHelper(s, s_start + 1, t, t_start);
            //当前字母不相等
        } else {
            //S 只能不选当前的字母，此时 S 跳过这个字母， T 不跳过字母。
            count = numDistinctHelper(s, s_start + 1, t, t_start);
        }
        return count;
    }

    /************************************请像死妈一样记住用哈希表优化递归的方法*****************************************************/
    public int numDistinctIIII(String s, String t) {
        HashMap<String, Integer> map = new HashMap<>();
        return numDistinctHelper(s, 0, t, 0, map);
    }

    private int numDistinctHelper(String s, int s_start, String t, int t_start, HashMap<String, Integer> map) {
        //T 是空串，选法就是 1 种
        if (t_start == t.length()) {
            return 1;
        }
        //S 是空串，选法是 0 种
        if (s_start == s.length()) {
            return 0;
        }
        String key = s_start + "@" + t_start;
        //先判断之前有没有求过这个解
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int count = 0;
        //当前字母相等
        if (s.charAt(s_start) == t.charAt(t_start)) {
            //从 S 选择当前的字母，此时 S 跳过这个字母, T 也跳过一个字母。
            count = numDistinctHelper(s, s_start + 1, t, t_start + 1, map)
                    //S 不选当前的字母，此时 S 跳过这个字母，T 不跳过字母。
                    + numDistinctHelper(s, s_start + 1, t, t_start, map);
            //当前字母不相等
        } else {
            //S 只能不选当前的字母，此时 S 跳过这个字母， T 不跳过字母。
            count = numDistinctHelper(s, s_start + 1, t, t_start, map);
        }
        //将当前解放到 map 中
        map.put(key, count);
        return count;
    }


    /************************************************下面是dp解***************************************************************/
    public int numDistinct(String S, String T) {
        // array creation
        int[][] mem = new int[T.length() + 1][S.length() + 1];

        // filling the first row: with 1s
        for (int j = 0; j <= S.length(); j++) {
            mem[0][j] = 1;//当T为0（空String的时候），不管S是什么只有一种可能就是把S里的字母全部拿掉，所以初始化为1
        }

        // the first column is 0 by default in every other rows but the first, which we need.
        for (int i = 0; i < T.length(); i++) {
            for (int j = 0; j < S.length(); j++) {
                if (T.charAt(i) == S.charAt(j)) {
                    mem[i + 1][j + 1] = mem[i][j] + mem[i + 1][j];//当字母相等的时候有两种可能，选T【i】那就不加一。 二，不选【i】那就加一；
                } else {
                    mem[i + 1][j + 1] = mem[i + 1][j];//不同的时候没得选，只能跳过T【i】；
                }
            }
        }

        return mem[T.length()][S.length()];
    }

    /***************************************** dp究极体，降维！！！！！！*******************************************************/
    public int numDistinctDp(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();
        int[] dp = new int[s_len + 1];
        for (int i = 0; i <= s_len; i++) {
            dp[i] = 1;
        }
        //倒着进行，T 每次增加一个字母
        for (int t_i = t_len - 1; t_i >= 0; t_i--) {
            int pre = dp[s_len];
            dp[s_len] = 0;
            //倒着进行，S 每次增加一个字母
            for (int s_i = s_len - 1; s_i >= 0; s_i--) {
                int temp = dp[s_i];
                if (t.charAt(t_i) == s.charAt(s_i)) {
                    dp[s_i] = dp[s_i + 1] + pre;
                } else {
                    dp[s_i] = dp[s_i + 1];
                }
                pre = temp;
            }
        }
        return dp[0];
    }
}
