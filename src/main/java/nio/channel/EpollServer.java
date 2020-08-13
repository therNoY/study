package nio.channel;

import util.log.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * Io 多路服用的服务端 使用Select
 * https://www.cnblogs.com/snailclimb/p/9086334.html
 */
public class EpollServer {


    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        try {
            ServerSocketChannel ssc8000 = ServerSocketChannel.open();
            ssc8000.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            ssc8000.configureBlocking(false);
            // 注册 channel，并且指定感兴趣的事件是 Accept
            ssc8000.register(selector, SelectionKey.OP_ACCEPT);

//            ServerSocketChannel ssc8001 = ServerSocketChannel.open();
//            ssc8001.socket().bind(new InetSocketAddress("127.0.0.1", 8001));
//            ssc8001.configureBlocking(false);
//            // 注册 channel，并且指定感兴趣的事件是 Accept
//            ssc8001.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);
            writeBuff.put("received".getBytes());
            writeBuff.flip();

            while (true) {

                int nReady = selector.select();
                Logger.info("有" + nReady + "个通道发生变化");
                if (nReady == 0) {
                    continue;
                }

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();


                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        Logger.info("新的连接");
                        SocketChannel socketChannel = ssc8000.accept();
                        socketChannel.configureBlocking(false);
                        SelectionKey connectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                        connectionKey.attach(new EpollTask(socketChannel, connectionKey));
                    }
                    else if (key.isReadable()) {
                        Logger.info("读取连接");
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        readBuff.flip();


                        EpollTask conn = (EpollTask)key.attachment();
                        conn.onRead(getInt(readBuff));

                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    else if (key.isWritable()) {
                        Logger.info("写连接");
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        EpollTask conn = (EpollTask)key.attachment();
                        key.interestOps(SelectionKey.OP_READ);
                        conn.onWrite();
                    }
                }

//                while (it.hasNext()) {
//                    SelectionKey key = it.next();
//                    it.remove();
//
//                    if (key.isAcceptable()) {
//                        try {
//                            Logger.info("新的连接");
//                            SocketChannel socketChannel = ssc8000.accept();
//                            socketChannel.configureBlocking(false);
//                            SelectionKey connectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
//                            connectionKey.attach(new EpollTask(socketChannel, connectionKey));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else if (key.isReadable()) {
//                        try {
//                            Logger.info("读取信息");
//                            SocketChannel socketChannel = (SocketChannel) key.channel();
//                            readBuff.clear();
//                            if (socketChannel.isOpen()) {
//                                socketChannel.read(readBuff);
//
//                                readBuff.flip();
//                                Logger.info("received : " + new String(readBuff.array()));
//                                key.interestOps(SelectionKey.OP_WRITE);
//                            }else {
//                                socketChannel.close();
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            SocketChannel socketChannel = (SocketChannel) key.channel();
//                            socketChannel.close();
//                        }
//                    } else if (key.isWritable()) {
//                        try {
//                            Logger.info("写信息");
//                            writeBuff.rewind();
//                            SocketChannel socketChannel = (SocketChannel) key.channel();
//                            socketChannel.write(writeBuff);
//                            key.interestOps(SelectionKey.OP_READ);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getInt(ByteBuffer buf) {
        int r = 0;
        while (buf.hasRemaining()) {
            r *= 10;
            r += buf.get() - '0';
        }

        return r;
    }
}

