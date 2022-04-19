package com.zq;

import com.zq.dao.AccountDao;
import com.zq.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/16 12:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountDaoTest {
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    /**
     * 测试{@link AccountDao#findAll()}方法的功能
     */
    @Test
    public void findAllTest(){
        List<Account> all = accountDao.findAll();
        for (Account account : all) {
            System.out.println(account);
        }
    }

    /**
     * 测试{@link AccountDao#findById(Integer)}方法的功能
     */
    @Test
    public void findByIdTest(){
        System.out.println(accountDao.findById(1));
    }

    /**
     * 测试{@link AccountDao#save(Account)}方法的功能
     */
    @Test
    public void saveTest(){
        Account account = new Account();
        account.setName("穗乃果");
        account.setMoney(10000000000d);
        System.out.println(accountDao.save(account));
    }

    /**
     * 测试{@link AccountDao#update(Account)}方法的功能
     */
    @Test
    public void updateTest(){
        Account account = accountDao.findById(4);
        account.setMoney(22222222222222222d);
        System.out.println(accountDao.update(account));
    }

    /**
     * 测试{@link AccountDao#delete(Integer)}方法的功能
     */
    @Test
    public void deleteTest(){
        System.out.println(accountDao.delete(4));
    }

    /**
     * 测试{@link AccountDao#transferIn(String, Double)}方法的功能
     */
    @Test
    public void transferInTest(){
        System.out.println(accountDao.transferIn("tom",1000d));
    }

    /**
     * 测试{@link AccountDao#transferOut(String, Double)}方法的功能
     */
    @Test
    public void transferOutTest(){
        System.out.println(accountDao.transferOut("tom",1000d));

    }
}
