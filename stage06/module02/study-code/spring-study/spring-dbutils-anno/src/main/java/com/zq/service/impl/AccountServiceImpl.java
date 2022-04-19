package com.zq.service.impl;

import com.zq.dao.AccountDao;
import com.zq.domain.Account;
import com.zq.service.AccountService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/8 7:49
 */
@NoArgsConstructor
@Service("accountService")
public class AccountServiceImpl implements AccountService{
    @Getter
    @Setter
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return getAccountDao().findAll();
    }

    @Override
    public Account findById(Integer accountId) {
        return getAccountDao().findById(accountId);
    }

    @Override
    public boolean save(Account account) {
       return getAccountDao().save(account) == 1 ;
    }

    @Override
    public boolean update(Account account) {
        return getAccountDao().update(account) == 1;
    }

    @Override
    public boolean delete(Integer accountId) {
       return getAccountDao().delete(accountId) == 1;
    }
}
