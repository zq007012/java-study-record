package com.zq.service;

import com.zq.pojo.Course;

import java.util.List;

/**
 * 课程管理模块的service层接口
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/3 19:32
 */
public interface CourseService {
    /**
     * 获取所有课程的信息
     * @return
     */
    List<Course> findCourseList();

    /**
     * 根据课程名称和课程状态查询课程信息, 课程名称可以进行模糊查询
     * @param courseName
     * @param status
     * @return
     */
    List<Course> findCourseListByCourseNameAndStatus(String courseName, String status);

    /**
     * 保存课程营销信息
     * @param course
     * @return  以{"msg":"success","status":0}的格式返回保存结果
     */
    String saveCourseSaleInfo(Course course);
}
