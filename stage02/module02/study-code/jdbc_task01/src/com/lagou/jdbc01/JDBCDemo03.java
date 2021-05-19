package com.lagou.jdbc01;

import java.sql.*;

public class JDBCDemo03 {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //1.注册驱动 省略
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "root", "123456");

            //3.获取语句执行对象
            statement = connection.createStatement();

            //4.执行SQL
            String sql = "select * from jdbc_user";
            resultSet = statement.executeQuery(sql);

            //5.处理结果集对象

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            //finally 中的代码始终会执行
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
