package com.lagou.testpool;

import com.lagou.utils.DBCPUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBCP {

    /*
    * 测试DBCP连接池
    * */
    public static void main(String[] args) throws SQLException {

        //1.从DBCP连接池中拿到连接
        Connection con = DBCPUtils.getConnection();

        //2.获取Statement对象
        Statement statement = con.createStatement();

        //3.查询所有员工的姓名
        String sql = "select ename from employee";
        ResultSet resultSet = statement.executeQuery(sql);

        //4.处理结果集
        while(resultSet.next()){
            String ename = resultSet.getString("ename");
            System.out.println("员工姓名: " + ename);
        }

        //5.释放资源
        DBCPUtils.close(con,statement,resultSet);
    }

}
