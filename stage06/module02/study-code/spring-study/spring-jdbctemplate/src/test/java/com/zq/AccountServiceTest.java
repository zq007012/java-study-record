package com.zq;

import com.zq.config.SpringConfig;
import com.zq.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/17 10:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:applicationContext.xml")
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest {
    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;
    /**
     * 测试{@link AccountService#transfer(String, String, Double)}方法的功能
     */
    @Test
    public void transferTest(){
        accountService.transfer("tom", "jerry", 500d);
    }
}
