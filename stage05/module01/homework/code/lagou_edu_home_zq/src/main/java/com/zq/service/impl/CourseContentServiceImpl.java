package com.zq.service.impl;

import com.zq.base.StatusCode;
import com.zq.dao.CourseContentDao;
import com.zq.pojo.Course;
import com.zq.pojo.Course_Lesson;
import com.zq.pojo.Course_Section;
import com.zq.service.CourseContentService;
import com.zq.utils.DateTimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

/**
 * 课程内容相关业务的Service实现类
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/6 21:48
 */
@NoArgsConstructor
public class CourseContentServiceImpl implements CourseContentService {
    @Getter
    @Setter
    private CourseContentDao courseContentDao;

    public CourseContentServiceImpl(CourseContentDao courseContentDao) {
        setCourseContentDao(courseContentDao);
    }

    /**
     * 更新课时信息
     * <p>需要更新的字段是: <code>course_id, section_id, theme, duration, is_free, order_num, update_time</code>,
     * update_time由本服务设置</p>
     * <p>需要满足的条件: <code>id=?</code></p>
     *
     * @param lesson
     * @return {@link StatusCode#toString()}
     */
    @Override
    public String updateCourseLesson(Course_Lesson lesson) {
        lesson.setUpdate_time(DateTimeUtils.getDateTime());
        int row = courseContentDao.updateCourseLesson(lesson);
        return row > 0 ? StatusCode.SUCCESS.toString() : StatusCode.FAIL.toString();
    }

    /**
     * 根据课程id获取课程内容
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Course_Section> findCourseSectionsWithLessonsByCourseId(int courseId) {
        return courseContentDao.findCourseSectionsWithLessonsByCourseId(courseId);
    }

    /**
     * 根据课程id获取课程信息, 课程信息只包含<code>id, course_name</code>
     *
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseById(int courseId) {
        return courseContentDao.findCourseById(courseId);
    }

    /**
     * 保存课程信息, 保存的字段有
     * <p><code>section_name,description,order_num,STATUS,create_time,update_time</code></p>
     * <p>status --- 0:隐藏；1：待更新；2：已发布</p>
     *
     * @param courseSection
     * @return {@link StatusCode#toString()}
     */
    @Override
    public String saveCourseSection(@NonNull Course_Section courseSection) {
        courseSection.setStatus(2);
        String now = DateTimeUtils.getDateTime();
        courseSection.setCreate_time(now);
        courseSection.setUpdate_time(now);
        int row = courseContentDao.saveCourseSection(courseSection);
        return row > 0 ? StatusCode.SUCCESS.toString() : StatusCode.FAIL.toString();
    }

    /**
     * 更新课程信息, 更新的字段有
     * <p><code>section_name,description,order_num,update_time</code></p>
     *
     * @param courseSection
     * @return {@link StatusCode#toString()}
     */
    @Override
    public String updateCourseSection(@NonNull Course_Section courseSection) {
        courseSection.setUpdate_time(DateTimeUtils.getDateTime());
        int row = row = courseContentDao.updateCourseSection(courseSection);
        return row > 0 ? StatusCode.SUCCESS.toString() : StatusCode.FAIL.toString();
    }

    @Override
    public String updateCourseSectionStatus(@NonNull Course_Section courseSection) {
        courseSection.setUpdate_time(DateTimeUtils.getDateTime());
        int row = courseContentDao.updateCourseSectionStatus(courseSection);
        return row > 0 ? StatusCode.SUCCESS.toString() : StatusCode.FAIL.toString();
    }

    /**
     * 保存新课时
     * <p>保存的字段有<code>course_id, section_id, theme, duration, is_free, order_num,  create_time, update_time, status</code></p>
     * <p>status : 课时状态,0-隐藏，1-未发布，2-已发布, 默认为2</p>
     * <p>is_free : 是否免费, 0-否, 1-是</p>
     *
     * @param lesson
     * @return {@link StatusCode#toString()}
     */
    @Override
    public String saveCourseLesson(Course_Lesson lesson) {
        lesson.setStatus(2);
        String now = DateTimeUtils.getDateTime();
        lesson.setCreate_time(now);
        lesson.setUpdate_time(now);
        int row = courseContentDao.saveCourseLesson(lesson);
        return row > 0 ? StatusCode.SUCCESS.toString() : StatusCode.FAIL.toString();
    }
}
