package com.lagou.dao;

import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.utils.DateUtils;
import org.junit.Test;

import java.util.List;

public class TestCourseDao {

    CourseDao courseDao = new CourseDaoImpl();

    //测试课程列表查询
    @Test
    public void testFindCourseList(){

        List<Course> courseList = courseDao.findCourseList();
        System.out.println(courseList);

    }


    //测试条件查询
    @Test
    public void testFindByCourseNameAndStatus(){

        List<Course> courseList = courseDao.findByCourseNameAndStatus("微服务", "");

        for (Course course : courseList) {
            System.out.println(course.getId() +" " +course.getCourse_name() +" " +course.getStatus());
        }
    }

    /**
     * course_name,
     * brief,
     * teacher_name,
     * teacher_info,
     * preview_first_field,
     * preview_second_field,
     * discounts,
     * price,
     * price_tag,
     * share_image_title,
     * share_title,
     * share_description,
     * course_description,
     *
     * course_img_url,
     * STATUS,
     * create_time,
     * update_time
     * */
    //测试保存课程营销信息
    @Test
    public void testSaveCourseSalesInfo(){

        //1.创建course对象
        Course course = new Course();
        course.setCourse_name("爱情36计");
        course.setBrief("学会去找对象");
        course.setTeacher_name("药水哥");
        course.setTeacher_info("人人都是药水哥");
        course.setPreview_first_field("共10讲");
        course.setPreview_second_field("每周日更新");
        course.setDiscounts(88.88);
        course.setPrice(188.0);
        course.setPrice_tag("最新优惠价");
        course.setShare_image_title("哈哈哈");
        course.setShare_title("嘻嘻嘻");
        course.setShare_description("天天向上");
        course.setCourse_description("爱情36计,就像一场游戏");
        course.setCourse_img_url("https://www.xx.com/xxx.jpg");
        course.setStatus(1); //1 上架 ,0 下架
        String formart = DateUtils.getDateFormart();
        course.setCreate_time(formart);
        course.setUpdate_time(formart);

        int i = courseDao.saveCourseSalesInfo(course);
        System.out.println(i);
    }


    //测试修改课程信息
    @Test
    public void testUpdateCourse(){

        //1.根据id查询课程信息
        Course course = courseDao.findCourseById(1);
        System.out.println(course);

        //2.修改课程营销信息
        course.setCourse_name("100个Java面试必考点");
        course.setTeacher_name("汪老师");
        course.setDiscounts(88.8);

        int i = courseDao.updateCourseSalesInfo(course);
        System.out.println(i);

    }
}
