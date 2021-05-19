package com.zq.task1;

import com.zq.utils.CloseUtils;
import com.zq.utils.InputUtils;
import com.zq.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Scanner;

/**
 * @ClassName: JDBCDemo2
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/6 17:14
 * @Version: V1.0
 */
public class JDBCDemo2 {
    @Test
    public void sqlInjectionTest(){
        Scanner scanner = new Scanner(System.in);
        String account = InputUtils.getString("请输入账户:",scanner);
        String password = InputUtils.getString("请输入密码:",scanner);
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            dbConnection = JDBCUtils.getDBConnection();

            statement = dbConnection.createStatement();
            String sql = "select * from user where uname = '" + account + "' &&" +
                    " upassword = '" + password + "';";

            resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                System.out.println("账号: " + account + "\t密码: " + password +
                        "\t登录成功");
            }else{
                System.out.println("账号: " + account + "\t密码: " + password +
                        "\t登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                CloseUtils.closeResources(resultSet,statement,dbConnection,
                        scanner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void avoidSqlInjection(){
        Scanner scanner = new Scanner(System.in);
        String account = InputUtils.getString("请输入账户:",scanner);
        String password = InputUtils.getString("请输入密码:",scanner);

        Connection dbConnection = null;
        PreparedStatement preparedStatement =
                null;
        ResultSet resultSet = null;
        try {
            dbConnection = JDBCUtils.getDBConnection();
            String sql = "select * from user where uname = ? && upassword = ?;";
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("账号: " + account + "\t密码: " + password +
                        "\t登录成功");
            }else{
                System.out.println("账号: " + account + "\t密码: " + password +
                        "\t登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseUtils.closeResources(resultSet, preparedStatement, dbConnection,scanner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
