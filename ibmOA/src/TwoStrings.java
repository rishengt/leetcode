import java.util.Arrays;
import java.util.List;

/**
 * Given two arrays of strings, determine whether corresponding elemnets contain a common substring.
 * For example, if array a = [ab,cd,ef] and array b = [af, ee, ef], we make the following decisions:
 *   i a[i] b[i]   Common  Result
 *   0 ab   af       a       YES
 *   1 cd   ee               NO
 *   2 ef   ef       ef      YES
 *
 * For each test, print your result on a new line, either YES if there is a common substring, or NO,
 *
 * Function Description
 * Complete the function commonSubstring in the editor below. For each a[i], b[i] pair, the function must print YES if they share a common substring,
 * or NO on a new line.
 *
 * commonSubstring has the following parameter(s):
 * a[a[0], ... a[n-1]]: an array of strings
 * b[b[0], ... b[n-1]]: an array of strings
 *
 * Constraints:
 *   All the strings consist of lowercase English letters only
 *   |a| = |b|
 */
public class TwoStrings {
    public static void main(String[] args) {
//        new TwoStrings().commonSubstring(new String[]{"hello","hi"}, new String[]{"world","bye"});
//        new TwoStrings().commonSubstring(new String[]{"ab","cd","ef"}, new String[]{"af","ee","ef"});
        new TwoStrings().commonSubstring(Arrays.asList("ab","cd","ee"), Arrays.asList("af","ee","ef"));
    }
    public void commonSubstring(String[] a, String[] b){
        for(int i = 0; i< a.length; i++){
            String one = a[i];
            String two = b[i];
            if(hasCommon(one,two)) System.out.println("YES");
            else{
                System.out.println("NO");
            }
        }
    }

    public boolean hasCommon(String a, String b){
        int[] cnta = new int[26];
        int[] cntb = new int[26];
        for(int i = 0; i< a.length(); i++){
            cnta[a.charAt(i)-'a'] ++;
        }
        for(int i = 0; i< a.length(); i++){
            cntb[b.charAt(i)-'a'] ++;
        }
        for(int i = 0; i< 26; i++){
            if(cnta[i] !=0 && cntb[i] !=0) return true;
        }
        return false;
    }

    /******************************** LC 上的答案***********************************************************************/
    public static void commonSubstring(List < String > a, List < String > b) {
        // Write your code here
        boolean[] ch = new boolean[26];
        int flag = 0;
        for (int i = 0; i < a.size(); i++) {
            flag = 0;
            ch = new boolean[26];
            for (int j = 0; j < a.get(i).length(); j++) {
                ch[a.get(i).charAt(j) - 'a'] = true;
            }

            for (int j = 0; j < b.get(i).length(); j++) {
                if (ch[b.get(i).charAt(j) - 'a']) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 1)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
