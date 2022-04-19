package com.zq.service.impl;

import com.zq.dao.AccountDao;
import com.zq.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 9:32
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String inUser, String outUser, Double amount) {
        accountDao.transferIn(inUser, amount);
        int i = 1 / 0;
        accountDao.transferOut(outUser, amount);
    }
}
