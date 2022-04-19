package com.zq;

import com.zq.proxy.CGLibProxyFactory;
import com.zq.proxy.JDKProxyFactory;
import com.zq.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 9:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountServiceTest {
    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;
    @Autowired
    private JDKProxyFactory jdkProxyFactory;
    @Autowired
    private CGLibProxyFactory cgLibProxyFactory;


    /**
     * 测试{@link AccountService#transfer(String, String, Double)}方法的功能
     */
    @Test
    public void transferTest() {
        accountService.transfer("tom", "jerry", 500d);
    }

    @Test
    public void transferJDKProxyTest() {
        jdkProxyFactory.createAccountServiceProxy(accountService).transfer("tom", "jerry", 1000d);
    }

    @Test
    public void transferCGLibProxyTest() {
        cgLibProxyFactory.createAccountServiceProxy(accountService).transfer("tom", "jerry", 1000d);
    }

}
