package com.zq.controller.servlet;

import com.zq.model.factory.UserDaoFactory;
import com.zq.model.javabean.User;
import com.zq.model.service.UserManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: 退出学生管理系统, 会去除登录状态, 以及一周内免登录的状态, 然后跳转到登录界面
 * @Author: zq007
 * @Date: 2021/5/31 15:05
 * @Version: V1.0
 */
@WebServlet(name = "QuitServlet", urlPatterns = "/quit")
public class QuitServlet extends HttpServlet {
    private static final long serialVersionUID = 1838698026240054339L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //1. 移除登录状态
        session.removeAttribute(WebConstant.LOGIN_STATUS);
        UserManagerService manager = null;
        try {
            //2. 移除一周内免登陆的状态
            manager = new UserManagerService(UserDaoFactory.newUserDao());
            manager.removeLogInStatus((User)session.getAttribute(WebConstant.USER));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("移除登录状态以及数据库中一周内免登录的状态, 跳转到登录界面");
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
