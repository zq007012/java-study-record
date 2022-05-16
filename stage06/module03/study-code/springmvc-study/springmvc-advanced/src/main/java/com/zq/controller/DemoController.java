package com.zq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/8 18:51
 */
@Controller("demoController")
public class DemoController {
    @RequestMapping("/demo")
    public ModelAndView demo(HttpServletRequest request,ModelAndView modelAndView){
        System.out.println("handler的方法体-----------------");
        request.setAttribute("name","蒂法");
        modelAndView.setViewName("success");
        return modelAndView.addObject("name","贝优妮塔");
    }
}
