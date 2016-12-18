package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 11981 on 2016/12/10.
 */
public class DbConn {
    // JDBC 驱动器名称 和数据库地址
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL = "jdbc:mysql://localhost/shopping?" +
            "useUnicode=true&characterEncoding=utf-8&useSSL=false";
    //加这一行可以消除一个warning
    //warning:建立ssl连接，但是服务器没有身份认证，这种方式不推荐使用

    //  数据库用户和密码
    static final String USER = "root";

    static final String PASSWORD = "root";

    public static Connection getconn()
    {
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        }catch (SQLException se)
        {
            se.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}
