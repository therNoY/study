package leetCode.self;

/**
 * 二分查找
 */
public class DoubleFind {

    static int[] a = {1,2,3,4,5,6,7,8,9,10,12,13,14,15,16};

    static int n = 11;

    public static void main(String[] args) {


        int s = 0, e = a.length - 1,i;

        while (s < e) {
            if (s == e) {
                System.out.println("No");
                break;
            }
            i = (s + e) /2;
            if (a[i] == n){
                System.out.println(i);
                break;
            }else if (a[i] > n) {
                e = i;
            }else {
                s = i;
            }
        }

    }
}
