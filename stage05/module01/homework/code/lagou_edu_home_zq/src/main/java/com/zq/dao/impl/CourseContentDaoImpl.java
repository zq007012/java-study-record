package com.zq.dao.impl;

import com.zq.base.BaseDao;
import com.zq.dao.CourseContentDao;
import com.zq.pojo.Course;
import com.zq.pojo.Course_Lesson;
import com.zq.pojo.Course_Section;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 课程内容相关的dao实现类
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/6 21:38
 */
@NoArgsConstructor
public class CourseContentDaoImpl extends BaseDao implements CourseContentDao {

    public CourseContentDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 更新<code>course_lesson</code>表中课时信息
     * <p>需要更新的字段是: <code>course_id, section_id, theme, duration, is_free, order_num, update_time</code></p>
     * <p>需要满足的条件: <code>id=?</code></p>
     *
     * @param lesson
     * @return
     */
    @Override
    public int updateCourseLesson(Course_Lesson lesson) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update course_lesson set course_id=?, section_id=?, theme=?, duration=?, is_free=?, order_num=?, update_time=? where id=?";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    lesson.getCourse_id(),lesson.getSection_id(),lesson.getTheme(),lesson.getDuration(),lesson.getIs_free(),lesson.getOrderNum(),lesson.getUpdate_time(),lesson.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return result;

    }

    /**
     * 根据章节id查询该章节下的所有课时
     *
     * @param sectionId 章节id
     * @return
     */
    @Override
    public List<Course_Lesson> findCourseLessonsBySectionId(int sectionId) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "SELECT id, course_id, section_id, theme, duration, is_free, order_num, status FROM course_lesson WHERE section_id = ?;";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = getQueryRunner();
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Course_Lesson> beanListHandler = new BeanListHandler<>(Course_Lesson.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Course_Lesson> query = null;
        try {
            query = queryRunner.query(sql, beanListHandler,
                    sectionId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return query;
    }

    /**
     * 根据课程id获取该课程下的所有章节, 同时每个章节都携带有课时信息
     *
     * @param courseId 课程id
     * @return
     */
    @Override
    public List<Course_Section> findCourseSectionsWithLessonsByCourseId(int courseId) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "SELECT id, course_id, section_name, description, order_num, status FROM course_section WHERE course_id = ?;";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = getQueryRunner();
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Course_Section> beanListHandler = new BeanListHandler<>(Course_Section.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Course_Section> query = null;
        try {
            query = queryRunner.query(sql, beanListHandler,
                    courseId);

            // 遍历章节集合, 给每个章节配上课时信息
            for (Course_Section courseSection :
                    query) {
                List<Course_Lesson> lessons = findCourseLessonsBySectionId(courseSection.getId());
                courseSection.setCourseLessonList(lessons);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return query;
    }

    /**
     * 根据课程id获取该课程的<code>id, course_name</code>
     *
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseById(int courseId) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select id,course_name from course where id = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = getQueryRunner();
        // 3. 创建一个可以对下划线和驼峰命名进行转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanHandler<Course> beanHandler = new BeanHandler<>(Course.class, brp);
        //4. 执行查找并以JavaBean的形式获得结果
        Course query = null;
        try {
            query = queryRunner.query(sql, beanHandler,
                    courseId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return query;


    }

    /**
     * 向course_section中保存一条章节记录, 保存的字段有
     * <p><code>section_name,description,order_num,STATUS,create_time,update_time</code></p>
     * <p>status --- 0:隐藏；1：待更新；2：已发布</p>
     *
     * @param courseSection
     * @return
     */
    @Override
    public int saveCourseSection(Course_Section courseSection) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "insert into course_section(course_id, section_name,description,order_num,status,create_time,update_time) values(?,?,?,?,?,?,?)";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    courseSection.getCourse_id(), courseSection.getSection_name(), courseSection.getDescription(), courseSection.getOrder_num(),
                    courseSection.getStatus(), courseSection.getCreate_time(), courseSection.getUpdate_time());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return result;

    }

    /**
     * 更新course_section表中的一条记录, 更新的字段有
     * <p><code>section_name,description,order_num,update_time</code></p>
     * <p>这条记录要满足<code>id = ?</code></p>
     * <p>status --- 0:隐藏；1：待更新；2：已发布</p>
     *
     * @param courseSection
     * @return
     */
    @Override
    public int updateCourseSection(Course_Section courseSection) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "UPDATE course_section SET section_name=?, description=?, order_num=?, update_time=? WHERE id=?;";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    courseSection.getSection_name(), courseSection.getDescription(), courseSection.getOrder_num(), courseSection.getUpdate_time(), courseSection.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return result;

    }

    /**
     * 修改课程状态, 修改的字段有
     * <p><code>status, update_time</code></p>
     * <p>条件是: <code>id = ? </code></p>
     * <p>status  --- 0:隐藏；1：待更新；2：已发布</p>
     *
     * @param section
     * @return
     */
    @Override
    public int updateCourseSectionStatus(Course_Section section) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update course_section set status=?,update_time=? where id=?";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    section.getStatus(), section.getUpdate_time(), section.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return result;

    }

    /**
     * 保存新课时
     * <p>保存的字段有<code>course_id, section_id, theme, duration, is_free, order_num,  create_time, update_time, status</code></p>
     * <p>status : 课时状态,0-隐藏，1-未发布，2-已发布</p>
     * <p>is_free : 是否免费, 0-否, 1-是</p>
     * @param lesson
     * @return
     */
    @Override
    public int saveCourseLesson(Course_Lesson lesson) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "insert into course_lesson(course_id, section_id, theme, duration, is_free, order_num,  create_time, update_time, status) values(?,?,?,?,?,?,?,?,?)";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    lesson.getCourse_id(), lesson.getSection_id(), lesson.getTheme(), lesson.getDuration(), lesson.getIs_free(), lesson.getOrderNum(), lesson.getCreate_time(), lesson.getUpdate_time(), lesson.getStatus());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return result;

    }
}
