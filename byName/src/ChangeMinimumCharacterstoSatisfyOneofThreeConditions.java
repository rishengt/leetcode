/**
 * You are given two strings a and b that consist of lowercase letters. In one operation, you can change any character in a or b to any lowercase letter.
 *
 * Your goal is to satisfy one of the following three conditions:
 *
 * Every letter in a is strictly less than every letter in b in the alphabet.
 * Every letter in b is strictly less than every letter in a in the alphabet.
 * Both a and b consist of only one distinct letter.
 * Return the minimum number of operations needed to achieve your goal.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "aba", b = "caa"
 * Output: 2
 * Explanation: Consider the best way to make each condition true:
 * 1) Change b to "ccc" in 2 operations, then every letter in a is less than every letter in b.
 * 2) Change a to "bbb" and b to "aaa" in 3 operations, then every letter in b is less than every letter in a.
 * 3) Change a to "aaa" and b to "aaa" in 2 operations, then a and b consist of one distinct letter.
 * The best way was done in 2 operations (either condition 1 or condition 3).
 * Example 2:
 *
 * Input: a = "dabadd", b = "cda"
 * Output: 3
 * Explanation: The best way is to make condition 1 true by changing b to "eee".
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 105
 * a and b consist only of lowercase letters.
 */
public class ChangeMinimumCharacterstoSatisfyOneofThreeConditions {
    public static void main(String[] args) {
        System.out.println(new ChangeMinimumCharacterstoSatisfyOneofThreeConditions().minCharacters("aab", "aac"));
    }
    public int minCharacters(String a, String b) {
        if(a.equals(b)) return 0;
        int[] k1 = new int[26];
        int[] k2 = new int[26];
        for(int i = 0; i<a.length(); i++){
            k1[a.charAt(i)-'a']++;
        }
        for(int i = 0; i<b.length(); i++){
            k2[b.charAt(i)-'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<k1.length; i++){
            ans = Math.min((a.length()+b.length()-(k1[i]+k2[i])),ans);
        }
        return Math.min(canWork(k1,k2),Math.min(canWork(k2,k1),ans));
    }

    public int canWork(int[] k1, int[] k2){/**make every letter in k1 < k2 所需要的步數*/
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i<k1.length; i++){
            int cnt = 0;
            for(int j = i; j<k1.length; j++) cnt+=k1[j];
            for(int j = 0; j<i; j++) cnt+=k2[j];
            ans = Math.min(ans,cnt);
        }
        return ans;
    }
}
