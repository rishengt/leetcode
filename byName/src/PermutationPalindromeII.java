import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 * Return an empty list if no palindromic permutation could be form.
 *
 * Example 1:
 *
 * Input: "aabb"
 * Output: ["abba", "baab"]
 * Example 2:
 *
 * Input: "abc"
 * Output: []
 */
public class PermutationPalindromeII {
    public static void main(String[] args) {
        System.out.println(new PermutationPalindromeII().allPermute("aabb"));
        System.out.println(new PermutationPalindromeII().allPermute("aabbhijkkjih"));/**用这方法LC会超时但菜🐔如我我只会这个了。。。*/
    }

    public List<String> allPermute(String s){
        List<String> ans = new ArrayList<>();
        char[] kao = s.toCharArray();
        Arrays.sort(kao);
        List<List<Character>> list = new ArrayList<>();
        dfs(kao,new ArrayList<>(),list,new ArrayList<>());
//        for(List<Character> c: list){
//            if(isPalindrom(String.valueOf(c))) ans.add(String.valueOf(c)); 把list转成string好像只能用builder。。。。我太菜了
//        }
        for(List<Character> c: list){
            StringBuilder sb = new StringBuilder();
            for(char fk : c){
                sb.append(fk);
            }
            if(isPalindrom(sb.toString())) ans.add(sb.toString());
        }
        return ans;
    }
    public void dfs(char[] s , List<Character> list, List<List<Character>> ans, List<Integer> repeated){
        if(list.size() == s.length){
            ans.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i< s.length; i++){
            if(repeated.contains(i)) continue;
            if(i>0&&!repeated.contains(i-1)&&s[i-1]==s[i]) continue; /**对于去重来说太过重要了这一行，为啥理解不了。。。。*/
            list.add(s[i]);
            repeated.add(i);
            dfs(s,list,ans,repeated);
            repeated.remove(repeated.size()-1);
            list.remove(list.size()-1);
        }
    }
    public boolean isPalindrom(String s){
        for(int i =0, j = s.length()-1; i<j; i++,j--){
            if(s.charAt(i)!=s.charAt(j)) return false;
        }
        return true;
    }




    /*******************************************以下是LC 的BF答案*******************************************还是会超时*****/
    Set< String > set = new HashSet< >();
    public List < String > generatePalindromes(String s) {

        permute(s.toCharArray(), 0);
        return new ArrayList < String > (set);
    }
    public boolean isPalindrome(char[] s) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] != s[s.length - 1 - i])
                return false;
        }
        return true;
    }
    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    void permute(char[] s, int l) {
        if (l == s.length) {
            if (isPalindrome(s))
                set.add(new String(s));
        } else {
            for (int i = l; i < s.length; i++) {
                swap(s, l, i);
                permute(s, l + 1);
                swap(s, l, i);
            }
        }
    }
}
