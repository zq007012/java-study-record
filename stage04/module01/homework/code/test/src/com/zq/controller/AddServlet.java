package com.zq.controller;

import com.zq.model.dao.StudentDaoImp;
import com.zq.model.javabean.Student;
import com.zq.model.service.ManagerService;
import com.zq.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/11 21:56
 * @Version: V1.0
 */
@WebServlet(name = "AddServlet", urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = -6330499232605079993L;

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


        try {
            ManagerService managerService = new ManagerService(StudentDaoImp.getInstance());
            String number = request.getParameter("number");
            String name = request.getParameter("name");
            System.out.println(name);
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String email = request.getParameter("email");
            String notes = request.getParameter("notes");

            Student student = new Student();
            student.setStudentId(UUIDUtils.generateUUID());
            student.setNumber(number);
            student.setName(name);
            student.setGender(gender);
            student.setBirthday(birthday);
            student.setEmail(email);
            student.setNotes(notes);

            boolean addResult = managerService.addStudent(student);

            if (addResult) {
                // 添加学生成功
                System.out.println("添加成功");
                session.setAttribute("lastOperate", "add");
                session.setAttribute("result", "success");
                response.sendRedirect("show-all");
            } else {
                System.out.println("添加失败");
                request.setAttribute("addFailed", "failed");
                request.getRequestDispatcher("/add-student.jsp")
                        .forward(request, response);
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
