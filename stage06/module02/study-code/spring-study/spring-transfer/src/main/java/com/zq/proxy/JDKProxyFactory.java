package com.zq.proxy;

import com.zq.service.AccountService;
import com.zq.manager.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.SocketTimeoutException;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 13:58
 */
@Component
public class JDKProxyFactory {
    @Autowired
    private TransactionManager transactionManager;

    public AccountService createAccountServiceProxy(AccountService accountService) {
        AccountService accountServiceProxy =  (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("transfer".equals(method.getName())) {
                            transactionManager.begin();
                            try {
                                Object obj = method.invoke(accountService, args);
                                transactionManager.commit();
                                return obj;
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                e.printStackTrace();
                                transactionManager.rollback();
                                return null;
                            } finally {
                                transactionManager.release();
                            }
                        } else {
                            Class<?> returnType = method.getReturnType();
                            return method.invoke(accountService, args);
                        }
                    }
                });
        return accountServiceProxy;
    }
}
