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

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/17 10:33
 * @Version: V1.0
 */
@WebServlet(name = "ModifyServlet", urlPatterns = "/modify")
public class ModifyServlet extends HttpServlet {
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
        Student student = new Student();
        student.setStudentId(request.getParameter("studentId"));
        student.setNumber(request.getParameter("number"));
        student.setName(request.getParameter("name"));
        student.setGender(request.getParameter("gender"));
        student.setBirthday(request.getParameter("birthday"));
        student.setEmail(request.getParameter("email"));
        student.setNotes(request.getParameter("notes"));

        ManagerService managerService = null;
        try {
            managerService = new ManagerService(StudentDaoImp.getInstance());
            boolean modifyResult = managerService.modifyStudent(student);
            if (modifyResult){
                session.setAttribute("lastOperate", "modify");
                response.sendRedirect("show-all");
            }else{
                request.setAttribute("student",student);
                request.setAttribute("modifyFailed", true);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/modify-student.jsp");
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
