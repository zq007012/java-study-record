package com.zq.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zq.base.BaseServlet;
import com.zq.dao.CourseDao;
import com.zq.dao.impl.CourseDaoImpl;
import com.zq.pojo.Course;
import com.zq.service.CourseService;
import com.zq.service.impl.CourseServiceImpl;
import com.zq.utils.DateTimeUtils;
import com.zq.utils.DruidPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 实现课程管理模块各个功能的Servlet
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/3 19:37
 */
@WebServlet(name = "CourseServlet", urlPatterns = {"/course"})
public class CourseServlet extends BaseServlet {

    private static final long serialVersionUID = 7262485194298022137L;

    /**
     * 课程管理模块中的获取所有课程信息的功能
     *
     * @param req
     * @param resp
     */
    public void findCourseList(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 接收参数
        // 没有"methodName"之外的参数
        // 2. 业务处理
        CourseDao coureseDao = new CourseDaoImpl(getDataSource());
        CourseService courseService = new CourseServiceImpl(coureseDao);
        List<Course> courseList = courseService.findCourseList();
        /*jsonFilter -- 实体类对象转换成json对象时, 可以指定实体类中的哪些字段能够转换成json字符串*/
        SimplePropertyPreFilter jsonFilter = new SimplePropertyPreFilter(Course.class,
                "id", "course_name", "price", "sort_num", "status");
        String courseListJson = JSON.toJSONString(courseList, jsonFilter);

        // 3. 响应结果
        try {
            resp.getWriter().println(courseListJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据课程名称和课程状态查询课程
     *
     * @param req
     * @param resp
     */
    public void findByCourseNameAndStatus(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 接收参数
        String courseName = req.getParameter("course_name");
        String status = req.getParameter("status");
        // 2. 处理业务
        CourseDao courseDao = new CourseDaoImpl(getDataSource());
        CourseService courseService = new CourseServiceImpl(courseDao);
        List<Course> courseList = courseService.findCourseListByCourseNameAndStatus(
                courseName, status);
        /*jsonFilter -- 实体类对象转换成json对象时, 可以指定实体类中的哪些字段能够转换成json字符串*/
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                "id", "course_name", "price", "sort_num", "status");
        String respString = JSON.toJSONString(courseList, filter);
        // 3. 响应结果
        try {
            resp.getWriter().println(respString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据课程的id获取课程的营销信息
     *
     * @param req
     * @param resp
     */
    public void findCourseById(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 解析请求
        String id = req.getParameter("id");
        // 2. 业务处理
        CourseDao courseDao = new CourseDaoImpl(getDataSource());
        CourseService courseService = new CourseServiceImpl(courseDao);
        Course course = courseService.findCourseById(Integer.parseInt(id));
        /*jsonFilter -- 实体类对象转换成json对象时, 可以指定实体类中的哪些字段能够转换成json字符串*/
        SimplePropertyPreFilter jsonFilter = new SimplePropertyPreFilter(Course.class,
                "id",
                "course_name",
                "brief",
                "teacher_name",
                "teacher_info",
                "preview_first_field",
                "preview_second_field",
                "discounts",
                "price",
                "price_tag",
                "course_img_url",
                "share_title",
                "share_image_title",
                "share_description",
                "course_description",
                "status");
        String respString = JSON.toJSONString(course, jsonFilter);
        // 3. 进行响应
        try {
            resp.getWriter().println(respString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改课程状态
     *
     * @param req
     * @param resp
     */
    public void updateCourseStatus(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 解析请求参数
        String id = req.getParameter("id");

        // 2.处理业务
        CourseDao courseDao = new CourseDaoImpl(getDataSource());
        CourseService courseService = new CourseServiceImpl(courseDao);

        Course course = courseDao.findCourseById(Integer.parseInt(id));
        String respString = null;
        if (course != null) {
            // 反转course的status值
            course.setStatus(course.getStatus() == 0 ? 1 : 0);
            course.setUpdate_time(DateTimeUtils.getDateTime());
            Map<String, Integer> map = courseService.updateCourseStatus(course);
            respString = JSON.toJSONString(map);
        }

        // 3. 进行响应
        try {
            resp.getWriter().println(respString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
