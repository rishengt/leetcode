import java.util.Arrays;
import java.util.List;

/**
 * In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor.
 * For example, the root an, followed by other, which can form another word another.
 *
 * Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it.
 * If a successor has many roots can form it, replace it with the root with the shortest length.
 *
 * You need to output the sentence after the replacement.
 *
 *
 *
 * Example 1:
 *
 * Input: dict = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 *
 * Constraints:
 *
 * The input will only have lower-case letters.
 * 1 <= dict.length <= 1000
 * 1 <= dict[i].length <= 100
 * 1 <= sentence words number <= 1000
 * 1 <= sentence words length <= 1000
 */
public class ReplaceWords {
    public static void main(String[] args) {
        System.out.println(new ReplaceWords().replaceWords(Arrays.asList("a", "aa","aaa","aaaa"), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"));
    }
    class TrieNode{
        TrieNode[] children;
        String word;
        boolean flag;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    public String replaceWords(List<String> dict, String sentence) {
        String ans = "";
        TrieNode root = new TrieNode();
        for(String s: dict){
            TrieNode pointer = root;
            for(int i = 0; i< s.length(); i++){
                char c = s.charAt(i);
                if(pointer.children[c-'a'] == null){
                    pointer.children[c-'a'] = new TrieNode();
                }
                pointer = pointer.children[c-'a'];
            }
            pointer.word = s;
        }
        String[] k = sentence.split("\\s+");
        for(String s: k){
            TrieNode pointer = root;
            String word = "";
            for(int i = 0; i< s.length(); i++){
                char c=s.charAt(i);
                if(pointer.children[c-'a'] == null) break;
                if(pointer.children[c-'a'] !=null){
                    pointer = pointer.children[c-'a'];
                }
                if(pointer.word != null){
                    word = pointer.word;
                    break;
                }
            }
            ans = word == ""?ans+s+" ": ans+word+" ";
        }
        return ans.trim();
    }
}
