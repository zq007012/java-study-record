package com.zq.service.impl;

import com.zq.dao.CourseContentMapper;
import com.zq.domain.Course;
import com.zq.domain.CourseLesson;
import com.zq.domain.CourseSection;
import com.zq.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/29 7:03
 */
@Service("courseContentService")
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    @Qualifier("courseContentMapper")
    private CourseContentMapper courseContentMapper;

    /**
     * 保存课时信息
     * 请求中携带的课时信息有courseId, sectionId, theme, duration, isFree, orderNum , 服务器这边需要生成createTime, updateTime
     *
     * @param courseLesson
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void saveCourseLesson(CourseLesson courseLesson) {
        LocalDateTime now = LocalDateTime.now();
        courseLesson.setCreateTime(now);
        courseLesson.setUpdateTime(now);
        courseContentMapper.saveCourseLesson(courseLesson);
    }

    /**
     * 更新章节状态
     * 请求中携带的章节信息有id status , 服务器这边需要生成updateTime
     *
     * @param courseSection
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updateSectionStatus(CourseSection courseSection) {
        courseSection.setUpdateTime(LocalDateTime.now());
        courseContentMapper.updateSectionStatus(courseSection);
    }

    /**
     * 更新章节信息
     * 请求中携带的章节信息有id, sectionName, description, orderNum , 服务器这边需要生成updateTime
     *
     * @param courseSection
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updateCourseSection(CourseSection courseSection) {
        LocalDateTime now = LocalDateTime.now();
        courseSection.setUpdateTime(now);
        courseContentMapper.updateCourseSection(courseSection);
    }

    /**
     * 根据课程id获取课程id和课程名称
     *
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseByCourseId(Integer courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    /**
     * 根据课程id查询该课程下的所有章节信息, 并且每条章节信息都要携带该章节下的所有可是信息
     *
     * @param courseId
     * @return
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        return courseContentMapper.findSectionAndLessonByCourseId(courseId);
    }

    /**
     * 保存章节信息
     * 请求中携带的章节信息有courseId, sectionName, description, orderNum,status, 服务器这边需要生成createTime, updateTime
     *
     * @param courseSection
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void saveCourseSection(CourseSection courseSection) {
        LocalDateTime now = LocalDateTime.now();
        courseSection.setCreateTime(now);
        courseSection.setUpdateTime(now);
        courseContentMapper.saveSection(courseSection);
    }
}
