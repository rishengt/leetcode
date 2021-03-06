/**
 * Given a string which consists of lowercase or uppercase letters,
 * find the length of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 *
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */

public class PalindromLongest {
    public static void main(String[] args) {
        System.out.println(new PalindromLongest().longestPalindrom("abccccdd"));
        System.out.println(new PalindromLongest().longestPalindrom("abccba"));
    }
    public int longestPalindrom(String s){
        int[] cnt = new int[128];
        int odd = 0;
        for(char c: s.toCharArray()) cnt[c-0]++;
        for(int i : cnt){
            if(i%2 != 0) odd++;
        }
        return odd == 0? s.length(): s.length()-odd+1 ;
    }
}
