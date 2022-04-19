package com.zq.advice;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 11:32
 */
@Component("myAdvice")
public class MyAdvice {
    public void before(){
        System.out.println("前置通知-----------------------");
    }
}
