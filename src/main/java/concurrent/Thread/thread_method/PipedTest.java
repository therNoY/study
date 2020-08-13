package concurrent.Thread.thread_method;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

/*
* 线程之间的管道命令传递
* */
public class PipedTest {

    public static void main(String[] args) throws IOException {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();

        /* 这里一定要做链接 */
        out.connect(in);


        new Thread(()->{
            try {
                int rec = -1;
                while ((rec = in.read()) != 0){
                    System.out.println(rec);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        String s = null;
        System.out.println("please writ...");
        while ((s = scanner.nextLine()) != "0") {
            out.write(s.getBytes());
            out.flush();
            System.out.println("please writ...");
        }

        out.close();



    }
}
