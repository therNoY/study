package leetCode.dfs_bfs;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 示例 1:
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * 示例 2:
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * [["1","0","1","1","1"],["1","0","1","0","1"],["1","1","1","0","1"]]
 */
public class Code200岛屿数量 {

    public static void main(String[] args) {
        System.out.println(new Code200岛屿数量().numIslands(new char[][]{
            {'1','0','1','1','1'},
            {'1','0','1','0','1'},
            {'1','1','1','0','1'}
        }));
    }

    int maxR;
    int maxC;

    public int numIslands(char[][] grid) {
        int count = 0;
        if (grid.length > 0) {
            maxR = grid.length;
            maxC = grid[0].length;
            boolean[][] isVised = new boolean[maxR][maxC];
            for (int i = 0; i < maxR; i++) {
                for (int j = 0; j < maxC; j++) {
                    if (grid[i][j] == '1' && !isVised[i][j]) {
                        bfs(i, j, isVised, grid);
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 找到
     * @param r
     * @param c
     * @param isVised
     * @param grid
     */
    public void bfs(int r, int c, boolean[][] isVised, char[][] grid){
        isVised[r][c] = true;
        if (r + 1 < maxR && grid[r + 1][c] == '1' && !isVised[r + 1][c]) bfs(r + 1, c, isVised, grid);
        if (r - 1 >= 0 && grid[r - 1][c] == '1' && !isVised[r - 1][c]) bfs(r - 1, c, isVised, grid);
        if (c + 1 < maxC && grid[r][c + 1] == '1' && !isVised[r][c + 1]) bfs(r, c + 1, isVised, grid);
        if (c - 1 >= 0 && grid[r][c - 1] == '1' && !isVised[r][c - 1]) bfs(r, c - 1, isVised, grid);
    }


}

