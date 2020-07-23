import java.util.*;

/**
 * Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:
 *
 * The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
 * A substring that contains a certain character c must also contain all occurrences of c.
 * Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings,
 * return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.
 *
 * Notice that you can return the substrings in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "adefaddaccc"
 * Output: ["e","f","ccc"]
 * Explanation: The following are all the possible substrings that meet the conditions:
 * [
 *   "adefaddaccc"
 *   "adefadda",
 *   "ef",
 *   "e",
 *   "f",
 *   "ccc",
 * ]
 * If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda",
 * we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also,
 * that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings.
 * No other solution of the same number of substrings exist.
 * Example 2:
 *
 * Input: s = "abbaccd"
 * Output: ["d","bb","cc"]
 * Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 */
public class MaximumNumberofNonOverlappingSubstrings {
    public static void main(String[] args) {
        System.out.println(new MaximumNumberofNonOverlappingSubstrings().maxNumOfSubstrings("adefaddaccc"));
    }

    /**
     * First find all the possible substrings. There will be at most one for each letter in s.
     * If we start at the first occurence of each letter and keep expanding the range to cover all occurences, we'll find all the substrings in O(26 * n) time.
     *
     * Once we've found all the possible substrings, this is a standard problem:
     * interval scheduling maximization problem (ISMP) (https://en.wikipedia.org/wiki/Interval_scheduling)
     *
     * We can solve this in O(n) time by greedily taking the next non-overlapping substring with the left-most endpoint.
     */

    /**这解法秒啊！！！**/
    private static class Interval {
        public int b;
        public int e;

        public Interval(int b, int e) {
            this.b = b;
            this.e = e;
        }
    }

    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = s.charAt(i) - 'a';/**这是啥？这是用一条array存每个字母的ASCII数值，可以理解为就是存了字母*/
        }

        int[] fst = new int[26];/**字母也就只有26个*/
        int[] lst = new int[26];
        for (int i = 0; i < n; i++) {
            lst[vals[i]] = i; /**这是啥？这是用来存每个字母最后一次出现的index的*/
        }
        for (int i = n - 1; i >= 0; i--) {
            fst[vals[i]] = i;/**第一次出现的index*/
        }

        List<Interval> t = new ArrayList<Interval>();
        for (int i = 0; i < 26; i++) {
            if (fst[i] < n) {
                int b = fst[i];
                int e = lst[i];
                for (int j = b; j <= e; j++) {  /**这又是干嘛的？？*/
                    b = Math.min(b, fst[vals[j]]);/**细啊，你得遍历你截出来的string，如果发现某个字母在string之外还出现过就证明你截的string没有包含所有出现过的字母，invalid*/
                    e = Math.max(e, lst[vals[j]]);
                }
                if (b == fst[i]) {
                    t.add(new Interval(b, e));/**这里就是判断有没有找到string以外的字母了*/
                }
            }
        }

        Collections.sort(t, Comparator.comparing(i -> i.e));/**类似于meeting room II的东西了*/
        List<String> ans = new ArrayList<String>();
        int prev = -1;
        for (Interval i : t) {
            if (i.b > prev) {
                ans.add(s.substring(i.b, i.e + 1));
                prev = i.e;
            }
        }

        return ans;
    }

    /**
     * Oh, human, so many edge cases... Update: see below for FAQ.
     *
     * The greedy logic is quite simple though:
     *
     * There could be no more that 26 valid substrings; each potential valid substring starts from the first occurrence of a given character.
     * If we find a valid substring, and then another valid substring within the first substring - we can ignore the larger substring.
     * E.g. if we find "abccba", and then "bccb", and then "cc", we only care about "cc". This can be easily proven by a contradiction.
     * Algorithm
     *
     * First, collect left (l) and right(r) indices for every character.
     * Then, go through the string. If the current index i is the left index for the character s[i], it may be a valid substring.
     * We then use a sliding window pattern to find the length of the substring.
     * If we find a character that appears before i - we do not have a valid string starting at i.
     * If substring is valid, we remember it, as well as it's right edge.
     * This logic, repeated, will ensure that we find the valid substring with the smallest right edge.
     * If the valid substring starts after the previous right edge, there is no overlap. The previous substring should be included into the result.
     */
    int checkSubstr(String s, int i, int l[], int r[]) {
        int right = r[s.charAt(i) - 'a'];
        for (int j = i; j <= right; ++j) {
            if (l[s.charAt(j) - 'a'] < i)
                return -1;
            right = Math.max(right, r[s.charAt(j) - 'a']);
        }
        return right;
    }
    public List<String> maxNumOfSubstringsII(String s) {
        int l[] = new int[26], r[] = new int[26];
        Arrays.fill(l, s.length());
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < s.length(); ++i) {
            int ch = s.charAt(i) - 'a';
            l[ch] = Math.min(l[ch], i);
            r[ch] = i;
        }
        int right = -1;
        for (int i = 0; i < s.length(); ++i)
            if (i == l[s.charAt(i) - 'a']) {
                int new_right = checkSubstr(s, i, l, r);
                if (new_right != -1) {
                    if (i > right)
                        res.add("");
                    right = new_right;
                    res.set(res.size() - 1, s.substring(i, right + 1));
                }
            }
        return res;
    }
}
