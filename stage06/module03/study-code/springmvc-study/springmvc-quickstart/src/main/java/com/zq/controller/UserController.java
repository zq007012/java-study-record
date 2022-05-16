package com.zq.controller;

import com.zq.domain.Account;
import com.zq.domain.ListDemo;
import com.zq.domain.MapDemo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 17:39
 */
@Controller("userController")
@RequestMapping("/user")
@SessionAttributes({"role"})
public class UserController {

    @RequestMapping("/quick")
    public String quick() {
        System.out.println("quick方法运行了-------------------");
        return "/success.jsp";
    }

    @RequestMapping(value = "/demo01", method = RequestMethod.GET, params = {"accountName", "money!=100"})
    public String demo1() {
        System.out.println("demo1方法运行了");
        return "success";
    }

    @RequestMapping("/simpleParams")
    public String simpleParams(String username, Integer money) {
        System.out.println("username: " + username);
        System.out.println("monney: " + money);
        return "success";
    }

    @RequestMapping("/simpleParamsByPost")
    public String simpleParamsByPost(String username, Integer money) {
        System.out.println("username: " + username);
        System.out.println("monney: " + money);
        return "success";
    }

    @RequestMapping("/pojoParams")
    public String pojoParams(Account account) {
        System.out.println("account.name: " + account.getName());
        System.out.println("account.money: " + account.getMoney());
        return "success";
    }

    @RequestMapping("/arrayParams")
    public String arrayParams(Integer[] ids) {
        for (Integer id : ids) {
            System.out.println(id);
        }
        return "success";
    }

    @RequestMapping("/listParams")
    public String listParams(ListDemo listDemo) {
        List<Account> accountList = listDemo.getAccountList();
        for (Account account : accountList) {
            System.out.println(account);
        }
        return "success";
    }

    @RequestMapping("/mapParams")
    public String mapParams(MapDemo mapDemo) {
        Map<String, Account> accountMap = mapDemo.getAccountMap();
        Set<Map.Entry<String, Account>> entries = accountMap.entrySet();
        for (Map.Entry<String, Account> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return "success";
    }

    @RequestMapping("/dateParams")
    public String mapParams(Date birthday) {
        System.out.println(birthday.toString());
        return "success";
    }

    @RequestMapping("/requestParamAnnoDemo")
    public String requestParamAnnoDemo(@RequestParam(name = "name", defaultValue = "蒂米特雷斯库") String username) {
        System.out.println("用户名是: " + username);
        return "success";
    }

    @RequestMapping("/requestHeaderAnnoDemo")
    public String requestHeaderAnnoDemo(@RequestHeader("cookie") String cookie, @RequestHeader("accept") String accept) {
        System.out.println("cookie是: " + cookie);
        System.out.println("accept是: " + accept);
        return "success";
    }

    @RequestMapping("/cookieValueAnnoDemo")
    public String cookieValueAnnoDemo(@CookieValue("JSESSIONID") String jsessionId) {
        System.out.println("jsessionId是: " + jsessionId);
        return "success";
    }

    @RequestMapping("/servletApiDemo")
    public String servletApiDemo(HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpSession session) {
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
        return "success";
    }

    @RequestMapping("/redirectDemo")
    public String redirectDemo() {
        return "redirect:/pages/success.jsp";
    }

    @RequestMapping("/forwardOfVoid")
    public void forwardOfVoid(HttpServletRequest request,
                              HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/pages/success.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/redirectOfVoid")
    public void redirectOfVoid(HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + "/pages/success.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/writeData")
    public void writeData(HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("欢迎欢迎---------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/modelAndViewDemo2")
    public ModelAndView modelAndViewDemo1(ModelAndView modelAndView){
        modelAndView.addObject("role", "穗乃果");
        modelAndView.setViewName("redirect:/pages/success.jsp");
        return modelAndView;
    }
}