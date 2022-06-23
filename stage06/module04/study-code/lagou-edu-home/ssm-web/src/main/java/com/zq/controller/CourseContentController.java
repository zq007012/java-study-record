package com.zq.controller;

import com.zq.domain.Course;
import com.zq.domain.CourseLesson;
import com.zq.domain.CourseSection;
import com.zq.domain.ResponseResult;
import com.zq.service.CourseContentService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/29 7:29
 */
@Controller("courseContentController")
@RequestMapping("/courseContent")
@ResponseBody
public class CourseContentController {
    @Autowired
    @Qualifier("courseContentService")
    private CourseContentService courseContentService;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId) {
        List<CourseSection> content = courseContentService.findSectionAndLessonByCourseId(courseId);
        return new ResponseResult(true, 200, "课程内容响应成功时", content);
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        return new ResponseResult(true, 200, "响应成功", course);
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {
        if (courseSection.getId() == null) {
            courseContentService.saveCourseSection(courseSection);
            return new ResponseResult(true, 200, "保存章节成功", null);
        } else {
            courseContentService.updateCourseSection(courseSection);
            return new ResponseResult(true, 200, "更新章节成功", null);
        }
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(CourseSection courseSection) {
        courseContentService.updateSectionStatus(courseSection);
        return new ResponseResult(true, 200, "更新章节状态成功", null);
    }

    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson (@RequestBody CourseLesson courseLesson){
        courseContentService.saveCourseLesson(courseLesson);
        return new ResponseResult(true,200,"新建课时成功",null);
    }
}
