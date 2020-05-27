/**
 * Given a non-empty string, encode the string such that its encoded length is the shortest.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 *
 * Note:
 * k will be a positive integer and encoded string will not be empty or have extra space.
 * You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
 * Example 1:
 *
 * Input: "aaa"
 * Output: "aaa"
 * Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 * Example 2:
 *
 * Input: "aaaaa"
 * Output: "5[a]"
 * Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 * Example 3:
 *
 * Input: "aaaaaaaaaa"
 * Output: "10[a]"
 * Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 * Example 4:
 *
 * Input: "aabcaabcd"
 * Output: "2[aabc]d"
 * Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
 * Example 5:
 *
 * Input: "abbbabbbcabbbabbbc"
 * Output: "2[2[abbb]c]"
 * Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
 *
 *
 *
 * 区间DP的问题，我们用dp[i][j]表示子串s[i:j]的最小压缩串，我们有如下递推方程：
 * dp[i][j] = min(dp[i][k] + dp[k + 1][j]), where 1 <= k < j, ‘+’ means concatenate here
 * dp[i][j] = min(dp[i][j], compress(s[i:j])), compress is a function to find if we can shorten input string by find a repeating pattern in itself
 * 这里两个递推公式，第一个递推适用于 abcabcd这种string，在k = 5的时候, dp(abcabcd) = dp(abcabc) + dp(d) = 2[abc] + d = 2[abc]d
 * 2. 但仅仅使用第一个递推是不够的，例如这个例子，abcabcabc, 用第一个递推公式可以得到, dp(abcabcabc) = dp(abcabc) + dp(abc) = 2[abc] + abc = 2[abc]abc，永远无法得到3[abc]
 * 所以由此我们需要第二个递推：
 * 这里compress的功能是，找到string自身的重复串来压缩string。比如对于abcabcabc，我们只进行区间dp是没有办法找到3[abc]的，所以我们必须对自身进行compress。
 * 这里就引出很重要的一个技巧：
 * 我们在字符串t后面再加上一个t，然后在这里面寻找子字符串t的第二个起始位置，如果第二个起始位置小于t的长度的话，说明t包含重复字符串，举个例子吧，比如 t = “abab”, 那么t+t = “abababab”，我们在里面找第二个t出现的位置为2，小于t的长度4，说明t中有重复出现，重复的个数为t.size()/pos = 2个
 * 3. 假设自身的字符串为sub, 在对自身进行压缩的过程中，如果自身是符合压缩条件的，压缩必须写成：
 * sub = sub.length() / index + “[” + dp[i][i + index – 1] + “]”;
 * 举个例子，比如
 * sub = “abcdbcd abcdbcd”，不能写成2[abcdbcd], 而是写成2[a2[bcd]]
 * 总结：
 * 1. 区间DP的辨别和运用，dp[i][j] = max/min(dp[i][k] + dp[k + 1][j])
 * 2. 像s=abcabc这样的字符串，在O(n)时间内进行压缩的方法，就是在s + s中寻找s的第二个起始位置，如果第二个起始位置小于s的长度的话，说明s包含重复字符串。
 *
 * 这道题还是应该用动态规划来做。我们建立一个二维的DP数组，其中dp[i][j]表示s在[i, j]范围内的字符串的缩写形式(如果缩写形式长度大于子字符串，那么还是保留子字符串)，
 * 那么如果s字符串的长度是n，最终我们需要的结果就保存在dp[0][n-1]中，然后我们需要遍历s的所有子字符串，对于任意一段子字符串[i, j]，
 * 我们我们以中间任意位置k来拆分成两段，比较dp[i][k]加上dp[k+1][j]的总长度和dp[i][j]的长度，将长度较小的字符串赋给dp[i][j]，
 * 然后我们要做的就是在s中取出[i, j]范围内的子字符串t进行合并。合并的方法是我们在取出的字符串t后面再加上一个t，然后在这里面寻找子字符串t的第二个起始位置，
 * 如果第二个起始位置小于t的长度的话，说明t包含重复字符串，举个例子吧，比如 t = "abab", 那么t+t = "abababab"，我们在里面找第二个t出现的位置为2，
 * 小于t的长度4，说明t中有重复出现，重复的个数为t.size()/pos = 2个，那么我们就要把重复的地方放入中括号中，注意中括号里不能直接放这个子字符串，
 * 而是应该从dp中取出对应位置的字符串，因为重复的部分有可能已经写成缩写形式了，比如题目中的例子5。如果t = "abc"，那么t+t = "abcabc"，我们在里面找第二个t出现的位置为3，
 * 等于t的长度3，说明t中没有重复出现，那么replace就还是t。然后我们比较我们得到的replace和dp[i][j]中的字符串长度，把长度较小的赋给dp[i][j]即可，时间复杂度为O(n3)，
 * 空间复杂度为O(n2)。
 */

public class EncodeStringWithShortestLength {
    public static void main(String[] args) {
        System.out.println(new EncodeStringWithShortestLength().encode("abbbabbbcabbbabbbc"));
        System.out.println(new EncodeStringWithShortestLength().encodeII("abbbabbbcabbbabbbc"));
    }

/********************************************感觉这个方法比较好记，但妈的遇到这题跪就是了，算你倒霉*******************************************************/
public String encode(String s) {
    int n = s.length();
    String[][] dp = new String[n][n];
    for(int i = 0; i < n; i++) dp[i][i] = "" + s.charAt(i);
    // j - i
    for(int len = 1; len < n; len++) {
        for(int i = 0; i + len < n; i++) {
            int j = i + len;
            // enumerate seperate k
            for(int k = i; k < j; k++) {
                int left = dp[i][k].length();
                int right = dp[k+1][j].length();
                // update shortest encoded string within (i, j)
                if(dp[i][j] == null || left + right < dp[i][j].length()) {
                    dp[i][j] = dp[i][k] + dp[k+1][j];
                }
            }
            // update string within (i, j), encode abcabc
            String sub = s.substring(i, j + 1);
            int index = (sub + sub).indexOf(sub, 1);
            if(index < sub.length()) {
                sub = (sub.length() / index) + "[" + dp[i][i+index-1] + "]";
            }
            if(dp[i][j] == null || dp[i][j].length() > sub.length()) {
                dp[i][j] = sub;
            }
        }
    }

    return dp[0][n-1];
}

/**************************************************************************************************************************************************/
    public String encodeII(String s){
        String[][] dp = new String[s.length()][s.length()];
        for(int l=0;l<s.length();l++) {
            for(int i=0;i<s.length()-l;i++) {
                int j = i+l;
                String substr = s.substring(i, j+1);
                // Checking if string length < 5. In that case, we know that encoding will not help.看第一个例子
                if(j - i < 4) {
                    dp[i][j] = substr;
                } else {
                    dp[i][j] = substr;
                    // Loop for trying all results that we get after dividing the strings into 2 and combine the results of 2 substrings
                    for(int k = i; k<j;k++) {
                        if((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()){
                            dp[i][j] = dp[i][k] + dp[k+1][j];
                        }
                    }
                    // Loop for checking if string can itself found some pattern in it which could be repeated.
                    for(int k=0;k<substr.length();k++) {
                        String repeatStr = substr.substring(0, k+1);
                        if(repeatStr != null
                                && substr.length()%repeatStr.length() == 0
                                && substr.replaceAll(repeatStr, "").length() == 0) {
                            String ss = substr.length()/repeatStr.length() + "[" + dp[i][i+k] + "]";
                            if(ss.length() < dp[i][j].length()) {
                                dp[i][j] = ss;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
