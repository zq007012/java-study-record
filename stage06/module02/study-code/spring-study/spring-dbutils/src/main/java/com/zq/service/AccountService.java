package com.zq.service;

import com.zq.domain.Account;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/8 7:48
 */
public interface AccountService {
    List<Account> findAll();

    Account findById(Integer accountId);

    boolean save(Account account);

    boolean update(Account account);

    boolean delete(Integer accountId);
}
