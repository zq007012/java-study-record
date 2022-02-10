package com.zq.dao;

import com.zq.pojo.Course;

import java.util.List;

/**
 * 课程管理模块的dao层接口
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/3 19:31
 */
public interface CourseDao {
    /**
     * 获取所有课程的信息
     * @return
     */
    List<Course> findCourseList();

    /**
     * 根据课程名称和课程状态查询课程信息, 课程名称可以模糊查询
     * @param courseName
     * @param status 课程状态, 0是上架, 1是下架
     * @return 所有满足条件的课程信息
     */
    List<Course> findCourseListByCourseNameAndStatus(String courseName, String status);

    /**
     * 保存课程营销信息
     * @param course
     * @return
     */
    int saveCourseSaleInfo(Course course);

    /**
     * 根据id查找课程的营销信息
     * @param id
     * @return
     */
    Course findCourseById(int id);

    /**
     * 更新某个课程的营销信息
     * @param course
     * @return
     */
    int updateCourseSaleInfo(Course course);

    /**
     * 修改某个课程的状态
     * @param course
     * @return
     */
    int updateCourseStatus(Course course);
}
