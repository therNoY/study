package java_base.java_lang;

import java.util.Random;

public class RandomTest {

    private String name;

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(new Random().nextInt(10));
//        }

        RandomTest randomTest = new RandomTest();
        randomTest.name = "2";

        getName(randomTest);


    }


    public static void getName(RandomTest r){
        r = new RandomTest();
        r.name = "1";
    }
}
