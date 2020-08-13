package leetCode.tags.array;

/**
 * 假设你是一个专业的狗仔，参加了一个 n 人派对，其中每个人被从 0 到 n - 1 标号。在这个派对人群当中可能存在一位 “名人”。
 * 所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。
 * 现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。
 * 而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。
 * 你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。
 * 在本题中，你可以使用辅助函数 bool knows(a, b) 获取到 A 是否认识 B。请你来实现一个函数 int findCelebrity(n)。
 * 派对最多只会有一个 “名人” 参加。若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 -1。
 输入: graph = [
 [1,1,0],
 [0,1,0],
 [1,1,1]
 ]
 输出: 1
 */
public class FindCelebrity {

    static int  array[][] = {{1,1,0},{0,1,0},{1,1,1}};

    public static void main(String[] args) {

        FindCelebrity findCelebrity = new FindCelebrity();
        findCelebrity.findCelebrity(array);
    }

    public int findCelebrity(int n[][]){
        return -1;
    }

    public boolean knows(int a, int b) {
        return array[a][b] == 1;
    }

}
