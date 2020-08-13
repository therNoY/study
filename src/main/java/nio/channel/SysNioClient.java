package nio.channel;

import util.log.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SysNioClient {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
            Logger.info("链接成功");
            socketChannel.configureBlocking(false);

            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

            if (args.length > 0) {
                writeBuffer.put(args[0].getBytes());
            }else {
                writeBuffer.put("default hello".getBytes());
            }

            writeBuffer.flip();

            Logger.info("客户端请求发送数据" + new String(writeBuffer.array()));
            socketChannel.write(writeBuffer);
            socketChannel.read(readBuffer);

            Logger.info("收到服务器回应" + new String(readBuffer.array()));
            Logger.info("准备关闭连接");
            socketChannel.close();
        } catch (IOException e) {
        }
    }


    public static void start(String... s){
        main(s);
    }

    public static void blockSendStart(String s){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
            Logger.info("链接成功");
//            socketChannel.configureBlocking(true);

            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

            writeBuffer.put(s.getBytes());

            writeBuffer.flip();


            for (int i = 0; i < 5; i++) {
                Logger.info("发送请求：" + i + "_" + new String(writeBuffer.array()));
                socketChannel.write(writeBuffer);
                socketChannel.read(readBuffer);
                Logger.info("收到客户端回应" + new String(readBuffer.array()));

                Thread.sleep(5000);
            }

            Logger.info("准备关闭连接");
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void blockSendStart2(String s){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
            Logger.info("链接成功");


//            socketChannel.configureBlocking(true);

            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

            for (int i = 0; i < 5; i++) {
                writeBuffer.put((i + "_" + s).getBytes());
                writeBuffer.flip();
                Logger.info("客户端请求发送数据" + new String(writeBuffer.array()));
                socketChannel.write(writeBuffer);

                socketChannel.read(readBuffer);
                readBuffer.flip();
                Logger.info("收到服务器回应" + new String(readBuffer.array()).trim() +"\n");
                System.out.println();
                Thread.sleep(1000);
                writeBuffer.clear();
                readBuffer.clear();
            }
            Logger.info("准备关闭连接");
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
