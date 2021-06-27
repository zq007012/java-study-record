package com.zq.controller.servlet.classgrade;

import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.service.ClassGradeManagerService;

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
 * @Date: 2021/5/31 20:18
 * @Version: V1.0
 */
@WebServlet(name = "ShowAllClassGrade", urlPatterns ="/show-all-class-grade")
public class ShowAlClassGradelServlet extends HttpServlet {
    private static final long serialVersionUID = 6855524999590718834L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            ClassGradeManagerService manager = new ClassGradeManagerService(ClassGradeDaoFactory.newClassGradeDao());
            List<ClassGrade> classGradeList = manager.getAll();
            session.setAttribute(WebConstant.CLASS_GRADE_LIST,classGradeList);
            session.setAttribute(WebConstant.CLASS_GRADE_LAST_OPERATE, WebConstant.SHOW_ALL);
            System.out.println("已获取所有班级信息, 重定向到班级信息管理系统");
            System.out.println(classGradeList.toString());
            response.sendRedirect("class-grade-system.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
