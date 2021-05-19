package com.zq.task2.DbUtilsTest;

import com.zq.dbconnpool.DruidPool;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: UpdateDemo
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/8 13:31
 * @Version: V1.0
 */
public class UpdateDemo {
    /**
     * 手动模式实现添加数据
     *
     * @throws SQLException
     */
    @Test
    public void insertCreate() throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into pirate values(?,?,?,?,?,?,?)";
        //添加一条数据
        Object[] params = new Object[]{null, "薇薇", 19, "女", "2001.6.1", 10, 1};
        Connection dbConnection = DruidPool.getInstance().getDbConnection();
        int updateRows1 = queryRunner.update(dbConnection, sql, params);
        System.out.println("updateRows1 = " + updateRows1);
        //在添加一条数据
        int updateRows2 = queryRunner.update(DruidPool.getInstance().getDbConnection(),
                sql, null, "瑞贝卡", 17, "女", "2003.8.1", 0, 1);
        System.out.println("updateRows2 = " + updateRows2);

        //关闭资源
        DbUtils.closeQuietly(dbConnection);
    }

    /**
     * 自动模式修改记录
     * @throws SQLException
     */
    @Test
    public void updateTest() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "update pirate set reward = ? where pname = ?";
        int updateRows = queryRunner.update(sql, 0, "薇薇");
        System.out.println("更新了" + updateRows + "行记录");
    }

    /**
     * 自动模式删除记录
     * @throws SQLException
     */
    @Test
    public void deleteTest() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "delete from pirate where pname in(?, ?)";
        int deleteRows = queryRunner.update(sql, "薇薇","瑞贝卡");
        System.out.println("删除了" + deleteRows + "行记录");
    }

    @Test
    public void transactonTest() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());

    }
}
