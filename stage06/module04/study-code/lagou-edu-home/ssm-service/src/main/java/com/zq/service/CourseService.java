package com.zq.service;

import com.zq.dao.CourseMapper;
import com.zq.domain.Course;
import com.zq.domain.CourseVO;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/21 15:33
 */
public interface CourseService {

    /**
     * 修改课程状态
     * @param courseId
     * @param status
     */
    void updateCourseStatus(Integer courseId, Integer status);

    /**
     * 多条件查询课程信息
     * @param courseVO
     * @return
     */
    List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     * 保存课程信息和讲师信息
     * @param courseVO
     */
    void saveCourseAndTeacher(CourseVO courseVO);

    /**
     * 根据课程id获取课程回显信息
     * @param courseId
     * @return
     */
    CourseVO findCourseById(Integer courseId);

    /**
     * 保存课程信息和讲师信息
     * @param courseVO
     */
    void updateCourseAndTeacher(CourseVO courseVO);
}
