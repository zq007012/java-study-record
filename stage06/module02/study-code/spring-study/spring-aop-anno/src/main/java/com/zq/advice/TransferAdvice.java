package com.zq.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/12 13:25
 */
@Component("transferAdvice")
@Aspect
public class TransferAdvice {
    @Pointcut("execution(void com.zq.service.impl.AccountServiceImpl.transfer(..))")
    public void myPointCout(){}

    @Before("TransferAdvice.myPointCout()")
    public void before(){
        System.out.println("前置通知-------------------");
    }

    public void afterReturing(){
        System.out.println("后置通知--------------------");
    }

    public void afterThrowing(){
        System.out.println("异常通知");
    }

    public void ending(){
        System.out.println("最终通知---------------------------");
    }

    public void around(ProceedingJoinPoint proceedingJoinPoint){
        try {
            System.out.println("转账前执行");
            proceedingJoinPoint.proceed();
            System.out.println("转账成功后执行");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("发生了异常后执行");
        }finally {
            System.out.println("无论如何都要执行");
        }
    }
}
