package com.zq.service;

import com.zq.pojo.Course;

import java.util.List;

/**
 * 课程管理功能的service接口, 提供的服务由{@link com.zq.dao.CourseDao}实现
 *
 * @author : zq007
 * @version : V1.0
 * @date : 2021/7/2 8:14
 */
public interface CourseService {
    /**
     * 查询所有课程
     * @return
     */
    List<Course> findCourseList();

    /**
     * 模糊查询课程名字和课程状态匹配指定关键字的课程
     * @param courseName
     * @param courseStatus
     * @return
     */
    List<Course> findCourseListByNameAndStatus(String courseName, String courseStatus);
}
