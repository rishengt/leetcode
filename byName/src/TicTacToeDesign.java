/**
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 *
 * You may assume the following rules:
 *
 * A move is guaranteed to be valid and is placed on an empty block. 不用考虑重复下一个点的问题，让life简单不少。。。。
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Example:
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 */

public class TicTacToeDesign {
    public static void main(String[] args) {
        TicTacToeDesign tic = new TicTacToeDesign(3);
        System.out.println(tic.move(1,1,1));
        System.out.println(tic.move(0,2,2));
        System.out.println(tic.move(0,1,1));
        System.out.println(tic.move(2,0,2));
        System.out.println(tic.move(2,1,1));

    }
    int[][] board; /**这个不写其实也没有多大关系，细品代码吧菜鸡*/
    int[] col;
    int[] row; /**这两个是用来判断横竖胜负手的*/
    int diag;/**往上提的对角线*/
    int antidiag;/**往下射的对角线*/
    int n;/**棋盘的大小，这就十分有趣了，可用来判断下射对角线的胜负手，蠢脑袋想不出来就画图吧少年。。。。*/
    public TicTacToeDesign(int n){
          this.n = n;
          diag = 0;
          antidiag = 0;
          col = new int[n];/**为什么不用board也可以这里就是揭秘了，因为一个坐标点下来横竖都有三次机会，每点一下都横竖记录并检查，自然用不上二维数组了。细品，回头看的傻叼*/
          row = new int[n];
    }
    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int point = player == 1? 1: -1; /**花里胡哨的写法，但我好像挺喜欢。。。*/
        this.col[col] +=point;
        this.row[row] +=point; /**想要赢横竖都要在同一个水平线上下满三次，所以可以这样写，精妙！*/
        if(row == col){/**上提对角线还是比较好推理出来的*/
            diag += point;
        }
        if(row+col == n-1){ /**下射确实比较难找到规律而且容易忘，没办法脑子笨，画图都推理不出来那就只好认栽咯傻逼*/
            antidiag +=point;
        }
        if(this.col[col]==n || this.row[row] == n || diag == n || antidiag == n) return 1;
        if(this.col[col]==-n || this.row[row] == -n || diag == -n || antidiag == -n) return 2;
        /**花里胡哨2。0
         * if(Math.abs(this.col[col] == n || Math.abs(this.row[row]) == n || this.diag == n || this.antidiag == n) return player;
         */
        return 0;
    }

}
