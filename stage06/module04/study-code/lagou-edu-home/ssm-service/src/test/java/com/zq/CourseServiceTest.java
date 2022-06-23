package com.zq;

import com.zq.domain.Course;
import com.zq.domain.CourseVO;
import com.zq.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/21 15:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextService.xml")
public class CourseServiceTest {

    @Autowired
    @Qualifier("courseService")
    private CourseService courseService;

    /**
     * 测试{@link CourseService#findCourseByCondition(CourseVO)}方法的功能
     */
    @Test
    public void findCourseByConditionTest() {
        List<Course> courseList = courseService.findCourseByCondition(null);
        for (Course course : courseList) {
            System.out.println(course);
        }
    }

}
