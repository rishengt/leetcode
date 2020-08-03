/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 */
public class PalindromeValid {
    public static void main(String[] args) {
        System.out.println(new PalindromeValid().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new PalindromeValid().isPalindrome("race a car"));
    }
    public boolean isPalindrome(String s){
        //s = s.toLowerCase().replaceAll("[^a-z0-9]", ""); 慢是慢但是Regex还是要记一记的
        for(int i = 0, j = s.length()-1; i<j; i++, j--){
            while(i<j&&!Character.isLetterOrDigit(s.charAt(i))/**(!Character.isAlphabetic(s.charAt(i))||!Character.isDigit(s.charAt(i)))这样写不行*/){
                i++;
            }
            while(i<j&&!Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }
            if(i<j&&Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))){
                return false;
            }
        }
        return true;
    }
}
