package com.zq.dao.impl;

import com.zq.dao.AccountDao;
import com.zq.utils.ConnectionUtils;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 9:22
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private ConnectionUtils connectionUtils;

    @Getter
    @Autowired
    @Qualifier("queryRunner")
    private QueryRunner queryRunner;

    @Override
    public void transferIn(String inUser, Double amount) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update account set money = money + ? where name = ?";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(
                    connectionUtils.getConnection(),
                    sql,
                    amount,
                    inUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void transferOut(String outUser, Double amount) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update account set money = money - ? where name = ?";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(
                    connectionUtils.getConnection(),
                    sql,
                    amount,
                    outUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
