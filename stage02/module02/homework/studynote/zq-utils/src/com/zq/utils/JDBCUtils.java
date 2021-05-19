package com.zq.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName: JDBCUtils
 * @Description: 这是一个JDBC的工具类
 * @Author: zq007
 * @Date: 2021/3/3 13:16
 * @Version: V1.0
 */
public class JDBCUtils {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/one_piece" +
            "?characterEncoding=utf-8";
    private static final String USER = "zq";
    private static final String PASSWORD = "111111";

    //不需要通过此种方式注册驱动了, 这个驱动会经由SIP自动注册
    // 1. 注册驱动
    /*static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    // 2. 与数据库建立连接
    public static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER,
                    PASSWORD);
    }


}
