package com.zq;

import com.zq.domain.Account;
import com.zq.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/8 8:05
 */
public class AccountServiceTest {
    private ClassPathXmlApplicationContext appContext;

    @Before
    public void init() {
        appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @After
    public void ending(){
        appContext.close();
    }

    /**
     * 测试{@link AccountService#findAll}方法的功能
     */
    @Test
    public void findAllTest() {
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
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
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
        Account account = accountService.findById(1);
        System.out.println(account);
    }

    /**
     * 测试{@link AccountService#save(Account)}方法的功能
     */
    @Test
    public void saveTest(){
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
        Account account = new Account();
        account.setName("穗乃果");
        account.setMoney(10000d);
        accountService.save(account);
    }

    /**
     * 测试{@link AccountService#update(Account)}方法的功能
     */
    @Test
    public void updateTest(){
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
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
    public void deleteTest(){
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
        accountService.delete(3);
    }
}
