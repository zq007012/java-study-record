package com.zq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zq.dao.CourseContentMapper;
import com.zq.domain.CourseSection;
import org.apache.logging.log4j.Logger;
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
 * @date 2022/5/29 6:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextDao.xml")
public class CourseContentMapperTest {
    @Autowired
    @Qualifier("courseContentMapper")
    private CourseContentMapper courseContentMapper;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    /**
     * 测试{@link CourseContentMapper#findSectionAndLessonByCourseId(Integer)}方法的功能
     */
    @Test
    public void findSectionAndLessonByCourseIdTest() throws JsonProcessingException {
        List<CourseSection> sectionList = courseContentMapper.findSectionAndLessonByCourseId(7);
        for (CourseSection courseSection : sectionList) {
            logger.info(courseSection);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(sectionList);
        logger.info(s);
    }

}
