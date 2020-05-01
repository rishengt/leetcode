/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class PalindromeValidII {
    public static void main(String[] args) {
        System.out.println(new PalindromeValidII().validPalindrome("aba"));
        System.out.println(new PalindromeValidII().validPalindrome("abca"));
        System.out.println(new PalindromeValidII().validPalindrome("acbda"));
    }
    public boolean validPalindrome(String s){
        if(s.length()<2||s==null) return true;
        int start = 0;
        int end = s.length()-1;
        while(start<end){
            if(s.charAt(start)!=s.charAt(end)){
                return isValid(s,start+1,end) || isValid(s,start,end-1);
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean isValid(String s, int start, int end){
        while(start<end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
