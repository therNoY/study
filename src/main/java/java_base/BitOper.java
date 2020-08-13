package java_base;

/**
 * 位操作
 */
public class BitOper {

    public static void main(String[] args) {

        char a = 'a';
        char A = 'A';
        char b = 'b';
        char B = 'B';
        // 字母 | ' '会转成小
        System.out.println(a | ' ');
        System.out.println(A | ' ');
        // 字母 & '_'会转成大
        System.out.println(b & '_');
        System.out.println(B & '_');
        // 字母 ^ ' '会小转大 大转小
        System.out.println(a ^ ' ');
        System.out.println(A ^ ' ');
        System.out.println(b ^ ' ');
        System.out.println(B ^ ' ');



        int n1 = 1;
        int n2 = -2;
        // 判断数字是否异号
        System.out.println((n1 ^ n2) < 0);


        // 消除这个数的二进制的最后一个1
        // 应用判断是一个数是否是2的指数
        System.out.println(n1 & (n1 - 1));

    }


}
