package com.lagou.utils;

import java.sql.*;

/**
 * JDBC工具类
 */
public class JDBCUtils {

    //1. 将连接信息定义为 字符串常量
    public static final String DRIVERNAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/db4?characterEncoding=UTF-8";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    //2.静态代码块
    static{
        try {
            //1.注册驱动
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //3.获取连接的 静态方法
    public static Connection getConnection(){
        try {
            //获取连接对象 并返回
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //4.关闭资源的方法
    public static void close(Connection con, Statement statement){

        if(con != null && statement != null){

            try {
                statement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con, Statement statement, ResultSet resultSet){

        if(con != null && statement != null){

            try {
	resultSet.close();
                statement.close();
                con.close();              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
