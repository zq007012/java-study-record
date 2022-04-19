package com.zq.service.impl;

import com.zq.dao.AccountDao;
import com.zq.domain.Account;
import com.zq.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/16 11:38
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public boolean save(Account account) {
        return accountDao.save(account) >= 1;
    }

    @Override
    public boolean update(Account account) {
        return accountDao.update(account) >= 1;
    }

    @Override
    public boolean delete(Integer id) {
        return accountDao.delete(id) >= 1;
    }

    /**
     * 进行转账操作
     *
     * @param accountNameOfOut 转账人姓名
     * @param accountNameOfIn  收账人姓名
     * @param transferAmount   转账金额
     */
    @Transactional(
            transactionManager = "platformTransactionManager",
            readOnly = false,
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = -1)
    @Override
    public void transfer(String accountNameOfOut, String accountNameOfIn, Double transferAmount) {
        accountDao.transferOut(accountNameOfOut, transferAmount);
        //int i = 1 / 0;
        accountDao.transferIn(accountNameOfIn, transferAmount);
    }

 /*   @Autowired
    @Qualifier("datasource")
    private DataSource dataSource;
    *//**
     * 进行转账操作
     * @param accountNameOfOut  转账人姓名
     * @param accountNameOfIn   收账人姓名
     * @param transferAmount    转账金额
     * @return true表示转账成功, false表示转账失败
     *//*
    @Override
    public boolean transfer(String accountNameOfOut, String accountNameOfIn, Double transferAmount) {
        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);

        DefaultTransactionDefinition txdefinition = new DefaultTransactionDefinition();
        txdefinition.setReadOnly(false);
        txdefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txdefinition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        txdefinition.setTimeout(-1);

        TransactionStatus txStatus = txManager.getTransaction(txdefinition);

        try {
            accountDao.transferOut(accountNameOfOut,transferAmount);
            //int i = 1 / 0;
            accountDao.transferIn(accountNameOfIn, transferAmount);
            txManager.commit(txStatus);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            txManager.rollback(txStatus);
            return false;
        }
    }*/
}
