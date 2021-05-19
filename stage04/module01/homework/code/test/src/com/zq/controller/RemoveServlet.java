package com.zq.controller;

import com.alibaba.fastjson.JSON;
import com.zq.model.dao.StudentDaoImp;
import com.zq.model.service.ManagerService;

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
 * @Date: 2021/5/17 12:05
 * @Version: V1.0
 */
@WebServlet(name = "RemoveServlet", urlPatterns = "/remove")
public class RemoveServlet extends HttpServlet {
    private static final long serialVersionUID = -8468730456425348194L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先判断当前是否处于成功登陆状态, 不是的话就跳转到登录页面
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        if (!"success".equals(session.getAttribute("logIn"))) {
            session.setAttribute("logIn", "invalid");
            response.sendRedirect("login.jsp");
            return;
        }

        request.setCharacterEncoding("utf-8");
        System.out.println("removeIdList : " + request.getParameter("removeIdList"));
        String[] removeIdList = JSON.parseObject(request.getParameter("removeIdList"), String[].class);
        ManagerService managerService = null;
        try {
            managerService = new ManagerService(StudentDaoImp.getInstance());
            int removeAmount = removeIdList.length;
            int removeSuccessCount = 0;
            for (String studentId : removeIdList) {
                boolean removeResult = managerService.removeStudentById(studentId);
                removeSuccessCount += removeResult ? 1 : 0;
            }

            session.setAttribute("lastOperate", "remove");
            session.setAttribute("removeAmount", removeAmount);
            session.setAttribute("removeSuccessCount",removeSuccessCount);
            response.sendRedirect("show-all");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
