package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseDao;
import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程管理模块 Service层实现类
 * */
public class CourseServiceImpl implements CourseService {

    //创建CourseDao
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findCourseList() {
        List<Course> courseList = courseDao.findCourseList();
        return courseList;
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {

        List<Course> courseList = courseDao.findByCourseNameAndStatus(courseName, status);
        return courseList;
    }

    @Override
    public String saveCourseSalesInfo(Course course) {

        //1.补全课程营销信息
        String strDate = DateUtils.getDateFormart();
        course.setCreate_time(strDate);
        course.setUpdate_time(strDate);
        course.setStatus(1);

        //2.执行插入操作
        int row = courseDao.saveCourseSalesInfo(course);

        //3.判断是否插入成功
        if(row > 0){
            //插入成功
            String result = StatusCode.SUCCESS.toString();
            return result;
        }else{
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }

    @Override
    public Course findCourseById(int id) {

        return courseDao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course course) {

        int row = courseDao.updateCourseSalesInfo(course);

        //判断是否保存成功
        if(row > 0){
            //保存成功
            String result = StatusCode.SUCCESS.toString();
            return result;
        }else{
            //保存失败
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }


    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {

        //调用dao
        int row = courseDao.updateCourseStatus(course);

        Map<String ,Integer> map = new HashMap<>();

        if(row > 0){

            if(course.getStatus() == 0){
                map.put("status",0);
            }else{
                map.put("status",1);
            }
        }

        return map;
    }


}
