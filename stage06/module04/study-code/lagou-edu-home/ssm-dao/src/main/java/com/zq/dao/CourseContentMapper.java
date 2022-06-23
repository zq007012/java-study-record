package com.zq.dao;

import com.zq.domain.Course;
import com.zq.domain.CourseLesson;
import com.zq.domain.CourseSection;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/28 18:52
 */
public interface CourseContentMapper {

    /**
     * 在course_lesson表中保存课时信息, 包含以下属性
     * <code>courseId, sectionId, theme, duration, isFree, orderNum, createTime, updateTime</code>
     * @param courseLesson
     * @return
     */
    int saveCourseLesson(CourseLesson courseLesson);

    /**
     * 根据章节id更新course_section表中指定记录的<code>status, updateTime</code>
     * @param courseSection
     * @return
     */
    int updateSectionStatus(CourseSection courseSection);

    /**
     * 根据章节id更新course_section表中指定记录的<code>sectionName, description, orderNum, updateTime</code>
     * @param courseSection
     * @return
     */
    int updateCourseSection(CourseSection courseSection);

    /**
     * 根据课程id查询该课程下的所有章节信息, 并且每条章节信息都携带着所有对应的课时信息
     * 章节信息中要有id, courseId, sectionName, description, createTime, updateTime, isDe, orderNum, status和lessonList
     * 章节信息中的lessonList携带者该章节下的所有课时信息,
     * 每条课时信息要有id, courseId, sectionId, theme, duration, isFree, createTime, updateTime, isDel, orderNum, status
     * @param courseId
     * @return
     */
    List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 根据课程id获取课程id和课程名称
     * @param courseId
     * @return
     */
    Course findCourseByCourseId(Integer courseId);

    /**
     * 保存章节信息,<code>courseId, sectionName, description, orderNum, createTime, updateTime</code>
     * @param courseSection
     * @return
     */
    int saveSection(CourseSection courseSection);


}
