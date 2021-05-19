package com.zq.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/17 8:22
 * @Version: V1.0
 */
@WebServlet(name = "QuitServlet", urlPatterns = "/quit")
public class QuitServlet extends HttpServlet {
    private static final long serialVersionUID = -3068847604732282148L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("logIn", "invalid");
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
