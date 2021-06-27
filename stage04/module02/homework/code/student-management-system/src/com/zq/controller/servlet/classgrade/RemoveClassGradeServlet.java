package com.zq.controller.servlet.classgrade;

import com.alibaba.fastjson.JSON;
import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.factory.StudentDaoFactory;
import com.zq.model.javabean.ClassGrade;
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

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/6/7 7:45
 * @Version: V1.0
 */
@WebServlet(name = "RemoveClassGradeServlet", urlPatterns = "/remove-class-grade")
public class RemoveClassGradeServlet extends HttpServlet {
    private static final long serialVersionUID = -1569299535406751008L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String idArrStr = request.getParameter(WebConstant.REMOVED_CLASS_GRADE_ID_ARR);
        String[] idArr = JSON.parseObject(idArrStr, String[].class);

        try {
            ClassGradeManagerService classGradeManager = new ClassGradeManagerService(
                    ClassGradeDaoFactory.newClassGradeDao());
            StudentMangerService studentManager = new StudentMangerService(
                    StudentDaoFactory.newStudentDao());
            String[] notices = new String[idArr.length];
            for (int i = 0; i < idArr.length; i++) {
                String id = idArr[i];
                ClassGrade classGrade = classGradeManager.getClassGradeByClassGradeId(id);
                classGrade.setStudentList(studentManager.getStudentListByClassGrade(classGrade));
                if (!EmptyUtils.isEmpty(classGrade.getStudentList())) {
                    notices[i] = classGrade.getClassGradeName() + "有" + classGrade.getStudentList().size() + "个学生, 请移除这些学生后再删除该班级";
                } else {
                    boolean result = classGradeManager.deleteClassGradeByClassGradeId(id);
                    notices[i] = result ? classGrade.getClassGradeName() + "删除成功" :
                            "数据库出错, 导致删除'" + classGrade.getClassGradeName() +
                                    "'失败";
                }
            }

            HttpSession session = request.getSession();
            session.setAttribute(WebConstant.CLASS_GRADE_LAST_OPERATE, WebConstant.REMOVED);
            session.setAttribute(WebConstant.LAST_OPERATE_RESULT, WebConstant.SUCCESS);
            session.setAttribute(WebConstant.NOTICES,notices);
            response.sendRedirect("show-all-class-grade");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
