import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 */

public class ConcatenatedWords {
    public static void main(String[] args) {
        System.out.println(new ConcatenatedWords().findAllConcatenatedWordsInADict(new String[]{"catsdogcats","cat","cats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"}));
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words){
        List<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for(String word: words){
            set.add(word);
        }
        for(String word: words){
            if(dfs(word,set)){
                list.add(word);
            }
        }
        return list;
    }

    public boolean dfs(String word, HashSet<String> set){
        for(int i = 1; i<word.length();i++){
            if(set.contains(word.substring(0,i))){
                String suffix = word.substring(i);
                if(set.contains(suffix)||dfs(suffix,set)){
                    return true;
                }
            }
        }
        return false;
    }

/*********************************************下面的方法太繁琐了，感觉记不住，砸手里**************************************************************************************/
    public List<String> findAllConcatenatedWordsInADictII(String[] words) {
        List<String> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> checked = new HashSet<>();
        for(String s: words){
            map.put(s,1);
        }
        for(String s : words){
            if(getSize(s,map,checked)>1){
                ans.add(s);
            }
        }
        return ans;
    }

    public int getSize(String s, HashMap<String, Integer> map, HashSet <String> set){
        if(s.length() == 0){
            return 0;
        }
        if(set.contains(s)){
            return map.get(s);
        }
        int len = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< s.length(); i++){
            sb.append(s.charAt(i));
            if(map.getOrDefault(sb.toString(),0)>0){
                if(i==s.length()-1){
                    len = 1;
                    break;
                }
                int local = getSize(s.substring(i+1),map,set);
                if(local>0){
                    len = local + 1;
                    break;
                }
            }
        }
        set.add(s);
        map.put(s, Math.max(map.getOrDefault(s,0),len));
        return len;
    }
}
