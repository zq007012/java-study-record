package com.zq.advice;

import com.zq.utils.ConnectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/16 5:47
 */
@Component("transferAdvice")
@Aspect
public class TransferAdvice {
    @Autowired
    @Qualifier("connectionUtils")
    private ConnectionUtils connectionUtils;

    @Around("execution(void com.zq.service.impl.AccountServiceImpl.transfer(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Connection connection = connectionUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            proceedingJoinPoint.proceed();
            connection.commit();
        } catch (Throwable throwable) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throwable.printStackTrace();

        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
                connectionUtils.removeConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    /*public void beforeTransfer(){
        try {
            connectionUtils.getConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void afterReturningTransfer(){
        try {
            connectionUtils.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void afteThrowingTransfer(){
        try {
            connectionUtils.getConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void afterTransfer(){
        try {
            connectionUtils.getConnection().close();
            connectionUtils.removeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
}
