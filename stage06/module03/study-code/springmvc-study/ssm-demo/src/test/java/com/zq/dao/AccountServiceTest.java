package com.zq.dao;

import com.zq.domain.Account;
import com.zq.service.AccountService;
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
 * @date 2022/5/2 8:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AccountServiceTest {
    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    /**
     * 测试{@link AccountService#findAll()}方法的功能
     */
    @Test
    public void findAllTest(){
        accountService.findAll();
    }
}
