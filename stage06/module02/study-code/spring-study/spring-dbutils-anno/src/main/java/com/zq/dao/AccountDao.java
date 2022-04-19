package com.zq.dao;

import com.zq.domain.Account;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/8 7:34
 */
public interface AccountDao {
    List<Account> findAll();

    Account findById(Integer accountId);

    int save(Account account);

    int update(Account account);

    int delete(Integer accountId);
}
