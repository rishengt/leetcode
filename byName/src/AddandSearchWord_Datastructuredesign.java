import java.util.HashSet;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */
//public class AddandSearchWord_Datastructuredesign {
///**不能用set，会炸掉,看你LC submit就知道多惨了。。。人不好的面试官绝对会刁难你**/
//
//    /** Initialize your data structure here. */
//    public AddandSearchWord_Datastructuredesign() {
//
//    }
//
//    /** Adds a word into the data structure. */
//    public void addWord(String word) {
//
//    }
//
//    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//    public boolean search(String word) {
//
//    }
//}
