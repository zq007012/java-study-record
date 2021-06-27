package com.zq.model.dao;

import com.zq.model.javabean.Student;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: StudentDaoImp
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/26 20:41
 * @Version: V1.0
 */
public class StudentDaoImp extends StudentDao {
    public StudentDaoImp() {
    }

    public StudentDaoImp(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 用来向表中插入一条记录, 在插入之前应该先通过retrieve方法判断表中是否有相同的记录
     *
     * @param student 要插入到表中的JavaBean对象
     * @return 返回true, 表明插入成功, 返回false, 表明插入失败
     */
    @Override
    public boolean create(Student student) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "insert into student values(?,?,?,?,?,?,?,?)";
        // 2. 创建QueryRunner

        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql,
                    student.getStudentId(),
                    student.getNumber(),
                    student.getName(),
                    student.getGender(),
                    student.getBirthday(),
                    student.getEmail(),
                    student.getNotes(),
                    student.getClassGradeId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;

    }

    /**
     * 用来根据某列字段的值来查找到所有匹配的数据
     *
     * @param fieldName 字段的名字, 可以到{@link Student}的常量中获取
     * @param value     需要查找的值
     * @return 返回值是个集合, 如果返回值为null或者集合的长度为0, 则说明没有匹配的数据
     */
    @Override
    public List<Student> retrieve(String fieldName, String value) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from student where " + fieldName + " = ? order by number, convert(NAME using GBK)";
        // 2. 创建QueryRunner对象

        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Student> beanListHandler = new BeanListHandler<>(Student.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Student> query = null;
        try {
            query = getQueryRunner().query(sql, beanListHandler,
                    value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 用来根据某列字段的值来模糊查询到所有匹配的数据, where fileName like concat('%',?,'%')
     *
     * @param fieldName 字段的名字, 可以到{@link Student}的常量中获取
     * @param keyword   需要模糊查找的关键字
     * @return 返回值是个集合, 如果返回值为null, 或者集合的长度为0, 则说明没有匹配的数据
     */
    @Override
    public List<Student> retrieveFuzzily(String fieldName, String keyword) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from student where " + fieldName + " like concat('%',?,'%') order by class_grade_id,number, convert(NAME using GBK)";
        // 2. 创建QueryRunner对象

        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Student> beanListHandler = new BeanListHandler<>(Student.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Student> query = null;
        try {
            query = getQueryRunner().query(sql, beanListHandler,
                    keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 获取表上的所有记录
     *
     * @return 返回表上的记录所组成的集合
     */
    @Override
    public List<Student> retrieveAll() {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from student order by number, convert(NAME using GBK)";
        // 2. 创建QueryRunner对象

        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Student> beanListHandler = new BeanListHandler<>(Student.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Student> query = null;
        try {
            query = getQueryRunner().query(sql, beanListHandler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 修改一条JavaBean对象中的除了javaBeanId属性之外的其它属性中的一个属性,
     *
     * @param primaryId 需要进行修改的记录的主键id
     * @param fieldName 需要修改的字段
     * @param value     需要改成的值
     * @return 返回true表示修改成功, 返回false表示修改失败
     */
    @Override
    public boolean update(String primaryId, String fieldName, String value) {
        if (Student.STUDENT_ID.equals(fieldName)){
            return false;
        }
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update student set " + fieldName + " = ? where student_id=?";
        // 2. 创建QueryRunner

        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql,
                    value, primaryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;

    }

    /**
     * 修改一条记录中的的除了主键id字段之外的其它字段,
     *
     * @param student 需要修改的JavaBean对象, 注意代表这个JavaBean对象的'id'是不可以改的
     * @return 返回true表示修改成功, 返回false表示修改失败
     */
    @Override
    public boolean updateExcludePrimaryId(String primaryId, Student student) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update student set number=?,name=?,gender=?,birthday=?,email=?,notes=?,class_grade_id=? where student_id=?";
        // 2. 创建QueryRunner

        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql,
                    student.getNumber(),
                    student.getName(),
                    student.getGender(),
                    student.getBirthday(),
                    student.getEmail(),
                    student.getNotes(),
                    student.getClassGradeId(),
                    primaryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;

    }

    /**
     * 删除符合条件的记录
     *
     * @param fieldName
     * @param value
     * @return 用来表示删除了几条信息, 返回值小于1, 则说明删除失败
     */
    @Override
    public int delete(String fieldName, String value) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "delete from student where " + fieldName + " = ?";
        // 2. 创建QueryRunner

        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql,
                    value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

}
