package java_base.IO;

import java.io.File;

public class FileBaseMethod {

    public static void main(String[] args) {
        File file = new File("D:/");


        file.listFiles(f->f.getName().contains("."));
    }

}
