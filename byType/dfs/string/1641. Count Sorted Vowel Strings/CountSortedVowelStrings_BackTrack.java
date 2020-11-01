/**
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 * Example 2:
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 * Example 3:
 *
 * Input: n = 33
 * Output: 66045
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 */
public class CountSortedVowelStrings_BackTrack {
    /**
     * The backtracking solution for the given problem would be, to begin with, the first vowel character, continue using the same vowel until we build a string of length nn.
     * Post that, backtrack and use the next character.
     *
     * As we are picking up each vowel character in alphabetically sorted order (first a, then e, then i, and so on), we know that the resultant string is always lexicographically sorted.
     *
     * Example, For n = 3, pick the first vowel a, continue picking up the same vowel until we reach nn. The resultant string would be aaa.
     * Now that we have found our first combination, backtrack and pick up the next character e. Our next string would be aae. The process continues until all the vowel characters are explored.
     *
     * Algorithm
     *
     * As we start with the first vowel a then e and so on, we need a way to determine the current vowel in a recursive function. We use an integer variable vowel for that purpose,
     * where 1 denotes a, 2 denotes e, and so on.
     * We begin with nn positions and first vowel a(1) and use method countVowelStringUtil to recursively compute all the combinations.
     * On every recursion, we decrement n by 1 as we have used up that position and continue passing the same vowel. On the backtrack, we move on to the next vowel.
     * Base Case
     *
     * If n = 0n=0, we have reached the end of the string and found one combination. Hence, we return 1 and backtrack.
     */
    public int countVowelStrings(int n){
        return backtrack(n,1);
    }

    public int backtrack(int n, int vowel){
        if(n == 0){
            return 1;
        }
        int ans = 0;
        for(int i = vowel; i<=5; i++){
            ans += backtrack(n-1,i);
        }
        return ans;
    }
}
