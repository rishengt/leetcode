/**
 *Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 *
 *
 * A sudoku puzzle...
 *
 *
 * ...and its solution numbers marked in red.
 *
 * Note:
 *
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */

public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        new SudokuSolver().sudokuSolver(board);
        System.out.println(board);
    }

    public void sudokuSolver(char[][] board){
        sudokusolver(board);
    }

    public boolean sudokusolver(char[][] board){
        for(int i = 0; i<board.length;i++){
            for(int j = 0; j<9; j++){
                if(board[i][j] != '.')continue;/**这就很灵性*/
                for(char c='1'; c<='9'; c++){
                    if(isValid(i,j,board,c)){
                        board[i][j] = c;
                        if(sudokusolver(board)){
                            return true;
                        }
                        board[i][j] = '.';/**backtrack*/
                    }
                }
                return false;/**这两个return是为定势，记住在哪以及return什么！！！！！*/
            }
        }
        return true;/**在里for return false；整个method return true*/
    }

    public boolean isValid(int i, int j, char[][] board, char c){
        for(int col = 0; col<9; col++){
            if(board[i][col] == c) return false;
        }

        for(int row = 0; row < 9; row++){
            if(board[row][j] == c) return false;
        }

        for(int row = i/3*3; row < i/3*3+3; row++){
            for(int col = j/3*3; col<j/3*3+3; col++){
                if(board[row][col] == c) return false;
            }
        }
        return true;
    }
}
