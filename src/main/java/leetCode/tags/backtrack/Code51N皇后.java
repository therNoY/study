package leetCode.tags.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 51. N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例:
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。
 * 当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子
 * 。当然，她横、竖、斜都可走一到七步，可进可退。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code51N皇后 {

    public List<List<String>> solveNQueens(int n) {
        return new NQueen().solveNQueens(n);
    }

    static class NQueen {

        List<List<String>> lists;

        public List<List<String>> solveNQueens(int n) {
            char[][] chars = new char[n][n];
            for (int i = 0; i < n; i++) {
                char[] c= new char[n];
                Arrays.fill(c, '.');
                chars[i] = c;
            }
            lists = new LinkedList<>();
            backtrack(chars, n);
            return lists;
        }

        public void backtrack(char[][] map, int row) {
            if (row == map.length) {
                lists.add(transCharToStringList(map));
                return;
            }
            for (int i = 0; i <= map.length; i++) {
                if (!isValid(map, row, i)) continue;
                map[row][i] = 'Q';
                backtrack(map, row + 1);
                map[row][i] = '.';
            }
        }

        private boolean isValid(char[][] map, int row, int col) {

            for (char[] chars : map) if (chars[col] == 'Q') return false;

            // check is valide upright
            for (int i = row - 1, j = col + 1; i >= 0 && j < map.length; i--, j++) {
                if (map[i][j] == 'Q') return false;
            }
            // check is valide upleft
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (map[i][j] == 'Q') return false;
            }

            return true;
        }


        public List<String> transCharToStringList(char[][] map) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < map.length; i++) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < map.length; j++) {
                    sb.append(map[i][j]);
                }
                list.add(sb.toString());
            }
            return list;
        }


    }

    public static void main(String[] args) {
        System.out.println(new Code51N皇后().solveNQueens(4));
    }
}
