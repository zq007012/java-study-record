package com.zq;

import com.zq.dao.CourseMapper;
import com.zq.domain.Course;
import com.zq.domain.CourseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/21 11:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextDao.xml")
public class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 测试{@link CourseMapper#findCourseByCondition(CourseVO)}方法的功能
     */
    @Test
    public void findCourseByConditionTest(){
        List<Course> courseList = courseMapper.findCourseByCondition(new CourseVO());
        for (Course course : courseList) {
            System.out.println(course);
        }
    }
}
