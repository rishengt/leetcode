/**
 * Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eleetminicoworoep"
 * Output: 13
 * Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
 * Example 2:
 *
 * Input: s = "leetcodeisgreat"
 * Output: 5
 * Explanation: The longest substring is "leetc" which contains two e's.
 * Example 3:
 *
 * Input: s = "bcbcbc"
 * Output: 6
 * Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 x 10^5
 * s contains only lowercase English letters.
 */
import java.util.*;
public class FindtheLongestSubstringContainingVowelsinEvenCounts{
    public static void main(String[] args) {
        System.out.println(new FindtheLongestSubstringContainingVowelsinEvenCounts().findTheLongestSubstring("eleetminicoworoep"));
        System.out.println(new FindtheLongestSubstringContainingVowelsinEvenCounts().findTheLongestSubstring("leetcodeisgreat"));
        System.out.println(new FindtheLongestSubstringContainingVowelsinEvenCounts().findTheLongestSubstring("bcbcbc"));
    }
    /**这道题的思路也是用到prefix sum的思路
     * 假设s[i:j]之间是最长的符合要求的substring，那么肯定要满足s[0:i]之间原音字母出现的次数的奇偶跟s[0:j]直接的奇偶是一样的，因为奇-奇=偶，偶-偶=偶；
     * 但是因为我们要求的不止一个元音，所以我们用一个5位的数组来记录它们出现的次数，并把这个数组变换成一个二位整数来表示每个元音的奇偶值，01010 0代表出现偶数次，1代表出现奇数次
     * 只要prefix[0:i]跟prefix[0:j]是相等的，那么代表s[i:j]元音出现的次数是偶数。我们可以通过一个hashmap把prefix一一记录下来。。
     */
    public int findTheLongestSubstring(String s) {
        int[] count = new int[5];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int ans = 0;
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == 'a'){
                count[0]++;
            }else if(c=='e'){
                count[1]++;
            }else if(c=='i'){
                count[2]++;
            }else if(c=='o'){
                count[3]++;
            }else if(c=='u'){
                count[4]++;
            }
            int key = countToInt(count);
            if(map.containsKey(key)){
                ans = Math.max(i-map.get(key),ans);
//                map.put(key,i);这里不能更新边界，因为我们要找的就是最宽的，保留的就是第一个，不像non-overlapping那些傻逼，天天更新边界，操！
            }else{
                map.put(key,i);
            }
        }
        return ans;
    }

    /**这个转换太神奇了，要画图细品的。。。。*///a  e  i  o u
    /**这个方法的主要目的就是为了把一个5位的数组【0，0，0，0，0】用一个整数来代替*/
    public int countToInt(int[] count){
        int key = 0;/**这东西是关键*/
        for(int i = 0; i<count.length; i++){/**应该也是另类的bit mask了*/
            if(count[i]%2 != 0){
                key+=(1<<i);/**这里也是关键，但是这些关键点在不看答案的情况下怎么自己想出来啊？？？*/
                /**每当数组当中出现奇数的时候，就要干起了,比如u出现了奇数次 -> [0,0,0,0,5],
                 * 那么通过这个位移会变成  00001向右位移4次 得到            1，0，0，0，0；
                 */
            }
        }
        return key;
    }
}
