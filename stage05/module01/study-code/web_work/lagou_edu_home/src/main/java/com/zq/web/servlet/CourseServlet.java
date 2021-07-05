package com.zq.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zq.base.BaseServlet;
import com.zq.dao.CourseDao;
import com.zq.dao.impl.CourseDaoImpl;
import com.zq.pojo.Course;
import com.zq.service.CourseService;
import com.zq.service.impl.CourseServiceImpl;
import com.zq.utils.DruidPool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 课程管理模块的Servlet, 具有课程管理的功能
 *
 * @author : zq007
 * @version : V1.0
 * @date : 2021/7/2 10:12
 */
@WebServlet(name = "CourseServlet", urlPatterns ={"/course"})
public class CourseServlet extends BaseServlet{

    private static final long serialVersionUID = 7262485194298022137L;

    /**
     * 获取所有课程信息的列表
     * @param request
     * @param response
     */
    public void findCourseList(HttpServletRequest request, HttpServletResponse response) {
        // 1. 接收请求参数中的数据

        // 2. 进行业务处理
        CourseDao courseDao = new CourseDaoImpl(getDataSource());
        CourseService courseService = new CourseServiceImpl(courseDao);
        List<Course> courseList = courseService.findCourseList();

        // 3. 响应结果
        SimplePropertyPreFilter sppFilter = new SimplePropertyPreFilter(Course.class, "id",
                "course_name",
                "price",
                "sort_num",
                "status");
        String jsonOfCourseList = JSON.toJSONString(courseList, sppFilter);
        try {
            response.getWriter().print(jsonOfCourseList);
        } catch (IOException e) {
            System.out.println("响应时出错");
            e.printStackTrace();
        }


    }

}
