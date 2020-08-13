package design_patterns.structure_type.exterior;

/**
 * 客户端操作的门面接口
 */
public class Facade {

    MysqlCli mysqlCli = new MysqlCli();
    OracleCli oracleCli = new OracleCli();

    public void connect(String type) {

        if (type.equals("mysql")) {
            mysqlCli.connect();
            return;
        }else if (type.equals("oracle")) {
            oracleCli.connect();
            return;
        }

    }
}
