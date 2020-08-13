package nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileChannelTest {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Administrator\\Desktop\\";
//        String file = path + "test.txt";
        String file = "desk.jpg";

//        byte[] ioGet = IOGetFile(path + file);
        ByteBuffer nioGet = NIOGetFile(path + file);


//        writeFile(ioGet, path + "io" + file);
        writeFile(nioGet, path + "nio" + file);

    }

    private static void writeNioFile(byte[] nioGet, String s) {

    }

    private static void writeFile(ByteBuffer bytes, String path) throws IOException {


        FileChannel outChannel = null;
        FileOutputStream fos = null;

        fos = new FileOutputStream(path);
        outChannel = fos.getChannel();

        outChannel.write(bytes);

        outChannel.close();
        fos.close();
    }

    private static ByteBuffer NIOGetFile(String path) throws IOException {

        long start = System.currentTimeMillis();

        RandomAccessFile aFile = new RandomAccessFile(new File(path), "rw");
        FileChannel fileChannel = aFile.getChannel();

        ByteBuffer readByte = ByteBuffer.allocate(1024);
        ByteBuffer writeByte = ByteBuffer.allocate((int) new File(path).length());
        while (fileChannel.read(readByte) != -1) {
            readByte.flip();
            System.arraycopy(readByte.array(), readByte.position(), writeByte.array(), writeByte.position(), readByte.limit());
            readByte.clear();
        }

        fileChannel.close();
        aFile.close();

        System.out.println("NIO读取文件的时间" + (System.currentTimeMillis() - start));

        return writeByte;
    }

    private static byte[] IOGetFile(String path) throws IOException {

        long start = System.currentTimeMillis();

        FileInputStream inputStream = new FileInputStream(new File(path));

        byte[] readBytes = new byte[1024];

        byte[] writeByte = new byte[(int) new File(path).length()];
        int position = 0;

        int len;
        while ((len = inputStream.read(readBytes)) != -1) {
            System.arraycopy(readBytes, 0, writeByte, position, len);
            position += readBytes.length;
        }

        inputStream.close();
        System.out.println("基础IO读取文件的时间" + (System.currentTimeMillis() - start));

        return writeByte;
    }

}
