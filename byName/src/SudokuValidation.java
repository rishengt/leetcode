import java.util.HashMap;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * A partially filled sudoku which is valid.
 *
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * Example 1:
 *
 * Input:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 *
 * Input:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 */

public class SudokuValidation {
    public static void main(String[] args) {
        System.out.println(new SudokuValidation().isValidSudoku(new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        }));
    }

    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i< board.length; i++){
            if(!isValidRow(board[i])) return false;
        }

        for(int i = 0; i< board.length; i++){
                if(!isValidCol(i,board)) return false;
        }

        for(int i = 0; i<=board.length; i+=3){
            for(int j = 0; j<9; j+=3){
                if(!isValidBox(i,j,board)) return false;
            }
        }
        return true;
    }

    public boolean isValidRow(char[]board){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i< board.length; i++){
            if(board[i] != '.'){
                if(map.getOrDefault(board[i],0)>0) return false;
            }
            map.put(board[i],1);
        }
        return true;
    }

    public boolean isValidCol(int col, char[][] board){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i< board.length; i++){
            if(board[i][col] != '.' ){
                if(map.getOrDefault(board[i][col],0)>0) return false;
            }
            map.put(board[i][col],1);
        }
        return true;
    }

    public boolean isValidBox(int row, int col, char[][]board){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i< 3; i++){
            for(int k = 0; k<3; k++){
                if(board[i][k]!='.') {
                    if (map.getOrDefault(board[i+row][k+col], 0) > 0) return false;
                }
                map.put(board[i][k],1);
            }
        }
        return true;
    }
}
