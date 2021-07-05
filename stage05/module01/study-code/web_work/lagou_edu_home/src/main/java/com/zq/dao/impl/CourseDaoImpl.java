package com.zq.dao.impl;

import com.zq.base.BaseDao;
import com.zq.dao.CourseDao;
import com.zq.pojo.Course;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 课程管理功能的Dao实现类, 本Dao处理的是{@link com.zq.pojo.Course}类型的对象
 *
 * @author : zq007
 * @version : V1.0
 * @date : 2021/7/2 7:47
 */
public class CourseDaoImpl extends BaseDao<Course> implements CourseDao {

    public CourseDaoImpl() {
        super();
    }

    public CourseDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 根据关键字非模糊查询实体类{@link Course }对应的数据表中的某些字段
     *
     * @param conditionMap 这个Map集合中的{@code <b>key</b>}是需要查询的字段 ,
     *                     {@code <b>value</b>}是该字段要匹配的关键字,
     *                     作用就是给sql语句追加查询条件
     * @return
     */
    @Override
    public List<Course> findCourseList(Map<String, String> conditionMap) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select id,course_name,price,status from course where 1 = 1";

        return retrieveList(sql, Course.class, conditionMap);
    }

    /**
     * 根据关键字模糊查询实体类{@link Course}对应的数据表中的某些字段
     *
     * @param conditionMap 这个Map集合中的@{code <b>key</b>}是需要查询的字段 , {@code <b>value</b>}是该字段要匹配的关键字,
     *                     作用就是给sql语句追加查询条件
     * @return
     */
    @Override
    public List<Course> findCourseListFuzzily(Map<String,String> conditionMap) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select id,course_name,price,status from course where 1 = 1";

        return retrieveListFuzzily(sql, Course.class, conditionMap);
    }
}
