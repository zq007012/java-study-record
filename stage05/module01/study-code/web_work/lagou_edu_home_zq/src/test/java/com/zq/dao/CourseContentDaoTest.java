package com.zq.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zq.dao.impl.CourseContentDaoImpl;
import com.zq.pojo.Course;
import com.zq.pojo.Course_Lesson;
import com.zq.pojo.Course_Section;
import com.zq.service.CourseService;
import com.zq.utils.DateTimeUtils;
import com.zq.utils.DruidPool;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/7 9:39
 */
public class CourseContentDaoTest {
    private CourseContentDao courseContentDao;

    {
        try {
            courseContentDao = new CourseContentDaoImpl(
                    DruidPool.getInstance().getDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试{@link CourseContentDao#findCourseLessonsBySectionId(int)}方法的功能
     */
    @Test
    public void testFindCourseLessonsBySectionId(){
        List<Course_Lesson> lessons = courseContentDao.findCourseLessonsBySectionId(5);
        System.out.println(JSON.toJSONString(lessons));
    }

    /**
     * 测试{@link CourseContentDao#findCourseSectionsWithLessonsByCourseId(int)}方法的功能
     */
    @Test
    public void testFindCourseSectionsWithLessonsByCourseId(){
        List<Course_Section> courseSections =
                courseContentDao.findCourseSectionsWithLessonsByCourseId(2);
        for (Course_Section section:
             courseSections) {
            System.out.println(section.getSection_name() + ":");
            List<Course_Lesson> lessons = section.getCourseLessonList();
            for (Course_Lesson lesson :
                    lessons) {
                System.out.println("\t" + lesson.getTheme());
            }
        }
    }

    /**
     * 测试{@link CourseContentDao#findCourseById(int)}方法的功能
     */
    @Test
    public void testFindCourseById(){
        Course course = courseContentDao.findCourseById(10);
        /*jsonFilter -- 实体类对象转换成json对象时, 可以指定实体类中的哪些字段能够转换成json字符串*/
        SimplePropertyPreFilter jsonFilter = new SimplePropertyPreFilter(Course.class,
                "id","course_name");
        System.out.println(JSON.toJSONString(course,jsonFilter));
    }

    /**
     * 测试{@link CourseContentDao#saveCourseSection(Course_Section)}方法的功能
     */
    @Test
    public void testSaveCourseSection(){
        Course_Section section = new Course_Section();
        section.setCourse_id(10);
        section.setSection_name("麻式太极第四章");
        section.setDescription("年轻人不讲武德");
        section.setOrder_num(4);
        section.setStatus(1);

        String now = DateTimeUtils.getDateTime();
        section.setCreate_time(now);
        section.setUpdate_time(now);

        int row = courseContentDao.saveCourseSection(section);
        System.out.println("保存" + (row > 0 ? "成功" : "失败"));
    }

    /**
     * 测试{@link CourseContentDao#updateCourseSection(Course_Section)}方法的功能
     */
    @Test
    public void testUpdateCourseSection(){
        Course_Section section = new Course_Section();
        section.setId(46);
        section.setSection_name("微服务架构");
        section.setDescription("Hero's never die.");
        section.setOrder_num(2);
        section.setUpdate_time(DateTimeUtils.getDateTime());

        int row = courseContentDao.updateCourseSection(section);
        System.out.println("修改" + (row > 0 ? "成功" : "失败"));

    }

    /**
     * 测试{@link CourseContentDao#updateCourseSectionStatus(Course_Section)}方法的功能
     */
    @Test
    public void testUpdateCourseSectionStatus(){
        Course_Section section = new Course_Section();
        section.setId(6);
        section.setStatus(1);
        section.setUpdate_time(DateTimeUtils.getDateTime());
        int row = courseContentDao.updateCourseSectionStatus(section);
        System.out.println("修改" + (row > 0 ? "成功" : "失败"));
    }
}
