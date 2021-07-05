package com.zq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zq.dao.CourseDao;
import com.zq.dao.impl.CourseDaoImpl;
import com.zq.pojo.Course;
import com.zq.utils.DruidPool;
import org.junit.Test;

import java.util.List;

/**
 * 测试{@link com.zq.dao.CourseDao}里的功能
 *
 * @author : zq007
 * @version : V1.0
 * @date : 2021/7/2 9:18
 */
public class CourseDaoTest{
    /**
     * @see CourseDaoImpl#findCourseList(KeyValue...)
     * @throws Exception
     */
    @Test
    public void testFindCourseList() throws Exception {
        CourseDao dao = new CourseDaoImpl(DruidPool.getInstance().getDataSource());
        List<Course> courseList = dao.findCourseList();
        SimplePropertyPreFilter sppFilter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "price", "status");
        String json = JSON.toJSONString(courseList, sppFilter);

        System.out.println(json);
    }

   /**
    * @see CourseDaoImpl#findCourseListFuzzily(KeyValue...)
    *
    * @throws Exception
    */
   @Test
   public void testFindCourseListFuzzily() throws Exception{
       CourseDao dao = new CourseDaoImpl(DruidPool.getInstance().getDataSource());
       KeyValue name = new KeyValue("course_name", "微服务");
       KeyValue status = new KeyValue("status", "0");
       List<Course> courseList = dao.findCourseListFuzzily(name, status);

       SimplePropertyPreFilter sppFilter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "price", "status");
       String json = JSON.toJSONString(courseList, sppFilter);

       System.out.println(json);
   }
}
