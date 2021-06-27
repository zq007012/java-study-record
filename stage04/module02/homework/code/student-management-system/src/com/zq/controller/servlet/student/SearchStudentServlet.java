package com.zq.controller.servlet.student;

import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.factory.StudentDaoFactory;
import com.zq.model.javabean.ClassGrade;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/6/9 14:23
 * @Version: V1.0
 */
@WebServlet(name = "SearchStudentServlet", urlPatterns ="/search-student")
public class SearchStudentServlet extends HttpServlet {
    private static final long serialVersionUID = -8541304327373849537L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        String keyword = request.getParameter(WebConstant.KEYWORD);
        try {
            ClassGradeManagerService cgMg = new ClassGradeManagerService(ClassGradeDaoFactory.newClassGradeDao());
            StudentMangerService stuMg = new StudentMangerService(StudentDaoFactory.newStudentDao());

            List<Student> studentList = stuMg.searchFuzzily(keyword);

            List<ClassGrade> cgList = cgMg.searchFuzzily(keyword);
            List<Student> studentListByCg = null;
            if (!EmptyUtils.isEmpty(cgList)){
                studentListByCg = new ArrayList<>();
                for (ClassGrade classGrade : cgList) {
                    List<Student> studentListByClassGrade = stuMg.getStudentListByClassGrade(classGrade);
                    studentListByCg.addAll(studentListByClassGrade);
                }

                studentList.removeAll(studentListByCg);
                studentList.addAll(studentListByCg);
            }

            for (Student student : studentList) {
                student.setClassGrade(cgMg.getClassGradeByStudent(student));
            }

            session.setAttribute(WebConstant.STUDENT_LAST_OPERATE,WebConstant.SEARCH);
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
