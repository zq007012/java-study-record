package com.zq.dao.impl;

import com.zq.dao.AccountDao;
import com.zq.domain.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/8 7:37
 */
@Repository("accountDao")
@NoArgsConstructor
public class AccountDaoImpl implements AccountDao {
    @Getter
    @Setter
    @Autowired
    @Qualifier("queryRunner")
    private QueryRunner queryRunner;

    @Override
    public List<Account> findAll() {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from account";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = getQueryRunner();
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Account> beanListHandler = new BeanListHandler<>(Account.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Account> query = null;
        try {
            query = queryRunner.query(sql, beanListHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return query;
    }

    @Override
    public Account findById(Integer accountId) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from account where id = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = getQueryRunner();
        // 3. 创建一个可以对下划线和驼峰命名进行转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanHandler<Account> beanHandler = new BeanHandler<>(Account.class, brp);
        //4. 执行查找并以JavaBean的形式获得结果
        Account query = null;
        try {
            query = queryRunner.query(sql, beanHandler,
                    accountId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return query;

    }

    @Override
    public int save(Account account) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "insert into account(name, money) values(?,?)";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    account.getName(), account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public int update(Account account) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update account set name = ?, money = ? where id = ?";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return result;
    }

    @Override
    public int delete(Integer accountId) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "delete from account where id = ?";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    accountId);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return result;
    }
}
