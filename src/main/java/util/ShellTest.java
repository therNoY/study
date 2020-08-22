package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.LongAdder;

public class ShellTest {

    public static void main(String[] args) throws Exception {

        String[] cmds = {"/bin/sh", "-c", "ps -ef|grep java"};
        Process pro = Runtime.getRuntime().exec(cmds);
        pro.waitFor();
        InputStream in = pro.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = read.readLine()) != null) {
            System.out.println(line);
        }
    }

    int func(int a, int b, int c) {
        int k = 1;
        if ((a > 0) || (b < 0)) {
            if (a + c > 0) {
                k = k + 1;
            } else {
                k = k + 2;
            }
        } else {
            k = k + 3;
        }
        if (c > 0) {
            k = k + 4;
        } else {

        }
        return k;
    }

    int func2(int a, int b, int c) {
        int k = 1;
        if ((a > 0) && (b < 0)) {
            if (a + c > 0) {
                k = k + 10;
            } else {
                k = k + 100;
            }
        } else {
            k = k + 1000;
        }
        return k;
    }


    /**
     * (2,3,4)、(5,4,-3)
     * @param a
     * @param b
     * @param c
     * @return
     */
    int func3(int a, int b, int c) {
        int k = 0;
        if ((a + b > 0) && (a + c > 0) && (b + c < 0)) {
            k = 10;
        }
        return k;
    }


    void func(int money) {
        double rate;
        if (money < 800) {
            rate = 0;
        } else if (money <= 1500) {
            rate = 0.03;
        } else if (money < 2000) {
            rate = 0.06;
        } else {
            rate = 0.1;
        }
    }

}