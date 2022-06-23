package com.zq.service.impl;

import com.zq.dao.CourseMapper;
import com.zq.domain.Course;
import com.zq.domain.CourseVO;
import com.zq.domain.Teacher;
import com.zq.service.CourseService;
import org.springframework.beans.BeanUtils;
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
 * @date 2022/5/21 15:37
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    @Qualifier("courseMapper")
    private CourseMapper courseMapper;


    /**
     * 修改课程状态
     *
     * @param courseId
     * @param status
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updateCourseStatus(Integer courseId, Integer status) {
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.updateCourseStatus(course);
    }

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        return courseVO != null ?
                courseMapper.findCourseByCondition(courseVO) :
                courseMapper.findCourseByCondition(new CourseVO());
    }

    /**
     * 保存课程信息和讲师信息
     *
     * @param courseVO
     */
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void saveCourseAndTeacher(CourseVO courseVO) {
        // 1. 封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseVO, course);
        LocalDateTime now = LocalDateTime.now();
        course.setCreateTime(now);
        course.setUpdateTime(now);
        // 2. 保存课程信息
        courseMapper.saveCourse(course);

        // 3. 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO, teacher);
        // 3.1 将课程id封装到讲师信息中
        teacher.setCourseId(course.getId());
        teacher.setCreateTime(now);
        teacher.setUpdateTime(now);

        // 4. 保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    /**
     * 根据课程id获取课程回显信息
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseVO findCourseById(Integer courseId) {
        return courseMapper.findCourseById(courseId);
    }

    /**
     * 更新课程信息和讲师信息
     *
     * @param courseVO
     */
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void updateCourseAndTeacher(CourseVO courseVO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseVO, course);
        LocalDateTime now = LocalDateTime.now();
        course.setUpdateTime(now);

        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO, teacher);
        teacher.setUpdateTime(now);
        teacher.setId(null);
        teacher.setCourseId(course.getId());

        courseMapper.updateTeacher(teacher);
    }
}
