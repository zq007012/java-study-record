package com.zq.controller;

import com.zq.domain.CourseVO;
import com.zq.domain.ResponseResult;
import com.zq.service.CourseService;
import com.zq.utils.UUIDUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/21 17:20
 */
@Controller("courseController")
@RequestMapping("/course")
@ResponseBody
public class CourseController {
    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    @Autowired
    @Qualifier("courseService")
    private CourseService courseService;

    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam("id") Integer courseId, Integer status) {
        courseService.updateCourseStatus(courseId, status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status", status);
        return new ResponseResult(true, 200, "响应成功", map);
    }

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {
        logger.debug(courseVO);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        responseResult.setStatus(200);
        responseResult.setMessage("响应成功");
        responseResult.setContent(courseService.findCourseByCondition(courseVO));

        return responseResult;
    }



    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) {
        if (courseVO.getId() == null) {
            courseService.saveCourseAndTeacher(courseVO);
            return new ResponseResult(true, 200, "课程信息保存成功", null);
        } else {
            courseService.updateCourseAndTeacher(courseVO);
            return new ResponseResult(true, 200, "课程信息更新成功", null);
        }
    }

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam("id") Integer id) {
        CourseVO courseVO = courseService.findCourseById(id);
        return new ResponseResult(true, 200, "相应成功", courseVO);
    }

}
