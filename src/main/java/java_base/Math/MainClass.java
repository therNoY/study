package java_base.Math;

public class MainClass {
    public static void main(String[] args) {
        testRound();
    }


    /**
     * Math  取绝对值 证书四舍五入 负数五舍六入
     */
    static void testRound() {
        double a = 1.4;
        double a2 = 1.5;
        double a3 = 1.6;
        double b = -1.4;
        double b1 = -1.5;
        double b2 = -1.6;


        System.out.println(Math.round(a));
        System.out.println(Math.round(a2));
        System.out.println(Math.round(a3));
        System.out.println(Math.round(b));
        System.out.println(Math.round(b1));
        System.out.println(Math.round(b2));
    }
}
