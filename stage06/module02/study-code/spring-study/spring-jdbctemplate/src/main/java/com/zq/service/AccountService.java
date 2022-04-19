package com.zq.service;

import com.zq.domain.Account;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/16 11:37
 */
public interface AccountService {
    List<Account> findAll();

    Account findById(Integer id);

    boolean save(Account account);

    boolean update(Account account);

    boolean delete(Integer id);

    /**
     * 进行转账操作
     * @param accountNameOfOut  转账人姓名
     * @param accountNameOfIn   收账人姓名
     * @param transferAmount    转账金额
     */
    void transfer(String accountNameOfOut, String accountNameOfIn, Double transferAmount);
}
