import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 */

public class GroupAnagrams {
    public static void main(String[] args) {
        System.out.println(new GroupAnagrams().groupAnagram(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
    public List<List<String>> groupAnagram(String[] strings){
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();/**神来之笔，直接存List<String>，妈的你好菜啊多看看wordBreakII啦菜鸡*/
        for(String string: strings){
            char[] c =string.toCharArray();
            Arrays.sort(c);
            String temp = String.valueOf(c);/**必须要化成string去存，你直接存char【】会指向不同地址，出错***/
            /**你用toString（）也不行因为toString也是新开一个内存，HashMap还是检测不到重复的，只有String.valueOf()是static的*/
            if(map.containsKey(temp)){
                map.get(temp).add(string);
            }else{
                map.put(temp, new ArrayList<>());
                map.get(temp).add(string);
            }
        }
        for(List<String> list : map.values()){
            ans.add(list);
        }
        return ans;
    }
}
