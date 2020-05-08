import java.util.HashMap;

/**
 * 3. Longest Substring Without Repeating Characters
 * Medium
 *
 * 8596
 *
 * 522
 *
 * Add to List
 *
 * Share
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacter {
    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacter().longestSubstringWithoutRepeatingChar("abba"));
    }
    public int longestSubstringWithoutRepeatingChar(String s){
        if(s.length() == 0) return 0;
        int ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0, j= 0; i<s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                /**j = map.get(s.charAt(i));
                 * 如果你这么写，当i走到第二个a的时候，j就变回1，答案显然就不对了，妈的做多少遍了前面这几道破题还他妈给老子写错，你就不适合写代码草菜鸡！
                 */
                j = Math.max(map.get(s.charAt(i)),j);/**j一定得是最后一个重复的傻逼东西！*/
            }
            ans = Math.max(ans, i-j+1);
            map.put(s.charAt(i), i+1);
        }
        return ans;
    }
}
