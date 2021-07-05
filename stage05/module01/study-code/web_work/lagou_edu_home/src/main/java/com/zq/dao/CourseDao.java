package com.zq.dao;

import java.util.List;
import java.util.Map;
import com.zq.pojo.Course;

/**
 * 课程管理功能的的Dao接口, 本Dao处理的是{@link Course}类型的对象
 *
 * @author : zq007
 * @version : V1.0
 * @date : 2021/7/2 7:35
 */
public interface CourseDao {
    /**
     * 根据关键字非模糊查询实体类{@link Course }对应的数据表中的某些字段
     * @param conditionMap 这个Map集合中的{@code <b>key</b>}是需要查询的字段 ,
     *                     {@code <b>value</b>}是该字段要匹配的关键字,
     *                     作用就是给sql语句追加查询条件
     * @return
     */
    List<Course> findCourseList(Map<String,String> conditionMap);

    /**
     * 根据关键字模糊查询实体类{@link Course}对应的数据表中的某些字段
     * @param conditionMap 这个Map集合中的@{code <b>key</b>}是需要查询的字段 , {@code <b>value</b>}是该字段要匹配的关键字,
     *                     作用就是给sql语句追加查询条件
     * @return
     */
    List<Course> findCourseListFuzzily(Map<String,String> conditionMap);
}