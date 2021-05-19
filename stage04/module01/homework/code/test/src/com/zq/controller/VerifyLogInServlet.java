package com.zq.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/9 22:44
 * @Version: V1.0
 */
@WebServlet(name = "VerifyLogInServlet", urlPatterns = "/verify")
public class VerifyLogInServlet extends HttpServlet {
    private static final long serialVersionUID = -2391418959688575651L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("success".equals(request.getSession().getAttribute("logIn"))) {
            response.sendRedirect("show-all");
            return;
        }

        String account = request.getParameter("account");
        System.out.println("account: " + account);
        String password = request.getParameter("password");
        System.out.println("password: " + password);
        if(null == account || null==password){
            response.sendRedirect("login.jsp");
        }

        if ("admin".equals(account) && "123456".equals(password)){
            System.out.println("登录成功");
            request.getSession().setAttribute("logIn","success");
            response.sendRedirect("show-all");
        }else{
            System.out.println("登录失败");
            request.getSession().setAttribute("logIn","failed");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
