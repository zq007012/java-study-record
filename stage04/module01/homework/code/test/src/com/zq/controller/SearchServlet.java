package com.zq.controller;

import com.zq.model.dao.StudentDaoImp;
import com.zq.model.javabean.Student;
import com.zq.model.service.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/17 17:42
 * @Version: V1.0
 */
@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 606581711412778854L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先判断当前是否处于成功登陆状态, 不是的话就跳转到登录页面
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        if (!"success".equals(session.getAttribute("logIn"))) {
            session.setAttribute("logIn", "invalid");
            response.sendRedirect("login.jsp");
            return;
        }

        request.setCharacterEncoding("utf-8");
        String keyword = request.getParameter("keyword");
        ManagerService managerService = null;
        try {
            managerService = new ManagerService(StudentDaoImp.getInstance());
            List<Student> searchedList = managerService.searchFuzzilyByNumber(keyword);
            List<Student> studentListByName = managerService.searchFuzzilyByName(keyword);

            searchedList.removeAll(studentListByName);
            searchedList.addAll(studentListByName);
            //按照学号的大小排序
            searchedList.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Integer.parseInt(o1.getNumber()) -
                            Integer.parseInt(o2.getNumber());
                }
            });

            session.setAttribute("studentList", searchedList);
            response.sendRedirect("student-system.jsp?indexOfPage=1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
