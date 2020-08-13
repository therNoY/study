package connect_pool.mysql;

import util.JsonUtil;

import java.io.*;
import java.util.Vector;

public class SourceManger {

    public Vector initConnect() {
        Vector vector = new Vector();
        String filePath = this.getClass().getClassLoader().getResource("connect.json").getFile();
        File file = new File(filePath);
//        vector.add(JsonUtil.fileToBean(file, ConnectSource.class));
        return vector;
    }


    public static void main(String[] args) {
        ConnectSource dsb = new ConnectSource();
        dsb.setType("oracle");
        dsb.setName("yyy004");
        dsb.setDriver("org.oracle.jdbc");
        dsb.setUrl("jdbc:oracle://localhost");
        dsb.setUsername("sa");
        dsb.setPassword("");
        dsb.setMaxConn(1000);

    }
}
