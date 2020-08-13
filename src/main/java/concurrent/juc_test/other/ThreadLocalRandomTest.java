package concurrent.juc_test.other;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {


    public static void main(String[] args) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        int i = 640;
        int c = 0;
        while (i > 0) {
            int r = localRandom.nextInt(64);
            if (r == 0) {
                System.out.println(i +" "+ r);
                c++;
                i--;
            }
        }

        System.out.println("让步次数" + c);
    }
}
