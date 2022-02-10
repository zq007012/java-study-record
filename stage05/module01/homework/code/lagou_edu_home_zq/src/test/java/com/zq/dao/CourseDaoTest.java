package com.zq.dao;

import com.alibaba.fastjson.JSON;
import com.zq.dao.impl.CourseDaoImpl;
import com.zq.pojo.Course;
import com.zq.utils.DateTimeUtils;
import com.zq.utils.DruidPool;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 测试{@link com.zq.dao.impl.CourseDaoImpl}类实现的各项功能
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/4 10:51
 */
public class CourseDaoTest {
    private CourseDao courseDao;

    {
        try {
            courseDao = new CourseDaoImpl(
                    DruidPool.getInstance().getDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试{@link CourseDaoImpl#findCourseListByCourseNameAndStatus(String, String)}方法的功能
     *
     * @throws Exception
     */
    @Test
    public void testFindCourseListByCourseNameAndStatus() {
        List<Course> courseList = courseDao.findCourseListByCourseNameAndStatus(
                "考点", "1");
        System.out.println(courseList.size());
        for (Course course :
                courseList) {
            System.out.println(JSON.toJSONString(course));
        }
    }

    /**
     * 测试{@link CourseDaoImpl#saveCourseSaleInfo(Course)}方法的功能
     */
    @Test
    public void testSaveCourseSaleInfo() {
        Course course = new Course();
        course.setCourse_name("微服务架构demo01");
        course.setBrief("大厂架构师带你一起学demo01");
        course.setTeacher_name("PDD_demo01");
        course.setTeacher_info("技术精湛安全驾驶30年demo01");
        course.setPrice(800.0);
        course.setPrice_tag("先到先得demo01");
        course.setDiscounts(88.8);
        course.setPreview_first_field("共5讲demo01");
        course.setPreview_second_field("每周二更新demo01");
        course.setCourse_img_url("htttps://www.xxx.com/xx.png");
        course.setShare_title("IT修炼之路永无止境demo01");
        course.setShare_description("金牌讲师带你了解最新最牛的技术让你的实力再次进阶!demo01");
        course.setCourse_description("十年编程两茫茫，工期短，需求长。千行代码，Bug何处藏。纵使上线又如何，新 版本，继续忙。黑白颠倒没商量，睡地铺，吃食堂。夜半梦醒，无人在身旁。最怕灯火阑珊时，手机响，心里 慌.demo01");
        course.setStatus(1);
        course.setShare_image_title("hello word_demo01");

        String dateTime = DateTimeUtils.getDateTime();
        course.setCreate_time(dateTime);
        course.setUpdate_time(dateTime);

        int result = courseDao.saveCourseSaleInfo(course);
        System.out.println(result);


    }

    /**
     * 测试{@link CourseDaoImpl#findCourseById(int)}方法的功能
     */
    @Test
    public void testFindCourseById() {
        Course course = courseDao.findCourseById(62);
        System.out.println(JSON.toJSONString(course));
    }

    /**
     * 测试{@link CourseDaoImpl#updateCourseSaleInfo(Course)}方法的功能
     */
    @Test
    public void testUpdateCourseSaleInfo() {
        Course course = new Course();
        course.setId(62);
        course.setCourse_name("微服务架构demo02");
        course.setBrief("大厂架构师带你一起学demo02");
        course.setTeacher_name("PDD_demo02");
        course.setTeacher_info("技术精湛安全驾驶30年demo02");
        course.setPrice(800.0);
        course.setPrice_tag("先到先得demo02");
        course.setDiscounts(88.8);
        course.setPreview_first_field("共5讲demo02");
        course.setPreview_second_field("每周二更新demo02");
        course.setCourse_img_url("htttps://www.xxx.com/xx.png");
        course.setShare_title("IT修炼之路永无止境demo02");
        course.setShare_description("金牌讲师带你了解最新最牛的技术让你的实力再次进阶!demo02");
        course.setCourse_description("十年编程两茫茫，工期短，需求长。千行代码，Bug何处藏。纵使上线又如何，新 版本，继续忙。黑白颠倒没商量，睡地铺，吃食堂。夜半梦醒，无人在身旁。最怕灯火阑珊时，手机响，心里 慌.demo02");
        course.setStatus(1);
        course.setShare_image_title("hello word_demo02");

        int result = courseDao.updateCourseSaleInfo(course);
        System.out.println(result > 0 ? "success" : "fail");
    }

    /**
     * 测试{@link CourseDao#updateCourseStatus(Course)}方法的功能
     */
    @Test
    public void testUpdateCourseStatus(){
        Course course = new Course();
        course.setId(2);
        course.setUpdate_time(DateTimeUtils.getDateTime());
        course.setStatus(1);
        int result = courseDao.updateCourseStatus(course);
        System.out.println(result > 0 ? "修改成功" : "修改失败");
    }
}
