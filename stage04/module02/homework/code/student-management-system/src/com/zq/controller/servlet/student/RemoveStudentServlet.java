package com.zq.controller.servlet.student;

import com.alibaba.fastjson.JSON;
import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.factory.StudentDaoFactory;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.service.ClassGradeManagerService;
import com.zq.model.service.StudentMangerService;

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
 * @Date: 2021/6/9 15:07
 * @Version: V1.0
 */
@WebServlet(name = "RemoveStudentServlet", urlPatterns = "/remove-student")
public class RemoveStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 6332558926153844002L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        String idArr = request.getParameter(WebConstant.REMOVED_STUDENT_ID_ARR);
        String[] arr = JSON.parseObject(idArr, String[].class);

        try {
            StudentMangerService manager = new StudentMangerService(
                    StudentDaoFactory.newStudentDao());
            ClassGradeManagerService cgMg = new ClassGradeManagerService(
                    ClassGradeDaoFactory.newClassGradeDao());
            /*记录删除成功的个数*/
            int countOfSuccess = 0;
            /*根据student表中的主键id删除对应的记录*/
            for (String studentId : arr) {
                if (manager.removeStudentByStudentId(studentId)){
                    countOfSuccess++;
                }
            }

            /*删除结束之后, 跟新所有班级的人数*/
            List<ClassGrade> allClassGrade = cgMg.getAll();
            for (ClassGrade classGrade : allClassGrade) {
                cgMg.updateClassSize(classGrade.getClassGradeId());
            }

            String[] notices = new String[]{"您一共勾选了" + arr.length + "个学生, " +
                    countOfSuccess + "个删除成功, " +
                    (arr.length - countOfSuccess) + "个删除失败"};



            session.setAttribute(WebConstant.STUDENT_LAST_OPERATE, WebConstant.REMOVED);
            session.setAttribute(WebConstant.LAST_OPERATE_RESULT, WebConstant.SUCCESS);
            session.setAttribute(WebConstant.NOTICES,notices);
            response.sendRedirect("show-all-student");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
