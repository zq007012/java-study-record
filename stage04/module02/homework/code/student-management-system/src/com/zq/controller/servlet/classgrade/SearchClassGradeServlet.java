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
 * @Date: 2021/6/7 15:24
 * @Version: V1.0
 */
@WebServlet(name = "SearchClassGradeServlet", urlPatterns ="/search-class-grade")
public class SearchClassGradeServlet extends HttpServlet {
    private static final long serialVersionUID = 8836867214994307614L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String keyword = request.getParameter(WebConstant.KEYWORD);

        try {
            ClassGradeManagerService manager = new ClassGradeManagerService(
                    ClassGradeDaoFactory.newClassGradeDao());
            List<ClassGrade> classGradeList= manager.searchFuzzily(keyword);
            System.out.println("搜索到的班级有: " + classGradeList.toString());
            session.setAttribute(WebConstant.LAST_OPERATE_RESULT,WebConstant.SEARCH);
            session.setAttribute(WebConstant.LAST_OPERATE_RESULT,WebConstant.SUCCESS);
            session.setAttribute(WebConstant.CLASS_GRADE_LIST,classGradeList);
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
