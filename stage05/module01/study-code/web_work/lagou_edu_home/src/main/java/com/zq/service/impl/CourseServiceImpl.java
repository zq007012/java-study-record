package com.zq.service.impl;

import com.zq.dao.CourseDao;
import com.zq.pojo.Course;
import com.zq.service.CourseService;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

/**
 * 课程管理功能的service接口的实现类, 提供的服务由{@link com.zq.dao.CourseDao}实现
 *
 * @author : zq007
 * @version : V1.0
 * @date : 2021/7/2 8:08
 */
public class CourseServiceImpl implements CourseService {
    @Getter
    @Setter
    private CourseDao courseDao;

    public CourseServiceImpl() {
    }

    public CourseServiceImpl(CourseDao courseDao) {
        setCourseDao(courseDao);
    }


    @Override
    public List<Course> findCourseList() {
        HashMap<String,String> conditionMap = new HashMap<>();
        conditionMap.put("is_del","0");
        return courseDao.findCourseList(conditionMap);
    }

    /**
     * 模糊查询课程名字和课程状态匹配指定关键字的课程
     *
     * @param courseName
     * @param courseStatus
     * @return
     */
    @Override
    public List<Course> findCourseListByNameAndStatus(String courseName, String courseStatus) {
        HashMap<String,String> conditionMap = new HashMap<>();
        conditionMap.put("is_del","0");
        conditionMap.put("course_name",courseName);
        conditionMap.put("status",courseStatus);
    }
}
