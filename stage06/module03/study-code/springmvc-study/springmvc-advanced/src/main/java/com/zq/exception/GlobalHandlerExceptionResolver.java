package com.zq.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/4 18:45
 */
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        response.setStatus(500);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg", ex.getMessage());
        modelAndView.addObject("errorStackTrace",ex.getStackTrace());
        modelAndView.setViewName("error");
        System.out.println(response.getStatus());
        return modelAndView;
    }
}
