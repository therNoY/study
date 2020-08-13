package connect_pool.mysql;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;

public class Main {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ConnectionManager connectionMan = ConnectionManager.getInstance();//得到唯一实例
        //得到连接
        String name = "u3cdb";//从上下文得到你要访问的数据库的名字
        Connection con = connectionMan.getConnection(name);
        // 使用完毕
        connectionMan.freeConnection(name, con);//释放，但并未断开连接
        countDownLatch.await();
    }
}
