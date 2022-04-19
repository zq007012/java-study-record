package com.zq;

import com.zq.conifg.SpringConfig;
import com.zq.domain.Account;
import com.zq.service.AccountService;
import lombok.ToString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/8 8:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value = "classpath:applicationContext.xml")
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest {
    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    /**
     * 测试{@link AccountService#findAll}方法的功能
     */
    @Test
    public void findAllTest() {
        List<Account> accountList = accountService.findAll();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    /**
     * 测试{@link AccountService#findById(Integer)}方法的功能
     */
    @Test
    public void findByIdTest() {
        Account account = accountService.findById(1);
        System.out.println(account);
    }

    /**
     * 测试{@link AccountService#save(Account)}方法的功能
     */
    @Test
    public void saveTest() {
        Account account = new Account();
        account.setName("穗乃果");
        account.setMoney(10000d);
        accountService.save(account);
    }

    /**
     * 测试{@link AccountService#update(Account)}方法的功能
     */
    @Test
    public void updateTest() {
        Account account = new Account();
        account.setId(3);
        account.setName("Dimitrescu");
        account.setMoney(11111.11d);
        accountService.update(account);

    }

    /**
     * 测试{@link AccountService#delete(Integer)}方法的功能
     */
    @Test
    public void deleteTest() {
        accountService.delete(3);
    }
}
