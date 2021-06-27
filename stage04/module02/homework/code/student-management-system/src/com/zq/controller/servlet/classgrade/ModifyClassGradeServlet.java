package com.zq.controller.servlet.classgrade;

import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.service.ClassGradeManagerService;

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
 * @Date: 2021/6/7 18:39
 * @Version: V1.0
 */
@WebServlet(name = "ModifyClassGradeServlet", urlPatterns = "/modify-class-grade")
public class ModifyClassGradeServlet extends HttpServlet {
    private static final long serialVersionUID = 4257845141772934292L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        String classGradeId = request.getParameter(WebConstant.CLASS_GRADE_ID);
        String grade = request.getParameter(WebConstant.GRADE).trim();
        String clazz = request.getParameter(WebConstant.CLAZZ).trim();
        String teacher = request.getParameter(WebConstant.TEACHER).trim();
        String watchwords = request.getParameter(WebConstant.WATCHWORDS).trim();
        String classGradeName = grade + "年级" + clazz + "班";
        String notice = "";

        try {
            ClassGradeManagerService manager = new ClassGradeManagerService(
                    ClassGradeDaoFactory.newClassGradeDao());
            //根据表单ID获取要进行编辑的班级对象
            ClassGrade oldClassGrade = manager.getClassGradeByClassGradeId(classGradeId);
            /*新的班级名与旧的班级名不相同, 并且可以根据新的班级名在数据库中查到对应的班级, 说明
            数据库中已经有了这个班级名, 不能使用这个班级名了*/
            notice = !classGradeName.equals(oldClassGrade.getClassGradeName()) &&
                    manager.getClassGradeByName(classGradeName) != null ? notice + "已存在名为'" + classGradeName + "'的班级" : notice;
            /*新的班主任姓名与旧的班主任姓名不相同, 并且可以根据新的班主任姓名在数据库中查到对应的班级, 说明
            数据库中已经有了这个班主任姓名, 不能使用这个班主任姓名了*/
            notice = !teacher.equals(oldClassGrade.getClassTeacher()) &&
                    manager.getClassGradeByTeacher(teacher) != null ? (notice.isEmpty() ? "已存在名为'" + teacher + "'的班主任" : notice + "和名为'" + teacher + "'的班主任") : notice;
            session.setAttribute(WebConstant.CLASS_GRADE_LAST_OPERATE, WebConstant.MODIFY_CLASS_GRADE);

            if (!notice.isEmpty()) {
                modifyFailed(request, response, session, notice);
                System.out.println(notice);
                return;
            }

            ClassGrade newClassGrade = new ClassGrade();
            newClassGrade.setClassGradeName(classGradeName);
            newClassGrade.setClassTeacher(teacher);
            newClassGrade.setWatchwords(watchwords);

            boolean modifyResult = manager.modifyClassGrade(oldClassGrade, newClassGrade);
            if (modifyResult) {
                modifySuccess(session, response);
            } else {
                notice = "数据库出现异常";
                modifyFailed(request, response, session, notice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void modifyFailed(HttpServletRequest request,
                              HttpServletResponse response,
                              HttpSession session,
                              String notice) throws ServletException, IOException {
        session.setAttribute(WebConstant.LAST_OPERATE_RESULT,
                WebConstant.FAILED);
        session.setAttribute(WebConstant.NOTICES, new String[]{"抱歉, 编辑班级失败, " + notice});
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/modify-class-grade.jsp");
        requestDispatcher.forward(request, response);
    }

    private void modifySuccess(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute(WebConstant.LAST_OPERATE_RESULT,
                WebConstant.SUCCESS);
        session.setAttribute(WebConstant.NOTICES, new String[]{"恭喜, 编辑班级信息成功"});
        response.sendRedirect("show-all-class-grade");
    }
}
