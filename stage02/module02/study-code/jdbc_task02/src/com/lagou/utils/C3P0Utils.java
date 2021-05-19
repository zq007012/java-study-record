package com.lagou.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Utils {

    //1.创建连接池对象 C3P0对DataSource接口的实现类
    //使用的配置是 配置文件中的默认配置
    //public static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    //使用指定的配置
    public static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

    //获取连接的方法
    public static Connection getConnection() throws SQLException {

        return dataSource.getConnection();
    }

    //释放资源
    public static void close(Connection con, Statement statement){


        if(con != null && statement != null){
            try {
                statement.close();
                //归还连接
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close(Connection con, Statement statement, ResultSet resultSet){

        if(con != null && statement != null && resultSet != null){
            try {
                resultSet.close();
                statement.close();
                //归还连接
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
