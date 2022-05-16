package com.zq.service.impl;

import com.zq.dao.AccountDao;
import com.zq.domain.Account;
import com.zq.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/2 8:42
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(Integer id) {
        accountDao.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteBatch(Integer[] ids) {
        if (ids == null || ids.length == 0) return;
        accountDao.deleteBatch(ids);
    }


}
