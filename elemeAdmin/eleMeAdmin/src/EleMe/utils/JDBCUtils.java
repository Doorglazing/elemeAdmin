package EleMe.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * Druid连接池工具类
 *
 *
 */
public final class JDBCUtils {
    //构造器只能给非静态的成员变量赋值
    // 定义成员变量datasource， 可以切换不同的连接池
    private static DataSource ds;

    //初始化配置
    static {
        try {
            // 1.加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.propeties"));
            ds =  DruidDataSourceFactory.createDataSource(pro);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    /**
     * 释放资源
     */
    public static void close(Statement statement, Connection conn){
        close(null, statement, conn);
    }

    public static void close(ResultSet rs,Statement statement, Connection conn){
        // 释放资源
        if(statement != null){
            try {
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     *  获取连接池方法
     */
    public static DataSource getDataSource(){
        return ds;
    }
}

