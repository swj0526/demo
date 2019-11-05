package core.utils.database;

import sun.plugin.javascript.navig.Link;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    public static MyDataSource dataSource = new MyDataSource();

    public static List getList(String sql) {  //获取数据返回的信息列表
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            conn = dataSource.getConnection();
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<Map<String,Object>> list = new ArrayList();
            while (result.next()) {
                Map<String ,Object> rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(metaData.getColumnName(i), result.getObject(i));
                }
                list.add(rowData);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                state.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dataSource.releaseConnection(conn);
        }
       return null;
    }

    public static int update(String sql) {  //增,删,改数据的方法
        Connection conn = null;
        PreparedStatement state = null;
        int num = 0;
        try {
            conn = dataSource.getConnection();
            state = conn.prepareStatement(sql);
            num = state.executeUpdate();
           return  num;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dataSource.releaseConnection(conn);
        }
        return 0;
    }

    public static int add_returnId(String sql) {   //新增数据并返回新增数据的id
        Connection conn = null;
        ResultSet generatedKeys =null;
        PreparedStatement state = null;
        int id = 0;
        try {
            conn = dataSource.getConnection();
            state = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            state.executeUpdate();
            generatedKeys = state.getGeneratedKeys();
            while (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            System.out.println("id:"+id);
           return  id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                generatedKeys.close();
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dataSource.releaseConnection(conn);
        }
        return id;
    }

    public static int getCount(String tableName) {  //获取集合的数量
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        int num = 0;
        try {
            String sql ="SELECT  COUNT(*)  FROM  "+tableName ;
            conn = dataSource.getConnection();
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                num = result.getInt(1);
            }
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dataSource.releaseConnection(conn);
        }
        return num;
    }
}