package com.zq.dao;

import com.zq.domain.Account;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/16 11:09
 */
public interface AccountDao {

    List<Account> findAll();

    Account findById(Integer id);

    int save(Account account);

    int update(Account account);

    int delete(Integer id);

    int transferOut(String accountNameOfOut, Double transferAmount);

    int transferIn(String accountNameOfIn, Double transferAmount);

}
