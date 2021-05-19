package com.zq.task1;

import com.zq.utils.CloseUtils;
import com.zq.utils.InputUtils;
import com.zq.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @ClassName: JDBCDemo3
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/6 17:22
 * @Version: V1.0
 */
public class JDBCDemo3 {
    @Test
    public void transactionTest(){
        Scanner scanner = null;
        Connection dbConnection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            //1. 与数据库建立连接,并开启事务
            dbConnection = JDBCUtils.getDBConnection();
            dbConnection.setAutoCommit(false);

            //2. 创建两个sql语句的预处理执行平台, 一个用来减, 一个用来加
            String sql1 = "update account set money = money - ? where aname =" +
                    " " +
                    "'路飞';";
            preparedStatement1 = dbConnection.prepareStatement(sql1);

            String sql2 =
                    "update account set money = money + ? where aname = " +
                            "'汉库克';";
            preparedStatement2 = dbConnection.prepareStatement(sql2);

            //获取用户想转账多少钱
            scanner = new Scanner(System.in);
            double transfer = InputUtils.getDouble(
                    "'路飞'向'汉库克'转账多少钱?", scanner);

            preparedStatement1.setDouble(1, transfer);
            preparedStatement2.setDouble(1, transfer);
            //3. 执行转账
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();

            //除0操作,可以产生异常导致事务失败
            /*int a = 1 / 0;*/
            //4. 转账成功, 提交事务
            dbConnection.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            try {
                if (null != dbConnection) {
                    //5. 转账失败, 回滚事务
                    dbConnection.rollback();
                    System.out.println("转账失败, 进行回滚");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                CloseUtils.closeResources(preparedStatement2,
                        preparedStatement1,
                        dbConnection, scanner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
