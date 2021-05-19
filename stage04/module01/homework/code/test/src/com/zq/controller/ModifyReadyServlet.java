package com.zq.controller;

import com.zq.model.dao.StudentDao;
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

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/13 13:48
 * @Version: V1.0
 */
@WebServlet(name = "ModifyReadyServlet", urlPatterns ="/modify-ready")
public class ModifyReadyServlet extends HttpServlet {
    private static final long serialVersionUID = 3984507721495971409L;

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
        String studentId = request.getParameter("studentId");
        ManagerService managerService = null;
        try {
            managerService = new ManagerService(StudentDaoImp.getInstance());
            Student student = managerService.getStudentById(studentId);
            if (null != student){
                request.setAttribute("student", student);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/modify-student.jsp");
                requestDispatcher.forward(request, response);
            }else{
                response.sendRedirect("show-all");
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
