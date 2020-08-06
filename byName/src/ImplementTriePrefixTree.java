/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class ImplementTriePrefixTree {

    class TrieNode{
        public TrieNode(){
            children = new TrieNode[26]; //其实之所以是26是因为题目的constrain。。。。
            flag = false;
        }
        TrieNode[] children;
        boolean flag;
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public ImplementTriePrefixTree() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode pointer = root;
        for(int i = 0; i<word.length(); i++){
            char c = word.charAt(i);
            if(pointer.children[c-'a'] == null) pointer.children[c-'a'] = new TrieNode();
            pointer = pointer.children[c-'a'];
        }
        pointer.flag = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode pointer = root;
        for(int i = 0; i<word.length(); i++){
            char c = word.charAt(i);
            if(pointer.children[c-'a'] == null) return false;
            pointer = pointer.children[c-'a'];
        }
        return pointer.flag;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode pointer = root;
        for(int i = 0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(pointer.children[c-'a'] == null) return false;
            pointer = pointer.children[c-'a'];
        }
        return true;
    }
}
