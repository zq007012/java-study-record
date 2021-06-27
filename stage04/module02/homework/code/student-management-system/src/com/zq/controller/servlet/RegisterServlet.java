package com.zq.controller.servlet;

import com.zq.model.factory.UserDaoFactory;
import com.zq.model.service.UserManagerService;
import com.zq.utils.EmptyUtils;

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
 * @Date: 2021/5/29 20:03
 * @Version: V1.0
 */
@WebServlet(name = "RegisterServlet", urlPatterns ="/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 8593631254092079306L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (EmptyUtils.isEmpty(userName) || EmptyUtils.isEmpty(password)){
            response.sendRedirect("register.jsp");
            return;
        }

        UserManagerService manager = null;
        try {
            manager = new UserManagerService(UserDaoFactory.newUserDao());
            boolean result = manager.registerUser(userName, password);
            if (result){
                //todo 注册成功,
                System.out.println("注册成功");
                request.getSession().setAttribute(WebConstant.REGISTER_RESULT,WebConstant.SUCCESS);
                response.sendRedirect("login.jsp");
            }else{
                request.setAttribute(WebConstant.REGISTER_RESULT,WebConstant.FAILED);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/register.jsp");
                requestDispatcher.forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
