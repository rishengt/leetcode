import java.util.HashMap;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 *
 * Example 1:
 *
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * Example 2:
 *
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * Example 3:
 *
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * Example 4:
 *
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 */
public class WordPattern {
    public static void main(String[] args) {
        System.out.println(new WordPattern().rightPattern("abba", "dog cat cat dog"));
        System.out.println(new WordPattern().rightPattern("abba", "dog cat cat fish"));
    }
    public boolean rightPattern(String pattern, String str){
        HashMap<Character,Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        String[] k = str.split(" ");
        if(k.length!=pattern.length()) return false;
        for(Integer i = 0; i< pattern.length(); i++){ /**写了int 的话就用！Object.equals(xx , xx)*/
            if(map1.put(pattern.charAt(i),i)!=map2.put(k[i],i)) return false;
        }
        return true;
    }
}
