package com.zq.controller.servlet;

import com.zq.model.dao.UserDaoImp;
import com.zq.model.factory.UserDaoFactory;
import com.zq.model.javabean.User;
import com.zq.model.service.UserManagerService;
import com.zq.utils.EmptyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/29 9:28
 * @Version: V1.0
 */
@WebServlet(name = "VerifyLogInServlet", urlPatterns ="/verify-login")
public class VerifyLogInServlet extends HttpServlet {
    private static final long serialVersionUID = 6462021565318563581L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        // 账号和密码为空, 直接重定向到登录界面
        if (EmptyUtils.isEmpty(userName) || EmptyUtils.isEmpty(password)){
            response.sendRedirect("login.jsp");
            return;
        }

        UserManagerService manager = null;
        try {
            manager = new UserManagerService(UserDaoFactory.newUserDao());
            User user = manager.logInVerify(userName, password);
            HttpSession session = request.getSession();
            if (null == user){
                System.out.println("账号或密码错误, 重定向到登录界面");
                user = new User();
                user.setUserName(userName);
                user.setPassword(password);
                session.setAttribute(WebConstant.LOGIN_STATUS, WebConstant.FAILED);
                session.setAttribute(WebConstant.USER,user);
                response.sendRedirect("login.jsp");
            }else {
                System.out.println("账号和密码正确, 重定向到主页");
                if(!EmptyUtils.isEmpty(request.getParameter("absolveAWeek"))){
                    String sessionId = session.getId();
                    boolean storeResult = manager.storeLoggedInStatus(user, sessionId);
                    if (storeResult){
                        Cookie cookie = new Cookie("sessionId", sessionId);
                        cookie.setMaxAge(7 * 24 * 3600);
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                    System.out.println("勾选了7天内免登录, 更新客户端cookie中的seesion为" + sessionId + ", 将7天内免登陆的状态保存到服务器中");
                }
                session.setAttribute(WebConstant.LOGIN_STATUS, WebConstant.SUCCESS);
                session.setAttribute(WebConstant.USER,user);
                response.sendRedirect("index.jsp");
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
