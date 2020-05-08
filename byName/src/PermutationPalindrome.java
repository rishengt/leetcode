import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 *
 * Example 1:
 *
 * Input: "code"
 * Output: false
 * Example 2:
 *
 * Input: "aab"
 * Output: true
 * Example 3:
 *
 * Input: "carerac"
 * Output: true
 */
public class PermutationPalindrome {
    public static void main(String[] args) {
        System.out.println(new PermutationPalindrome().canBePalindrome("code"));
        System.out.println(new PermutationPalindrome().canBePalindrome("aab"));
        System.out.println(new PermutationPalindrome().canBePalindrome("carerac"));
    }

    public boolean canBePalindrome(String s){
        int[] kao = new int[128];
        for(char c: s.toCharArray()){
            kao[c]++;
        }
        int count = 0;
        for(int ans: kao){
            if(ans%2 != 0){
                count++;/**用来记录字母出现的单次数*/
            }
        }
        return count<=1;/**要是字母单次出现超过一次就不可能重新组成回文， 细品*/
    }
}
