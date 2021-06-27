package com.zq.controller.servlet.student;

import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.factory.StudentDaoFactory;
import com.zq.model.javabean.Student;
import com.zq.model.service.ClassGradeManagerService;
import com.zq.model.service.StudentMangerService;
import com.zq.utils.UUIDUtils;

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
 * @Date: 2021/6/8 21:29
 * @Version: V1.0
 */
@WebServlet(name = "AddStudentServlet", urlPatterns ="/add-student")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 5168367989231783103L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        String number = request.getParameter(WebConstant.NUMBER);
        String name = request.getParameter(WebConstant.NAME);
        String classGradeId = request.getParameter(WebConstant.CLASS_GRADE_ID);
        String gender = request.getParameter(WebConstant.GENDER);
        String birthday = request.getParameter(WebConstant.BIRTHDAY);
        String email = request.getParameter(WebConstant.EMAIL);
        String notes = request.getParameter(WebConstant.NOTES);

        String notice = "";
        try {
            StudentMangerService stuMg = new StudentMangerService(StudentDaoFactory.newStudentDao());
            //用来标记已存在的学号
            notice = stuMg.getStudentByNumber(number) != null ? "已存在学号为'" + number + "'的学生" : notice;
            //用来标记已存在的学生姓名
            //notice = stuMg.getStudentByName(name) != null ? (notice.isEmpty() ? "已存在姓名为'" + name + "'的学生" : notice + "和姓名为'" + name + "'的学生") : notice;
            session.setAttribute(WebConstant.STUDENT_LAST_OPERATE,WebConstant.ADD);
            if (!notice.isEmpty()){
                addFailed(request,response,session,notice);
                return;
            }

            Student student = new Student();
            student.setStudentId(UUIDUtils.generateUUID());
            student.setNumber(number);
            student.setName(name);
            student.setGender(gender);
            student.setBirthday(birthday);
            student.setEmail(email);
            student.setNotes(notes);
            student.setClassGradeId(classGradeId);

            boolean result = stuMg.addStudent(student);
            if(result){
                ClassGradeManagerService cgMg = new ClassGradeManagerService(ClassGradeDaoFactory.newClassGradeDao());
                /*
                添加成功, 更新班级人数
                 */
                cgMg.updateClassSize(classGradeId);
                addSuccess(session,response);
            }else{
                notice = "数据库出错";
                addFailed(request,response,session,notice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void addFailed(HttpServletRequest request,
                           HttpServletResponse response,
                           HttpSession session,
                           String notice) throws ServletException, IOException {
        session.setAttribute(WebConstant.LAST_OPERATE_RESULT,
                WebConstant.FAILED);
        session.setAttribute(WebConstant.NOTICES,new String[]{"抱歉, 创建学生失败, " + notice});
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/add-student.jsp");
        requestDispatcher.forward(request,response);
    }

    private void addSuccess(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute(WebConstant.LAST_OPERATE_RESULT,
                WebConstant.SUCCESS);
        session.setAttribute(WebConstant.NOTICES, new String[]{"恭喜, 创建学生成功"});
        response.sendRedirect("show-all-student");
    }
}
