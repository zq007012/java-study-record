package com.zq.service;

import com.zq.domain.Course;
import com.zq.domain.CourseLesson;
import com.zq.domain.CourseSection;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/29 7:00
 */
public interface CourseContentService {

    /**
     * 保存课时信息
     * 请求中携带的课时信息有courseId, sectionId, theme, duration, isFree, orderNum , 服务器这边需要生成createTime, updateTime
     * @param courseLesson
     */
    void saveCourseLesson(CourseLesson courseLesson);

    /**
     * 更新章节状态
     * 请求中携带的章节信息有id status , 服务器这边需要生成updateTime
     * @param courseSection
     */
    void updateSectionStatus(CourseSection courseSection);

    /**
     * 更新章节信息
     * 请求中携带的章节信息有id, sectionName, description, orderNum , 服务器这边需要生成updateTime
     * @param courseSection
     */
    void updateCourseSection(CourseSection courseSection);

    /**
     * 根据课程id获取课程id和课程名称
     * @param courseId
     * @return
     */
    Course findCourseByCourseId(Integer courseId);

    /**
     * 根据课程id查询该课程下的所有章节信息, 并且每条章节信息都要携带该章节下的所有可是信息
     * @param courseId
     * @return
     */
    List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 保存章节信息
     * 请求中携带的章节信息有courseId, sectionName, description, orderNum,status, 服务器这边需要生成createTime, updateTime
     * @param courseSection
     */
    void saveCourseSection(CourseSection courseSection);
}
