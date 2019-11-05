package core.utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyDataSource {
    //LinkedList集合，存储连接的容器---连接池
    private LinkedList<Connection> dataSources = new LinkedList();
    //初始化连接数量
    public MyDataSource() {
        //一次性创建10个连接
        for(int i = 0; i < 10; i++) {
            try {
                //1、装载驱动对象
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2、通过JDBC建立数据库连接
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/school?serverTimezone=UTC&characterEncoding=UTF-8", "root", "123456");
                //3、将连接加入连接池中
                dataSources.add(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Connection getConnection() throws SQLException {
        //取出连接池中一个连接
        final Connection conn = dataSources.removeFirst(); // 获取第一个连接给Connection，把剩下的连接返回给LinkedList集合
        return conn;
    }

    public void releaseConnection(Connection conn) {
        //把连接返还给连接池
        dataSources.add(conn);
    }




}
