package com.zq.task2.DbUtilsTest;

import com.zq.utils.CloseUtils;
import com.zq.utils.InputUtils;
import com.zq.dbconnpool.DruidPool;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @ClassName: TransactionDemo
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/9 18:10
 * @Version: V1.0
 */
public class TransactionDemo {
    @Test
    public void test(){
        Connection dbConnection = null;
        Scanner scanner = null;
        double transfer = 0;
        try {
            QueryRunner queryRunner = new QueryRunner();
            dbConnection = DruidPool.getInstance().getDbConnection();
            dbConnection.setAutoCommit(false);
            String sql1 = "update account set money = money - ? where aname = '路飞'";
            String sql2 =
                        "update account set money = money + ? where aname = '汉库克'";
            scanner = new Scanner(System.in);
            transfer = InputUtils.getDouble(
                    "'路飞'向'汉库克'转账多少钱(可以精确到小数点后两位)",scanner);

            queryRunner.update(dbConnection,sql1,transfer);
            queryRunner.update(dbConnection,sql2,transfer);

            //如果转账额度超过了余额, 就抛出异常
            if (transfer > getBalance("路飞", queryRunner, dbConnection)){
                throw new Exception("余额不足, 无法转账");
            }

            DbUtils.commitAndCloseQuietly(dbConnection);
            System.out.println("'路飞'向'汉库克'转账" + transfer + "元成功");
        } catch (Exception e) {
            DbUtils.rollbackAndCloseQuietly(dbConnection);
            System.out.println(e.getMessage());
        } finally {
            CloseUtils.closeResourcesQuietly(scanner);
        }


    }

    /**
     * 获取账户`name`的余额
     * @param name
     * @param queryRunner
     * @param dbConnection
     * @return
     */
    private double getBalance(String name, QueryRunner queryRunner, Connection dbConnection) throws SQLException {
        String sql = "select money from account where aname = ?";
        Object[] result = queryRunner.query(dbConnection, sql,
                new ArrayHandler(),name);
        return (double) result[0];
    }
}
