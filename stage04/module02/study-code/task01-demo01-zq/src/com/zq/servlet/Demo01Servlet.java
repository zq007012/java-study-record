package com.zq.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/19 21:27
 * @Version: V1.0
 */
@WebServlet(name = "Demo01Servlet", urlPatterns ="/demo01")
public class Demo01Servlet extends HttpServlet {
    private static final long serialVersionUID = 5259429526276139270L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //java.lang.IllegalStateException: 初始化上下文后无法设置初始化参数
        //this.getServletContext().setInitParameter("name","黑百合");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
