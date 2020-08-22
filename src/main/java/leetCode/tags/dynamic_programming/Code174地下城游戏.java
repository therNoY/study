package leetCode.tags.dynamic_programming;

/**
 * 174. 地下城游戏
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。
 * 地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，
 * 他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数
 * （若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），
 * 要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * <p>
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * <p>
 * -2   -3	  3
 * -5	-10	  1
 * 10	30	 -5
 * <p>
 * 说明:
 * <p>
 * 骑士的健康点数没有上限。
 * <p>
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code174地下城游戏 {

    public static void main(String[] args) {

        System.out.println(new Code174地下城游戏().calculateMinimumHP(new int[][]{
                {-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}
        }));


        System.out.println(new Code174地下城游戏().calculateMinimumHP(new int[][]{
                {0, -3}
        }));

        System.out.println(new Code174地下城游戏().calculateMinimumHP(new int[][]{
                {100}
        }));

    }

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        if (m == 0) return 0;

        /**
         * dp[i][j]表示 从ij点到达终点需要的最小生命值
         */
        int dp[][] = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = Math.max(0 - dungeon[i][j], 0);
                } else if (i == m - 1) {
                    dp[i][j] = Math.max(dp[i][j + 1] - dungeon[i][j], 0);
                } else if (j == n - 1) {
                    dp[i][j] = Math.max(dp[i + 1][j] - dungeon[i][j], 0);
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                    dp[i][j] = Math.max(dp[i][j], 0);
                }
            }
        }

        return dp[0][0] + 1;

    }

    public int calculateMinimumHP2(int[][] dungeon) {

        int m = dungeon.length;
        int n = dungeon[0].length;

        if (m == 0) return 0;

        /**
         * 到达第ij个点需要的最小生命值和剩余生命
         */
        Knight knight[][] = new Knight[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    Knight k = new Knight();
                    if (dungeon[0][0] <= 0) {
                        k.minLeft = 1 - dungeon[0][0];
                        k.currentLeft = 1;
                    } else {
                        k.minLeft = 1;
                        k.currentLeft = dungeon[0][0] + 1;
                    }
                    knight[i][j] = k;
                } else if (i == 0) {
                    Knight k = new Knight();
                    Knight u = knight[i][j - 1];
                    if (u.currentLeft + dungeon[i][j] > 0) {
                        k.minLeft = u.minLeft;
                        k.currentLeft = u.currentLeft + dungeon[i][j];
                    } else {
                        k.minLeft = Math.max(u.minLeft - (u.currentLeft + dungeon[i][j]) + 1, u.minLeft);
                        k.currentLeft = 1;
                    }
                    knight[i][j] = k;
                } else if (j == 0) {
                    Knight k = new Knight();
                    Knight l = knight[i - 1][j];
                    if (l.currentLeft + dungeon[i][j] > 0) {
                        k.minLeft = l.minLeft;
                        k.currentLeft = l.currentLeft + dungeon[i][j];
                    } else {
                        k.minLeft = Math.max(l.minLeft - (l.currentLeft + dungeon[i][j]) + 1, l.minLeft);
                        k.currentLeft = 1;
                    }
                    knight[i][j] = k;
                } else {
                    Knight k = new Knight();
                    Knight l = knight[i][j - 1];
                    Knight u = knight[i - 1][j];

                    int lm, um, lc, uc;

                    if (l.currentLeft + dungeon[i][j] > 0) {
                        lm = l.minLeft;
                        lc = l.currentLeft + dungeon[i][j];
                    } else {
                        lm = Math.max(l.minLeft - (l.currentLeft + dungeon[i][j]) + 1, l.minLeft);
                        lc = 1;
                    }

                    if (u.currentLeft + dungeon[i][j] > 0) {
                        um = u.minLeft;
                        uc = u.currentLeft + dungeon[i][j];
                    } else {
                        um = Math.max(u.minLeft - (u.currentLeft + dungeon[i][j]) + 1, u.minLeft);
                        uc = 1;
                    }

                    if (lm < um) {
                        k.minLeft = lm;
                        k.currentLeft = lc;
                    } else {
                        k.minLeft = um;
                        k.currentLeft = uc;
                    }
                    knight[i][j] = k;
                }
            }
        }

        return knight[m - 1][n - 1].minLeft;
    }

    class Knight {
        int minLeft;
        int currentLeft;
    }
}
