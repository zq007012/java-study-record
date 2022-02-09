package com.zq.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zq.base.BaseServlet;
import com.zq.dao.CourseContentDao;
import com.zq.dao.impl.CourseContentDaoImpl;
import com.zq.pojo.Course;
import com.zq.pojo.Course_Section;
import com.zq.service.CourseContentService;
import com.zq.service.impl.CourseContentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 课程内容管理模块, 具有课程内容管理的各种功能
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/6 22:06
 */
@WebServlet(name = "CourseContentServlet", urlPatterns = {"/courseContent"})
public class CourseContentServlet extends BaseServlet {

    private static final long serialVersionUID = -2030989247169628487L;

    /**
     * 根据课程id获取该课程下的章节和章节下的课时信息
     *
     * @param req
     * @param resp
     */
    public void findSectionAndLessonByCourseId(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 解析请求
        String courseId = req.getParameter("course_id");
        // 2. 业务处理
        CourseContentDao courseContentDao = new CourseContentDaoImpl(getDataSource());
        CourseContentService courseContentService = new CourseContentServiceImpl(courseContentDao);
        List<Course_Section> courseSectionsWithLessonsByCourseId =
                courseContentService.findCourseSectionsWithLessonsByCourseId(Integer.parseInt(courseId));

        // 3. 进行响应
        String respString = JSON.toJSONString(courseSectionsWithLessonsByCourseId);
        try {
            resp.getWriter().println(respString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 课程信息回显
     *
     * @param req
     * @param resp
     */
    public void findCourseById(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 解析请求
        String courseId = req.getParameter("id");
        // 2. 处理业务
        CourseContentDao courseContentDao = new CourseContentDaoImpl(getDataSource());
        CourseContentService courseContentService = new CourseContentServiceImpl(courseContentDao);
        Course course = courseContentService.findCourseById(Integer.parseInt(courseId));

        /*jsonFilter -- 实体类对象转换成json对象时, 可以指定实体类中的哪些字段能够转换成json字符串*/
        SimplePropertyPreFilter jsonFilter = new SimplePropertyPreFilter(Course.class,
                "id", "course_name");
        String respString = JSON.toJSONString(course, jsonFilter);
        // 3. 进行相应
        try {
            resp.getWriter().println(respString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存或更新章节信息, 不携带id, 就是保存章节, 携带id, 就是更新章节
     *
     * @param req
     * @param resp
     */
    public void saveOrUpdateSection(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 解析请求
        Map<String, Object> map = (Map) req.getAttribute("postJsonMap");
        // 2. 处理业务
        Course_Section section = new Course_Section();
        try {
            BeanUtils.populate(section, map);
            String result = null;
            CourseContentDao courseContentDao = new CourseContentDaoImpl(getDataSource());
            CourseContentService courseContentService = new CourseContentServiceImpl(courseContentDao);
            // 判断map是否携带id
            if (map.get("id") == null) {
                // 不携带id, 就是保存章节
                result = courseContentService.saveCourseSection(section);
            } else {
                // 携带id, 就是更新章节
                result = courseContentService.updateCourseSection(section);
            }
            // 3. 进行响应
            resp.getWriter().println(result);
        } catch (IllegalAccessException | IOException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改课程状态的功能
     *
     * @param req
     * @param resp
     */
    public void updateSectionStatus(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 解析请求
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        // 2. 处理业务
        Course_Section section = new Course_Section();
        section.setId(Integer.parseInt(id));
        section.setStatus(Integer.parseInt(status));
        CourseContentDao courseContentDao = new CourseContentDaoImpl(getDataSource());
        CourseContentService courseContentService = new CourseContentServiceImpl(courseContentDao);
        String respString = courseContentService.updateCourseSectionStatus(section);
        // 3. 进行响应
        try {
            resp.getWriter().println(respString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
