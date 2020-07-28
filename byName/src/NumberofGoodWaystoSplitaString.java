import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation
 * is equal to s and the number of distinct letters in p and q are the same.
 *
 * Return the number of good splits you can make in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aacaba"
 * Output: 2
 * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: 1
 * Explanation: Split the string as follows ("ab", "cd").
 * Example 3:
 *
 * Input: s = "aaaaa"
 * Output: 4
 * Explanation: All possible splits are good.
 * Example 4:
 *
 * Input: s = "acbadbaada"
 * Output: 2
 *
 *
 * Constraints:
 *
 * s contains only lowercase English letters.
 * 1 <= s.length <= 10^5
 */
public class NumberofGoodWaystoSplitaString {
    public static void main(String[] args) {
        System.out.println(new NumberofGoodWaystoSplitaString().numSplits("aacaba"));
        System.out.println(new NumberofGoodWaystoSplitaString().numSplits("abcd"));
        System.out.println(new NumberofGoodWaystoSplitaString().numSplits("aaaaa"));
        System.out.println(new NumberofGoodWaystoSplitaString().numSplits("acbadbaada"));
    }
    /**
     * 1, use a arrays or map, rc and lc, to cache counts for each unique charater on right and left subarrays.
     * 2, use l and r to count unique chars on right and left subarrays.
     * 3, init rc with first loop, then update lc and rcwith second loop.
     * 4, in the second loop, if (lc == rc) res++;
     */
    public int numSplits(String s) {
        int m = s.length(), rc[] = new int[26], lc[] = new int[26], l = 0, r = 0, res = 0;
        for (char c : s.toCharArray()) if (rc[c - 'a']++ == 0) r++;/**先把所有的unique的字母个数记录下来*/
        for (char c : s.toCharArray()) {/**从头开始遍历*/
            if (lc[c - 'a']++ == 0) l++;/**左边第一次出现的记录*/
            if (--rc[c - 'a'] == 0) r--;/**为什么这些++， --写在括号里面，这不是为了装杯，而是这样写你不进if那个动作也会发生*/
            if (l == r) res++;
        }
        return res;
    }

    public int numSplitsII(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap();
        for(int i = 0; i < n; i++)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

        int goodWays = 0;
        Map<Character, Integer> mapLeft = new HashMap();
        for(int i = 1; i < n; i++) {
            char c = s.charAt(i - 1);

            mapLeft.put(c, mapLeft.getOrDefault(c, 0) + 1);

            if(map.containsKey(c) && map.get(c) > 1) map.put(c, map.get(c) - 1);
            else map.remove(c);

            if(mapLeft.size() == map.size()) goodWays++;
        }

        return goodWays;
    }
}
