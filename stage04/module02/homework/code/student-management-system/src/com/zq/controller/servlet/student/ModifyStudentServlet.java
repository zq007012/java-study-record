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
 * @Date: 2021/6/9 16:07
 * @Version: V1.0
 */
@WebServlet(name = "ModifyStudentServlet", urlPatterns ="/modify-student")
public class ModifyStudentServlet extends HttpServlet {
    private static final long serialVersionUID = -5647561989149820141L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        String studentId = request.getParameter((WebConstant.STUDENT_ID));
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
            Student student = stuMg.getStudentByStudentId(studentId);

            System.out.println(number);
            System.out.println(studentId);
            System.out.println(student);
            //??????????????????????????????
            notice = !number.equals(student.getNumber()) && stuMg.getStudentByNumber(number) != null ? "??????????????????'" + number + "'?????????" : notice;
            //????????????????????????????????????
            //notice = !name.equals(student.getName()) && stuMg.getStudentByName(name) != null ? (notice.isEmpty() ? "??????????????????'" + name + "'?????????" : notice + "????????????'" + name + "'?????????") : notice;
            session.setAttribute(WebConstant.STUDENT_LAST_OPERATE,WebConstant.ADD);
            if (!notice.isEmpty()){
                addFailed(request,response,session,notice);
                return;
            }

            student.setNumber(number);
            student.setName(name);
            student.setGender(gender);
            student.setBirthday(birthday);
            student.setEmail(email);
            student.setNotes(notes);
            String oldClassGradeId = student.getClassGradeId().equals(classGradeId) ? null : student.getClassGradeId();
            student.setClassGradeId(classGradeId);

            boolean result = stuMg.modifyStudent(studentId,student);
            if(result){
                /*?????????????????????, ????????????????????????????????????, ????????????????????????*/
                ClassGradeManagerService cgMg = new ClassGradeManagerService(ClassGradeDaoFactory.newClassGradeDao());
                if (null != oldClassGradeId){
                    //?????????????????????
                    cgMg.updateClassSize(oldClassGradeId);
                    //?????????????????????
                    cgMg.updateClassSize(classGradeId);
                }
                addSuccess(session,response);
            }else{
                notice = "???????????????";
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
        session.setAttribute(WebConstant.NOTICES,new String[]{"??????, ??????????????????, " + notice});
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/modify-student.jsp");
        requestDispatcher.forward(request,response);
    }

    private void addSuccess(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute(WebConstant.LAST_OPERATE_RESULT,
                WebConstant.SUCCESS);
        session.setAttribute(WebConstant.NOTICES, new String[]{"??????, ??????????????????"});
        response.sendRedirect("show-all-student");
    }
}
