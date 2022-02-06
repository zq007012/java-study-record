package com.zq.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zq.base.BaseServlet;
import com.zq.dao.CourseDao;
import com.zq.dao.impl.CourseDaoImpl;
import com.zq.pojo.Course;
import com.zq.service.CourseService;
import com.zq.service.impl.CourseServiceImpl;
import com.zq.utils.DruidPool;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * 实现课程管理模块各个功能的Servlet
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/3 19:37
 */
@WebServlet(name = "CourseServlet", urlPatterns ={"/course"})
public class CourseServlet extends BaseServlet{
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            dataSource = DruidPool.getInstance().getDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final long serialVersionUID = 7262485194298022137L;

    /**
     * 课程管理模块中的获取所有课程信息的功能
     * @param req
     * @param resp
     */
    public void findCourseList(HttpServletRequest req, HttpServletResponse resp){
        // 1. 接收参数
        // 没有"methodName"之外的参数
        // 2. 业务处理
        CourseDao coureseDao = new CourseDaoImpl(dataSource);
        CourseService courseService = new CourseServiceImpl(coureseDao);
        List<Course> courseList = courseService.findCourseList();
        /*jsonFilter -- 实体类对象转换成json对象时, 可以指定实体类中的哪些字段能够转换成json字符串*/
        SimplePropertyPreFilter jsonFilter = new SimplePropertyPreFilter(Course.class,
                "id","course_name","price","sort_num","status");
        String courseListJson = JSON.toJSONString(courseList, jsonFilter);

        // 3. 响应结果
        try {
            resp.getWriter().println(courseListJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findByCourseNameAndStatus(HttpServletRequest req, HttpServletResponse resp){
        // 1. 接收参数
        String courseName = req.getParameter("course_name");
        String status = req.getParameter("status");
        // 2. 处理业务
        CourseDao courseDao = new CourseDaoImpl(dataSource);
        CourseService courseService = new CourseServiceImpl(courseDao);
        List<Course> courseList = courseService.findCourseListByCourseNameAndStatus(
                courseName, status);
        /*jsonFilter -- 实体类对象转换成json对象时, 可以指定实体类中的哪些字段能够转换成json字符串*/
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                "id","course_name","price","sort_num","status");
        String respString = JSON.toJSONString(courseList, filter);
        // 3. 响应结果
        try {
            resp.getWriter().println(respString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
