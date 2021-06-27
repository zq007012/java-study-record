package com.zq.controller.servlet.classgrade;

import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.service.ClassGradeManagerService;
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
 * @Date: 2021/6/2 9:18
 * @Version: V1.0
 */
@WebServlet(name = "AddClassGradeServlet", urlPatterns = "/add-class-grade")
public class AddClassGradeServlet extends HttpServlet {
    private static final long serialVersionUID = -7580415598284319404L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String grade = request.getParameter(WebConstant.GRADE).trim();
        String clazz = request.getParameter(WebConstant.CLAZZ).trim();
        String teacher = request.getParameter(WebConstant.TEACHER).trim();
        String watchwords = request.getParameter(WebConstant.WATCHWORDS).trim();

        String classGradeName = grade + "年级" + clazz + "班";
        ClassGrade classGrade = new ClassGrade();
        classGrade.setClassGradeId(UUIDUtils.generateUUID());
        classGrade.setClassGradeName(classGradeName);
        classGrade.setClassTeacher(teacher);
        classGrade.setClassSize(0);
        classGrade.setWatchwords(watchwords);

        HttpSession session = request.getSession();
        session.setAttribute(WebConstant.CLASS_GRADE_LAST_OPERATE,
                WebConstant.ADD);

        try {
            ClassGradeManagerService manager = new ClassGradeManagerService(
                    ClassGradeDaoFactory.newClassGradeDao());
            ClassGrade classGrade1 = manager.getClassGradeByName(classGradeName);
            ClassGrade classGrade2 = manager.getClassGradeByTeacher(teacher);
            String notice = classGrade1 == null ? "" : "已存在该班级名";
            notice = classGrade2 == null ? notice : (notice.isEmpty() ? "已存在该班主任" : notice + "和班主任");
            if (classGrade1 != null || classGrade2 != null){
                addFailed(request, response, session, notice);
                return;
            }

            boolean createResult = manager.createClassGrade(classGrade);
            if (createResult){
                addSuccess(session, response);
            }else{
                notice = "数据库出错";
                addFailed(request,response,session,notice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addFailed(HttpServletRequest request,
                           HttpServletResponse response,
                           HttpSession session,
                           String notice) throws ServletException, IOException {
        session.setAttribute(WebConstant.LAST_OPERATE_RESULT,
                WebConstant.FAILED);
        session.setAttribute(WebConstant.NOTICES,new String[]{"抱歉, 创建班级失败, " + notice});
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/add-class-grade.jsp");
        requestDispatcher.forward(request,response);
    }

    private void addSuccess(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute(WebConstant.LAST_OPERATE_RESULT,
                WebConstant.SUCCESS);
        session.setAttribute(WebConstant.NOTICES, new String[]{"恭喜, 创建班级成功"});
        response.sendRedirect("show-all-class-grade");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
