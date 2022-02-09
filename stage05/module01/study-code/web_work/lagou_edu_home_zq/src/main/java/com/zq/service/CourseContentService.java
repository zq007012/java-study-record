package com.zq.service;

import com.zq.base.StatusCode;
import com.zq.pojo.Course;
import com.zq.pojo.Course_Section;
import lombok.NonNull;

import java.util.List;

/**
 * 课程内容相关业务的Service接口
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/6 21:47
 */
public interface CourseContentService {

    /**
     * 根据课程id获取课程内容
     * @param courseId
     * @return
     */
    List<Course_Section> findCourseSectionsWithLessonsByCourseId(int courseId);

    /**
     * 根据课程id获取课程信息, 课程信息只包含<code>id, course_name</code>
     * @param courseId
     * @return
     */
    Course findCourseById(int courseId);

    /**
     * 保存课程信息, 保存的字段有
     * <p>status --- 0:隐藏；1：待更新；2：已发布</p>
     * @param courseSection
     * @return {@link StatusCode#toString()}
     */
    String saveCourseSection(Course_Section courseSection);

    /**
     * 更新课程信息, 更新的字段有
     * <p><code>section_name,description,order_num,update_time</code></p>
     * @param courseSection
     * @return {@link StatusCode#toString()}
     */
    String updateCourseSection(@NonNull Course_Section courseSection);

    /**
     * 更新课程状态
     * <p>修改的字段有<code>status, update_time</code></p>
     * <p>条件是: <code>id = ? </code></p>
     * @param courseSection
     * @return {@link StatusCode#toString()}
     */
    String updateCourseSectionStatus(@NonNull Course_Section courseSection);
}
