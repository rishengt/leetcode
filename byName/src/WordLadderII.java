import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

public class WordLadderII {
    /**凭你的悟性大概也就只能领悟这两种了，算了吧菜🐔*/
    public static void main(String[] args) {
        System.out.println(new WordLadderII().findLadders("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"})));
        System.out.println(new WordLadderII().findLadders("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log"})));
        System.out.println(new WordLadderII().findLaddersII("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"})));
        System.out.println(new WordLadderII().findLaddersII("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log"})));
    }
/****************************这道题非常的难，但是leetcode.wang有者超级好的解释，建议理解透彻**************************************************************/
/***第一种做法过不了LC 所有 test, 会超时 但思路没有问题，剩下的就是优化了*/
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList){
        List<List<String>> ans = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        temp.add(beginWord);
        findLaddersHelper(beginWord, endWord, wordList, temp, ans);/**非常经典的backtrack了，细品*/
        return ans;
    }
    int min = Integer.MAX_VALUE;/**用来判断最短路径的工具数*/
    public void findLaddersHelper(String beginWord, String endWord, List<String> wordList, ArrayList<String> path, List<List<String>> ans){
        if(beginWord.equals(endWord)){/**符合条件*/
            if(min> path.size()){/**发现更短路径*/
                ans.clear();/**原先保存的路径都不要了因为不是最短的*/
                min = path.size();/**更新最短路径长度*/
                ans.add(new ArrayList<>(path));
            }else if(min == path.size()){/**遇到另一条最短路径，也加进来*/
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        if(path.size() >= min){/**要是已经用完路径还没到endWord，就提前结束*/
            return;
        }
        for(int i = 0; i<wordList.size(); i++){
            String curWord = wordList.get(i);
            if(path.contains(curWord)) continue;
            if(oneChanged(beginWord, curWord)){
                path.add(curWord);
                findLaddersHelper(curWord,endWord,wordList,path,ans);
                path.remove(path.size()-1);
            }
        }
    }
    public boolean oneChanged(String beginWord, String curWord){
        int count = 0;
        for(int i = 0; i< beginWord.length(); i++){
            if(beginWord.charAt(i) != curWord.charAt(i)) count++;
            if(count == 2) return false;
        }
        return count == 1;
    }
/***********以上的方法非常简单粗暴，但输入词条一长就gg，大概是你这菜🐔唯一能记住的方法了。。。下面是BFS+DFS,对上面方法的优化*******************/
    public List<List<String>> findLaddersII(String start, String end, List<String> wordList){
        HashSet<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<>();/**是用来映射单词与它只变化一个字母所能得出所有单词的map*/
        HashMap<String, Integer> distance = new HashMap<>();/**bfs层次遍历， 按层次记录每个变化到beginWord的距离，每深入一层就+1*/
        ArrayList<String> solution = new ArrayList<>();
        dict.add(start);
        bfs(start, end, dict, nodeNeighbors, distance);/** 用bfs得出最短路径（能达到endWord最浅的层数）*/
        dfs(start,end,dict,nodeNeighbors,distance,solution,res);/**跟据得出的最短路径把符合条件的路径都加到res里面*/
        return res;
    }
    /**BFS : Trace every node's distance from the start node(level by level)*/
    public void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors,HashMap<String, Integer> distance){
        for(String str : dict){
            nodeNeighbors.put(str, new ArrayList<>());
        }
        Queue<String> queue = new LinkedList<>();/**说了是BFS那怎么能少得了queue。。。。*/
        queue.offer(start);
        distance.put(start,0);/**我自己离我自己当然0距离*/
        while(!queue.isEmpty()){
            int count = queue.size();
            boolean foundEnd = false;
            for(int i = 0; i< count; i++){
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                ArrayList<String> neighbors = getNeighbors(cur,dict);
                for(String neighbor : neighbors){
                    nodeNeighbors.get(cur).add(neighbor);
                    if(!distance.containsKey(neighbor)){//check if visited
                        distance.put(neighbor, curDistance+1);
                        if(end.equals(neighbor)){/**到达目的单词*/
                            foundEnd = true;
                        }else{
                            queue.offer(neighbor);
                        }
                    }
                }
                if(foundEnd) break;
            }
        }
    }
    public ArrayList<String> getNeighbors(String word, Set<String> dict){
        ArrayList<String> ans = new ArrayList<>();
        char[] array = word.toCharArray();
        for(int i = 0; i<word.length(); i++){
            for(int j = 'a'; j<='z'; j++){
                if(array[i] == j) continue;
                char c = array[i];
                array[i] = (char)j;
                if(dict.contains(String.valueOf(array))) ans.add(String.valueOf(array));
                array[i] = c;
            }
        }
        return ans;
    }
    //DFS : output all paths with the shortest distance
    public void dfs(String cur, String end, Set<String> dict, HashMap<String,ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance,
                    ArrayList<String> path, List<List<String>> res){
        path.add(cur);
        if(end.equals(cur)){
            res.add(new ArrayList<>(path));
        }else{
            for(String next: nodeNeighbors.get(cur)){
                if(distance.get(next) == distance.get(cur)+1){
                    dfs(next,end,dict,nodeNeighbors,distance,path,res);
                }
            }
        }
        path.remove(path.size()-1);
    }
}
