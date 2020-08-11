import java.util.HashMap;

/**
 * Given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it palindrome.
 *
 * Return the length of the maximum length awesome substring of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3242415"
 * Output: 5
 * Explanation: "24241" is the longest awesome substring, we can form the palindrome "24142" with some swaps.
 * Example 2:
 *
 * Input: s = "12345678"
 * Output: 1
 * Example 3:
 *
 * Input: s = "213123"
 * Output: 6
 * Explanation: "213123" is the longest awesome substring, we can form the palindrome "231132" with some swaps.
 * Example 4:
 *
 * Input: s = "00"
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists only of digits.
 */
public class FindLongestAwesomeSubstring {
    public static void main(String[] args) {
        System.out.println(new FindLongestAwesomeSubstring().longestAwesome("3242415"));
//        System.out.println(new FindLongestAwesomeSubstring().longestAwesome("12345678"));
//        System.out.println(new FindLongestAwesomeSubstring().longestAwesome("213123"));
//        System.out.println(new FindLongestAwesomeSubstring().longestAwesome("00"));
    }
    boolean oneOdd = true;
    public int longestAwesome(String s) {
        int[] count = new int[10];
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            count[c-'0']++;
            int key = countToKey(count);
            if(map.containsKey(key) && oneOdd){
                ans = Math.max(ans, i- map.get(key));
            }else if(oneOdd&&!map.containsKey(key)){
                map.put(key,i);
            }
        }
        return ans;
    }

    public int countToKey(int[] count){
        this.oneOdd = true;
        int odd = 0;
        int key = 0;
        for(int i = 0; i<count.length; i++){
            if(count[i] %2 !=0){
                odd++;
                if(odd>1)this.oneOdd = false;
                key += 1<<i;
            }
        }
        return key;
    }
}
