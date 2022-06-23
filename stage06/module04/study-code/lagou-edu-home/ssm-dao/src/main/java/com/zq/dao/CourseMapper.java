package com.zq.dao;

import com.zq.domain.Course;
import com.zq.domain.CourseVO;
import com.zq.domain.Teacher;
import lombok.NonNull;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/21 10:26
 */
public interface CourseMapper {


    /**
     * 更新课程状态
     * @param course
     * @return
     */
    int updateCourseStatus(Course course);

    /**
     * 根据条件获取课程列表
     *
     * @param courseVO 多条件查询的条件
     * @return 返回查询到的课程列表
     */
    List<Course> findCourseByCondition(@NonNull CourseVO courseVO);

    /**
     * 保存课程信息
     *
     * @param course
     * @return 返回值为1则表示保存成功
     */
    int saveCourse(Course course);

    /**
     * 保存讲师信息
     *
     * @param teacher
     * @return 返回值为1则表示保存成功
     */
    int saveTeacher(Teacher teacher);

    /**
     * 根据课程id获取对应的课程回显信息
     *
     * @param courseId
     * @return
     */
    CourseVO findCourseById(Integer courseId);

    /**
     * 更新课程信息, 更新course表中id=course.getId()的记录, 会根据course对象中的属性
     * <code>courseName, brief,  previewFirstField, previewSecondField, discounts, price, discountsTag, courseImgUrl, courseListImg, sortNum, courseDescriptionMarkDown, sales, updateTime</code>
     * 更新这条记录中的对应字段
     * @param course
     * @return
     */
    int updateCourse(Course course);

    /**
     * 更新讲师信息, 更新teacher表中course_id=teacher.getCourseId()的记录, 会根据teacher对象中的属性
     * <code>teacherName, description, position,updateTime</code>
     * 更新这条记录中的对应字段
     * @param teacher
     * @return
     */
    int updateTeacher(Teacher teacher);

}
