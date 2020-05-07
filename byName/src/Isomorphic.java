import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 */

public class Isomorphic {
    public static void main(String[] args) {
        System.out.println(new Isomorphic().isIsomorphic("egg", "add"));
        System.out.println(new Isomorphic().isIsomorphic("foo", "bar"));
        System.out.println(new Isomorphic().isIsomorphic("paper", "title"));
        //System.out.println(null.equals(null));
    }

    /**
     * == operator does the object comparison and since java caches integers from -128 to 127 hence ,
     * the above Integer == Integer is working for string's size less than 127.
     * When we use for (int i = 0; i < s1.length(); i++), if i>=128, we put two different Integers to map,
     * which means m1.put(s1.charAt(i), i) != m2.put(s2.charAt(i), i) and will return false.
     * If you want uses i, you should use equals. And dont't forget nullpointer because i1 might be null. Hope my explanation will help you!
     */
    public boolean isIsomorphic(String s1, String s2){
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(Integer i = 0; i<s1.length();i++){
            if(map1.put(s1.charAt(i),i)!=(map2.put(s2.charAt(i),i))) return false;
            /**菜鸡如我在这之前根本不知道put还返回被同一个key新value顶替的旧value。。。没有value时返回null。。。。妈的看一眼官方借口文档会死吗。。ctrl加鼠标*/
            /**而java的包装类如String， Integer对equals进行了重写，equals只比较值不比较内存地址。。。又复习了一遍了菜鸡。。。感觉都用equals就对了以后。。*/
            /**天真。。。null没有equals，此题第一次放进去的时候返回null，傻了吧，想办法解决吧，用 ==只能检验 -128 到 127 的Integer哦。。妈的java Integer就这么多。。*/
        }
        return true;
    }
}
