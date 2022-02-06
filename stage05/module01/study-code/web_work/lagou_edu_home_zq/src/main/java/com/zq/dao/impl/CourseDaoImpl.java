package com.zq.dao.impl;

import com.zq.base.BaseDao;
import com.zq.dao.CourseDao;
import com.zq.pojo.Course;
import com.zq.utils.EmptyUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程管理模块的dao层实现类
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/3 19:31
 */
public class CourseDaoImpl extends BaseDao implements CourseDao {

    public CourseDaoImpl() {
    }

    public CourseDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 获取所有课程的信息
     *
     * @return 获取所有课程的信息
     */
    @Override
    public List<Course> findCourseList() {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select \n" +
                "    id,/*ID*/\n" +
                "    course_name,/*课程名称*/\n" +
                "    price,/*价格*/\n" +
                "    sort_num,/*排序*/\n" +
                "    status/*状态*/\n" +
                "from course\n" +
                "where is_del = 0/*逻辑删除状态为未删除*/";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = getQueryRunner();
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Course> beanListHandler = new BeanListHandler<>(Course.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Course> query = null;
        try {
            query = queryRunner.query(sql, beanListHandler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 根据课程名称和课程状态查询课程信息, 课程名称可以模糊查询
     *
     * @param courseName
     * @param status     课程状态, 0是上架, 1是下架
     * @return 所有满足条件的课程信息
     */
    @Override
    public List<Course> findCourseListByCourseNameAndStatus(String courseName, String status) {
        // 0. 拼接SQL语句, 填充占位符对应的参数
        StringBuilder sqlSb = new StringBuilder(
                "SELECT id, course_name, price, sort_num, STATUS FROM course WHERE 1 = ? && is_del = 0");
        // 用来存放占位符的参数
        List<Object> paramList = new ArrayList<>();
        // 条件"1 = 1"是为了方便后面直接拼接"&&"语句, 将第二个"1"换成"?"是可以保证当方法的两个
        // 参数的值都为空, 在queryRunner运行query方法时, paramList.toArray()这个参数不会多余
        paramList.add(1);

        if (!EmptyUtils.isEmpty(courseName)) {
            sqlSb.append(" && course_name like concat('%', ?, '%')");
            paramList.add(courseName);
        }

        if (!EmptyUtils.isEmpty(status)) {
            sqlSb.append(" && status = ?");
            paramList.add(Integer.parseInt(status));
        }

        // 1. 编写SQL语句
        // language=MySQL
        String sql = sqlSb.toString();
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = getQueryRunner();
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Course> beanListHandler = new BeanListHandler<>(Course.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Course> query = null;
        try {
            query = queryRunner.query(sql, beanListHandler, paramList.toArray()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 保存课程营销信息
     *
     * @param course
     * @return 返回值为-1时表示保存失败
     */
    @Override
    public int saveCourseSaleInfo(Course course) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "INSERT INTO course(\n" +
                "course_name,/*名称*/\n" +
                "brief,/*简介*/\n" +
                "teacher_name,/*讲师名称*/\n" +
                "teacher_info,/*讲师介绍*/\n" +
                "preview_first_field,/*课程概述第一个预览字段*/\n" +
                "preview_second_field,/*课程概述第二个预览字段*/\n" +
                "discounts,/*售卖价格*/\n" +
                "price,/*商品原价*/\n" +
                "price_tag,/*活动文案*/\n" +
                "share_image_title,/*分享小图的标题*/\n" +
                "share_title,/*分享标题*/\n" +
                "share_description,/*分享简介*/\n" +
                "course_description,/*课程概述*/\n" +
                "course_img_url,/*课程分享小图的url*/\n" +
                "STATUS,/*课程状态*/\n" +
                "create_time,/*课程创建时间*/\n" +
                "update_time/*课程更新时间*/\n" +
                ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ;
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    course.getCourse_name(), course.getBrief(), course.getTeacher_name(),
                    course.getTeacher_info(), course.getPreview_first_field(),
                    course.getPreview_second_field(), course.getDiscounts(),
                    course.getPrice(), course.getPrice_tag(), course.getShare_image_title(),
                    course.getShare_title(), course.getShare_description(),
                    course.getCourse_description(), course.getCourse_img_url(),
                    course.getStatus(), course.getCreate_time(), course.getUpdate_time());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
