package leetCode.every_day;

public class Code130被围绕的区域 {
    public static void main(String[] args) {
        solve(new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'},});
    }

    /**
     * 从周围出发 找里面的
     *
     * @param board
     */
    public static void solve(char[][] board) {
        int row = board.length, col = board[0].length;
        boolean[][] out = new boolean[row][col];
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') helper(board, out, 0, i);
            if (board[row - 1][i] == 'O') helper(board, out, row - 1, i);
        }
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') helper(board, out, i, 0);
            if (board[i][col - 1] == 'O') helper(board, out, i, col - 1);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!out[i][j]) board[i][j] = 'X';
            }
        }
    }

    public static void helper(char[][] board, boolean[][] out, int i, int j) {
        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
            if (out[i][j]) return;
            if (board[i][j] == 'O') {
                out[i][j] = true;
                helper(board, out, i - 1, j);
                helper(board, out, i + 1, j);
                helper(board, out, i, j - 1);
                helper(board, out, i, j + 1);
            }

        }
    }
}
