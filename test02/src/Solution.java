/**
 * @author LongZhen
 * @date Created in 2022/11/8 23:13
 */


public class Solution {

    /**
     * 判断某⼀⾏是否被填满：一个个格子判断是否已经被放置
     * @param board
     * @param row
     * @return
     */
    public boolean isFull(boolean[][] board, int row) {
        for(int i = 0; i < board[0].length; i++) {
            if(!board[row][i]) {
                return false;
            }
        }

        return true;
    }

    /**
     *判断dot形状(即占据⼀个格⼦的块)是否可以放置在某个位置：判断该格子是否已经被放置
     * @param board
     * @param pos
     * @return
     */
    public boolean lockDot(boolean[][] board, int[] pos) {
        return !board[pos[0]][pos[1]];
    }

    /**
     * 判断某个形状(例如L)的块是否可以放置在某个位置：判断这个形状的块所需的格子是否被占用
     * @param board
     * @param pos 左上角部分放置的位置
     * @param piece
     * @return
     */
    public boolean lockPiece(boolean[][] board, int[] pos, String piece) {
        int x = pos[0];
        int y = pos[1];
        if("L".equals(piece)) {
            if(board[x][y]) {
                return false;
            }

            if(x + 1 >= board.length || board[x + 1][y]) {
                return false;
            }

            if(x + 2 >= board.length || board[x + 2][y]) {
                return false;
            }

            if(x + 2 >= board.length || y + 1 > board[0].length || board[x + 2][y + 1]) {
                return false;
            }

            if(x + 2 >= board.length || y + 2 > board[0].length || board[x + 2][y + 2]) {
                return false;
            }
        } else {
            // 其它形状的判断
        }

        return true;
    }
}
