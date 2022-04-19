package com.zq.dao.impl;

import com.zq.dao.AccountDao;
import com.zq.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/16 11:12
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        // language=MySQL
        String sql = "select * from account";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public Account findById(Integer accountId) {
        // language=MySQL
        String sql = "select * from account where id = ?";

        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Account.class),
                accountId);
    }

    @Override
    public int save(Account account) {
        //language=MySQL
        String sql = "insert into account(name,money) values(?,?)";
        return jdbcTemplate.update(sql, account.getName(), account.getMoney());
    }

    @Override
    public int update(Account account) {
        //language=MySQL
        String sql = "update account set name = ? , money = ? where id =?";
        return jdbcTemplate.update(sql, account.getName(), account.getMoney(), account.getId());
    }

    @Override
    public int delete(Integer id) {
        //language=MySQL
        String sql = "delete from account where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int transferOut(String accountNameOfOut, Double transferAmount) {
        //language=MySQL
        String sql = "update account set money = money - ? where name like ?";
        return jdbcTemplate.update(sql, transferAmount, accountNameOfOut);
    }

    @Override
    public int transferIn(String accountNameOfIn, Double transferAmount) {
        //language=MySQL
        String sql = "update account set money = money + ? where name like ?";
        return jdbcTemplate.update(sql, transferAmount, accountNameOfIn);
    }
}
