package com.zq.proxy;

import com.zq.service.AccountService;
import com.zq.manager.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 18:46
 */
@Component
public class CGLibProxyFactory {
    @Autowired
    private TransactionManager transactionManager;
    @Autowired
    private AccountService accountService;

    public AccountService createAccountServiceProxy(AccountService accountService) {
        return (AccountService) Enhancer.create(accountService.getClass(),
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        if ("transfer".equals(method.getName())){
                            transactionManager.begin();
                            try {
                                Object obj = method.invoke(accountService, objects);
                                transactionManager.commit();
                                return obj;
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                e.printStackTrace();
                                transactionManager.rollback();
                                return null;
                            } finally {
                                transactionManager.release();
                            }
                        }else{
                            return method.invoke(accountService,objects);
                        }
                    }
                });
    }
}
