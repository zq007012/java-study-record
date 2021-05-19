package com.zq.controller;

import com.zq.model.dao.StudentDaoImp;
import com.zq.model.javabean.Student;
import com.zq.model.service.ManagerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/11 12:48
 * @Version: V1.0
 */
@WebServlet(name = "ShowAllServlet", urlPatterns = "/show-all")
public class ShowAllServlet extends HttpServlet {
    private static final long serialVersionUID = -2625950820200954066L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先判断当前是否处于成功登陆状态, 不是的话就跳转到登录页面
        HttpSession session = request.getSession();
        if (!"success".equals(session.getAttribute("logIn"))) {
            session.setAttribute("logIn", "invalid");
            response.sendRedirect("login.jsp");
            return;
        }

        ManagerService managerService;
        List<Student> studentList;
        try {
            managerService = new ManagerService(StudentDaoImp.getInstance());
            studentList = managerService.getAllStudent();
            request.getSession().setAttribute("studentList", studentList);
            response.sendRedirect("student-system.jsp?indexOfPage=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
