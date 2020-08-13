package connect_pool.mysql;


import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * 连接池 管理类
 */
public class ConnectionManager {

    private static ConnectionManager instance;//唯一数据库连接池管理实例类
    static private int clients;                 //客户连接数
    private Vector<ConnectSource> drivers = new Vector();//驱动信息
    private Hashtable<String, ConnectionPool> pools = new Hashtable();//连接池

    /**
     * 实例化管理类
     */
    public ConnectionManager() {
        this.init();
    }

    /**
     * 得到唯一实例管理类
     *
     * @return
     */
    public static ConnectionManager getInstance() {
        if (instance == null) {
            synchronized (ConnectionManager.class) {
                if (instance == null) {
                    instance = new ConnectionManager();
                }
            }
        }
        return instance;

    }

    /**
     * 释放连接
     *
     * @param name
     * @param con
     */
    public void freeConnection(String name, Connection con) {
        ConnectionPool pool = pools.get(name);//根据关键名字得到连接池
        if (pool != null)
            pool.freeConnection(con);//释放连接
    }

    /**
     * 得到一个连接根据连接池的名字name
     *
     * @param name
     * @return
     */
    public Connection getConnection(String name) {
        ConnectionPool pool = null;
        Connection con = null;
        pool = pools.get(name);//从名字中获取连接池
        con = pool.getConnection();//从选定的连接池中获得连接
        if (con != null)
            System.out.println("得到连接。。。");
        return con;
    }

    /**
     * 得到一个连接，根据连接池的名字和等待时间
     *
     * @param name
     * @return
     */
    public Connection getConnection(String name, long timeout) {
        ConnectionPool pool = null;
        Connection con = null;
        pool = (ConnectionPool) pools.get(name);//从名字中获取连接池
        con = pool.getConnection(timeout);//从选定的连接池中获得连接
        System.out.println("得到连接。。。");
        return con;
    }

    /**
     * 释放所有连接
     */
    public synchronized void release() {
        Enumeration allpools = pools.elements();
        while (allpools.hasMoreElements()) {
            ConnectionPool pool = (ConnectionPool) allpools.nextElement();
            if (pool != null) pool.release();
        }
        pools.clear();
    }

    /**
     * 创建连接池
     */
    private void createPools(ConnectSource dsb) {
        ConnectionPool dbPool = new ConnectionPool();
        dbPool.setName(dsb.getName());
        dbPool.setDriver(dsb.getDriver());
        dbPool.setUrl(dsb.getUrl());
        dbPool.setUser(dsb.getUsername());
        dbPool.setPassword(dsb.getPassword());
        dbPool.setMaxConn(dsb.getMaxConn());
        pools.put(dsb.getName(), dbPool);
    }

    /**
     * 初始化连接池的参数
     */
    private void init() {
        //加载驱动程序
        this.loadDrivers();
        //创建连接池
        Iterator allDriver = drivers.iterator();
        while (allDriver.hasNext()) {
            this.createPools((ConnectSource) allDriver.next());
            System.out.println("创建连接池。。。");

        }
        System.out.println("创建连接池完毕。。。");
    }

    /**
     * 加载驱动程序
     */
    private void loadDrivers() {
        SourceManger pd = new SourceManger();
        //读取数据库配置文件
        this.drivers = pd.initConnect();
    }


}
