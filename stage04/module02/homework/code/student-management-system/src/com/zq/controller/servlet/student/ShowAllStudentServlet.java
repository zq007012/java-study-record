package com.zq.controller.servlet.student;

import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.factory.StudentDaoFactory;
import com.zq.model.javabean.Student;
import com.zq.model.service.ClassGradeManagerService;
import com.zq.model.service.StudentMangerService;
import com.zq.utils.EmptyUtils;

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
 * @Date: 2021/6/8 13:58
 * @Version: V1.0
 */
@WebServlet(name = "ShowAllStudentServlet", urlPatterns ="/show-all-student")
public class ShowAllStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 3219265245102950699L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        try {
            StudentMangerService stuMg = new StudentMangerService(StudentDaoFactory.newStudentDao());
            ClassGradeManagerService cgMg = new ClassGradeManagerService(ClassGradeDaoFactory.newClassGradeDao());
            List<Student> studentList = stuMg.getAllStudents();
            if (!EmptyUtils.isEmpty(studentList)){
                for (Student student : studentList) {
                    student.setClassGrade(cgMg.getClassGradeByStudent(student));
                }
            }

            session.setAttribute(WebConstant.STUDENT_LAST_OPERATE,WebConstant.SHOW_ALL);
            session.setAttribute(WebConstant.LAST_OPERATE_RESULT,WebConstant.SUCCESS);
            session.setAttribute(WebConstant.STUDENT_LIST,studentList);
            response.sendRedirect("student-system.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
