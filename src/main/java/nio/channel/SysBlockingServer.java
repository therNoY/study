package nio.channel;

import util.log.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 一个简单的阻塞同步客户端  同步阻塞
 */
public class SysBlockingServer {
    public static void main(String args[]) {

        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            ByteBuffer writeBuffer = ByteBuffer.allocate(2048);

            for (; ; ) {
                Logger.info("准备接受客户端连接据");
                SocketChannel socketChannel = ssc.accept();
                Logger.info("收到请求连接信息" + socketChannel.socket().getInetAddress().getAddress());
                try {
                    for (; ; ) {
                        Logger.info("等待接收客户端请求数据");
                        socketChannel.read(readBuffer);
                        readBuffer.flip();
                        Logger.info("收到客户端请求数据" + new String(readBuffer.array()));
                        writeBuffer.put(("response:_" + new String(readBuffer.array())).getBytes());
                        writeBuffer.flip();
                        Logger.info("准备回应" + new String(writeBuffer.array()));
                        socketChannel.write(writeBuffer);

                        writeBuffer.clear();
                        readBuffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Logger.info("关闭客户端连接");
                    socketChannel.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void start() {
        main(null);
    }


}
