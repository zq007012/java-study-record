package com.zq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/8 10:22
 * @Version: V1.0
 */
@WebServlet(name = "CookUrlInUrl02Servlet", urlPatterns ="/url02/url/cookie")
public class CookUrlInUrl02Servlet extends HttpServlet {


    private static final long serialVersionUID = 7801322558967083926L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getPath());
        }
        Cookie url01 = new Cookie("urlInUrl02", "urlInUrl02-value");
        System.out.println(url01.getPath());
        response.addCookie(url01);
        System.out.println(url01.getPath());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
