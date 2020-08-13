package leetCode.tags.dynamic_programming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 464. 我能赢吗
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，
 * 累计整数和，先使得累计整数和达到 100 的玩家，即为胜者。
 * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），
 * 直到累计整数和 >= 100。
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），
 * 判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
 * 示例：
 * 输入：
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * 输出：
 * false
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 */
public class CanIWin {


    public static void main(String[] args) {
        System.out.println(new CanIWin().canIWin(10, 11));
    }


    /**
     * 状态压缩DP 注意
     * 1.status初始为0全都没有被选中
     * 2.状态的总数表示 (1<<num) - 1
     * 3.当前数 1 << (num-1)
     * 4.当前数是否在状态被选中 1 << (num-1) & status
     * 5.当前数加入状态中的选中 1 << (num-1) | status
     * 需要注意 位运算优先级较低 需要时刻加括号
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) return true;
        Boolean[] dp = new Boolean[(1 << maxChoosableInteger) - 1];

        return dfs(maxChoosableInteger, desiredTotal, 0, dp);

    }

    private boolean dfs(int maxChoosableInteger, int desiredTotal, int status, Boolean[] dp) {
        if (dp[status] != null) return dp[status];
        int temp;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            temp = 1 << (i - 1);
            if ((temp & status) == 0) {
                if (desiredTotal -i <= 0 || !dfs(maxChoosableInteger, desiredTotal - i, temp | status, dp)){
                    dp[status] = true;
                    return true;
                }
            }
        }
        dp[status] = false;
        return false;
    }

    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {

        if (maxChoosableInteger >= desiredTotal) return true;
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        /**
         *  dp表示"每个"取数(A和B共同作用的结果)状态下的输赢
         *  例如只有1,2两个数选择，那么 (1 << 2) - 1 = 4 - 1 = 3种状态表示：
         *  01,10,11分别表示：A和B已经选了1，已经选了2，已经选了1、2状态下，A的输赢情况
         *  并且可见这个表示所有状态的dp数组的每个状态元素的长度为maxChoosableInteger位的二进制数
         */
        Boolean[] dp = new Boolean[(1 << maxChoosableInteger) - 1];
        return dfs2(maxChoosableInteger, desiredTotal, 0, dp);
    }

    /**
     * @param maxChoosableInteger 选择的数的范围[1,2,...maxChoosableInteger]
     * @param desiredTotal        目标和
     * @param state               当前状态的十进制表示（记录着可能不止一个数被选择的状态）
     * @param dp                  记录所有状态
     * @return
     */
    private boolean dfs2(int maxChoosableInteger, int desiredTotal, int state, Boolean[] dp) {
        if (dp[state] != null)
            return dp[state];
        /**
         * 例如maxChoosableInteger=2，选择的数只有1,2两个，二进制只要两位就可以表示他们的选择状态
         * 最大位为2（第2位），也就是1 << (2 - 1)的结果，所以最大的位可以表示为  1 << (maxChoosableInteger - 1)
         * 最小的位可以表示为 1 << (1 - 1)，也就是1（第1位）
         * 这里i表示括号的范围
         */
        for (int i = 1; i <= maxChoosableInteger; i++) {
            //当前待抉择的位，这里的tmp十进制只有一位为1，用来判断其为1的位，对于state是否也是在该位上为1
            //用以表示该位（数字）是否被使用过
            /**
             * (&运算规则，都1才为1)
             * 例如,i=3, tmp = 4, state = 3;
             *  100
             * &011
             * =0  表示该位没有被使用过，也就是第三位没有被使用过，即数字3 (i)没有被使用过
             */
            int tmp = (1 << (i - 1));
            if ((tmp & state) == 0) {  //该位没有被使用过
                //如果当前选了i已经赢了或者选了i还没赢但是后面对方选择输了,tmp|state表示进行状态的更新
                /**
                 * 例如
                 *  100
                 * |011
                 * =111
                 */
                //注意这里并没有像回溯一样进行状态的(赋值化的)更新、回溯
                //其实这里是隐含了回溯的，我们通过参数传递更新后的state
                //但是我们在这个调用者这里的state还是没有进行更新的，所以
                //就相当于回溯了状态。
                if (desiredTotal - i <= 0 || !dfs(maxChoosableInteger, desiredTotal - i, tmp | state, dp)) {
                    dp[state] = true;
                    return true;
                }
            }
        }
        //如果都赢不了
        dp[state] = false;
        return false;
    }

    /**
     * 回溯解答
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin3(int maxChoosableInteger, int desiredTotal) {
        List<Integer> canChoiceList = new ArrayList<>(maxChoosableInteger);
        for (int i = 1; i < maxChoosableInteger + 1; i++) {
            canChoiceList.add(i);
        }
        return canIWin(canChoiceList, desiredTotal, true);
    }

    public boolean canIWin(List<Integer> canChoiceList, int lastNum, boolean isFirst) {

        for (int i = 0; i < canChoiceList.size(); i++) {
            if (canChoiceList.get(i) == lastNum) {
                return isFirst;
            }
        }

        for (int i = 0; i < canChoiceList.size(); i++) {
            int removeNum = canChoiceList.get(i);
            canChoiceList.remove(i);
            if (isFirst && canIWin(canChoiceList, lastNum - removeNum, !isFirst)) {
                return true;
            }
            canChoiceList.add(i);
        }
        return !isFirst;
    }

}
