package leetCode.tags.dynamic_programming;

/**
 Q：假设你有一个特殊的键盘包含下面的按键：
 Key 1: (A)：在屏幕上打印一个 'A'。
 Key 2: (Ctrl-A)：选中整个屏幕。
 Key 3: (Ctrl-C)：复制选中区域到缓冲区。
 Key 4: (Ctrl-V)：将缓冲区内容输出到上次输入的结束位置，并显示在屏幕上。
 现在，你只可以按键 N 次（使用上述四种按键），请问屏幕上最多可以显示几个 'A'呢？

 样例 1:
 输入: N = 3
 输出: 3
 解释:
 我们最多可以在屏幕上显示三个'A'通过如下顺序按键：
 A, A, A

 样例 2:
 输入: N = 7
 输出: 9
 解释:
 我们最多可以在屏幕上显示九个'A'通过如下顺序按键：
 A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 */
public class _4Key {

    public static void main(String[] args) {
        System.out.println(new _4Key().getMaxNum(3));
        System.out.println(new _4Key().getMaxNum(7));
    }

    public int getMaxNum(int n) {
        int[] dp = new int[n];
        if (n < 4) return n;
        for (int i = 0; i < 4; i++) {
            dp[i] = i + 1;
        }
        for (int i = 4; i < n; i++) {
            dp[i] = getMax(dp[i-1] + 1, dp[i-2] + 2, dp[i-3] * 2, dp[i-3] + 3, dp[i-1] + dp[i-4]);
        }
        return dp[n-1];
    }

    public int getMax(int... nums) {
        int max = -1;
        for (int n : nums) {
            if (n > max) max = n;
        }
        return max;

    }
}
