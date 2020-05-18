import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

public class WordLadder {/**细节太多都不知道怎么备注好了，自己细品吧*/
    public static void main(String[] args) {
        System.out.println(new WordLadder().shortestPath("hit","cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"})));
        System.out.println(new WordLadder().shortestPath("hit","cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log"})));
    }

    public int shortestPath(String beginWord, String endWord, List<String> wordList){
        HashSet<String> visited = new HashSet<>();
        HashSet<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        if(dict.contains(beginWord)) dict.remove(beginWord);
        int ans = 0;
        queue.offer(beginWord);
        while(queue.size()!=0) {
            ans++;/**细节++*/
            int size = queue.size();/**细节bfs*/
            for (int i = 0; i < size; i++) {
                String temp = queue.poll();
                if (endWord.equals(temp)) return ans;
                else {
                    List<String> tempList = changeOneChar(temp, visited, new ArrayList<>(), dict);
                    for (String s : tempList) {
                        queue.offer(s);
                    }
                }
            }
        }
        return 0;
    }


    public List<String> changeOneChar(String s, HashSet<String> visited/**细节去重*/, List<String> ans, HashSet<String> dict){
        char[] array = s.toCharArray();
        for(int i = 0; i< array.length; i++){/**细节backtrack*/
            for(int j = 'a'; j<='z'; j++){
                if(array[i] == j){
                    continue;
                }
                char c = array[i];
                array[i] = (char)j;
                if(!visited.contains(String.valueOf(array))&&dict.contains(String.valueOf(array))){
                    ans.add(String.valueOf(array));
                    visited.add(String.valueOf(array));
                }
                array[i] = c;
            }
        }
        return ans;
    }
}
