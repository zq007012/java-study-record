package com.zq.service;

import com.zq.pojo.Course;
import com.zq.pojo.Course_Lesson;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据id获取课程的营销信息
     * @param id
     * @return
     */
    Course findCourseById(int id);

    /**
     * 修改某个课程的营销信息
     * @param course
     * @return
     */
    String updateCourseSaleInfo(Course course);

    /**
     * 修改课程的状态
     * @param course
     * @return
     */
    Map<String,Integer> updateCourseStatus(Course course);

}
