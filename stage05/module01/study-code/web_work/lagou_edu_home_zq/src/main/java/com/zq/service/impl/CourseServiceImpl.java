package com.zq.service.impl;

import com.zq.base.StatusCode;
import com.zq.dao.CourseDao;
import com.zq.pojo.Course;
import com.zq.service.CourseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 课程管理模块的service层的实现类
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/3 19:34
 */
@NoArgsConstructor
public class CourseServiceImpl implements CourseService {
    @Getter
    @Setter
    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    /**
     * 获取所有课程的信息
     *
     * @return
     */
    @Override
    public List<Course> findCourseList() {
        return courseDao.findCourseList();
    }

    /**
     * 根据课程名称和课程状态查询课程信息, 课程名称可以进行模糊查询
     *
     * @param courseName
     * @param status
     * @return
     */
    @Override
    public List<Course> findCourseListByCourseNameAndStatus(String courseName, String status) {
        return courseDao.findCourseListByCourseNameAndStatus(courseName, status);
    }

    @Override
    public String saveCourseSaleInfo(Course course) {
        int result = courseDao.saveCourseSaleInfo(course);
        return result > 0 ? StatusCode.SUCCESS.toString() :
                StatusCode.FAIL.toString();
    }
}
