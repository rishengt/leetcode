/**\
 * Implement the StreamChecker class as follows:
 *
 * StreamChecker(words): Constructor, init the data structure with the given words.
 * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
 *
 *
 * Example:
 *
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
 * streamChecker.query('a');          // return false
 * streamChecker.query('b');          // return false
 * streamChecker.query('c');          // return false
 * streamChecker.query('d');          // return true, because 'cd' is in the wordlist
 * streamChecker.query('e');          // return false
 * streamChecker.query('f');          // return true, because 'f' is in the wordlist
 * streamChecker.query('g');          // return false
 * streamChecker.query('h');          // return false
 * streamChecker.query('i');          // return false
 * streamChecker.query('j');          // return false
 * streamChecker.query('k');          // return false
 * streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 *
 *
 * Note:
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * Words will only consist of lowercase English letters.
 * Queries will only consist of lowercase English letters.
 * The number of queries is at most 40000.
 */

/**这题用set依旧会爆炸，一般这种搜索字母的用set又炸掉的希望你能想起一个叫trie的东西。。。。*/
public class StreamofCharacters {
    public static void main(String[] args) {
        StreamofCharacters k = new StreamofCharacters(new String[]{"ab","ba","aaab","abab","baa"});
        System.out.println(k.query('a'));
        System.out.println(k.query('a'));
        System.out.println(k.query('a'));
        System.out.println(k.query('a'));
        System.out.println(k.query('a'));
        System.out.println(k.query('b'));
        System.out.println(k.query('a'));
        System.out.println(k.query('b'));
        System.out.println(k.query('a'));
        System.out.println(k.query('b'));
        System.out.println(k.query('b'));
        System.out.println(k.query('b'));
        System.out.println(k.query('a'));
    }

    class TrieNode{
        TrieNode[] children;
        boolean flag;
        String word;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    TrieNode root;
    String k = "";
    public StreamofCharacters(String[] words) {
        this.root = new TrieNode();
        for(String s: words){
            createTrie(s);
        }
    }

    public boolean query(char letter) {
        TrieNode pointer = this.root;
        k+=String.valueOf(letter);
        for(int i = k.length()-1; i>=0 && pointer!=null; i--){ /**这里的pointer ！= null 太关键了！！！！！！*/
            char c = k.charAt(i);
                pointer=pointer.children[c-'a'];
                if(pointer!=null && pointer.flag){
                    return true;
                }

        }
        return false;
    }

    public void createTrie(String s){ /**反着存，反着拿**/
        TrieNode pointer = this.root;
        for(int i = s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if(pointer.children[c-'a'] == null){
                pointer.children[c-'a'] = new TrieNode();
            }
            pointer = pointer.children[c-'a'];
        }
        pointer.flag = true;
        pointer.word = s;
    }
}
