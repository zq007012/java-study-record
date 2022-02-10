package com.zq.service;

import com.zq.base.StatusCode;
import com.zq.pojo.Course;
import com.zq.pojo.Course_Lesson;
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
     * 更新课时信息
     * <p>需要更新的字段是: <code>course_id, section_id, theme, duration, is_free, order_num, update_time</code>,
     * update_time由本服务设置</p>
     * <p>需要满足的条件: <code>id=?</code></p>
     * @param lesson
     * @return {@link StatusCode#toString()}
     */
    String updateCourseLesson(Course_Lesson lesson);

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

    /**
     * 保存新课时
     * <p>保存的字段有<code>course_id, section_id, theme, duration, is_free, order_num,  create_time, update_time, status</code></p>
     * <p>status : 课时状态,0-隐藏，1-未发布，2-已发布, 默认为2</p>
     * <p>is_free : 是否免费, 0-否, 1-是</p>
     * @param lesson
     * @return {@link StatusCode#toString()}
     */
    String saveCourseLesson(Course_Lesson lesson);
}
