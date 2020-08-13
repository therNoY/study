package nio.channel;

import util.ScanUtil;

import java.io.IOException;


/**
 * 测试同步阻塞IO 一个线程只能接收一个IO
 *
 */

public class SysNIOTest {
    public static void main(String[] args) throws IOException {

        new Thread(()-> SysBlockingServer.start(), "server").start();
//        new Thread(()-> NoBlockIoServer.start(), "server").start();


//        ScanUtil.runInThread(s-> SysNioClient.start(s));
//        ScanUtil.runInThread(s-> SysNioClient.blockSendStart(s));
        ScanUtil.runInThread(s-> SysNioClient.blockSendStart2(s));
    }
}
