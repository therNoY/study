package java_base.IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


// https://www.jianshu.com/p/1e22f012bb01
public class FilesTest {


    static String path = "D:\\2019\\02\\1.txt";
//    static String path = "C:\\Users\\Administrator\\Desktop\\2019";


    public static void main(String[] args) throws IOException {
//        testReadLine();

Files.move(Paths.get(path), Paths.get("D:\\2019\\01\\ttttt\\1.txt"));
        return;


//        TestReadLine();


//        boolean exit = Files.exists(Paths.get(path));
//
//        if (!Files.exists(Paths.get(path + f + "05"))) {
//            Files.createDirectory(Paths.get(path + f + "05"));
//        }
    }

    private static void TestReadLine() throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("D:\\hello.txt")));
        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }
        in.close();
    }

    private static void testReadLine() throws IOException {
        File file = new File(path);
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));


        String red = null;

        while ((red = lineNumberReader.readLine()) != null) {
            System.out.println(red);
        }

        lineNumberReader.close();
    }

}
