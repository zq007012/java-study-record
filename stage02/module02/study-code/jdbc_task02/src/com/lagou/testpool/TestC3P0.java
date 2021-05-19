package com.lagou.testpool;

import com.lagou.utils.C3P0Utils;

import java.sql.*;

public class TestC3P0 {

    //需求 查询姓名为李白的 记录
    public static void main(String[] args) throws SQLException {

        //1.获取连接
        Connection con = C3P0Utils.getConnection();

        //2.获取预处理对象
        String sql = "select * from employee where ename = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        //3.设置占位符的值
        ps.setString(1,"李白");
        ResultSet resultSet = ps.executeQuery();

        //4.处理结果集
        while(resultSet.next()){
            int eid = resultSet.getInt("eid");
            String ename = resultSet.getString("ename");
            int age = resultSet.getInt("age");
            String sex = resultSet.getString("sex");
            double salary = resultSet.getDouble("salary");
            Date date = resultSet.getDate("empdate");

            System.out.println(eid +" " + ename + " " + age +" " + sex +" " + salary +" " +date);
        }

        //5.释放资源
        C3P0Utils.close(con,ps,resultSet);
    }

}
