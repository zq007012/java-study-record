package com.zq;

import com.zq.advice.TransferAdvice;
import com.zq.config.SpringConfig;
import com.zq.service.AccountService;
import com.zq.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 9:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest {
    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    @Autowired
    @Qualifier("springConfig")
    private SpringConfig springConfig;

    @Test
    public void transferTest() {
        accountService.transfer();
    }

}
