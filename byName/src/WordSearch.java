/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 *
 * Constraints:
 *
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */

public class WordSearch {
    public static void main(String[] args) {
        System.out.println(new WordSearch().wordSearch(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        },"ABCCED"));
        System.out.println(new WordSearch().wordSearch(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        },"SEE"));
        System.out.println(new WordSearch().wordSearch(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        },"ABCB"));
    }

    public boolean wordSearch(char[][] board, String word){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                 if(backtrack(board,word,i,j,0)){
                     return true;
                 }
            }
        }
        return false;
    }

    public boolean backtrack(char[][] board, String word, int x, int y, int index){
        if(x<0||x>=board.length||y<0||y>=board[0].length) return false;
        if(board[x][y] != word.charAt(index)) return false;
        if(index == word.length()-1) return true;
        /**if(index == word.length()-1&&x>0&&x<board.length&&y>0&&y<board.length&&board[x][y] == word.charAt(index))return true;条件合在一起就是不行*/
        char c = board[x][y];
        board[x][y] = '$';
        if(backtrack(board, word, x+1, y, index+1) ||
                            backtrack(board, word, x-1, y, index+1) ||
                            backtrack(board, word, x, y+1, index+1) ||
                            backtrack(board, word, x, y-1, index+1)){
            return true;
        }
        board[x][y] = c;
        return false;
    }
}
