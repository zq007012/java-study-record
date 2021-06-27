package com.zq.model.dao;

import com.zq.model.javabean.ClassGrade;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: ClassGredeDaoImp
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/26 17:39
 * @Version: V1.0
 */
public class ClassGradeDaoImp extends ClassGradeDao {

    public ClassGradeDaoImp() {
    }

    public ClassGradeDaoImp(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void updateClassSize(String fieldName, String fieldValue) {
        List<ClassGrade> classGradeList = retrieve(fieldName, fieldValue);
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update class_grade set class_size=(select count(student_id) from student where student.class_grade_id=?) where class_grade_id=?";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = getQueryRunner();
        // 3. 执行增删改操作
        try {
            for (ClassGrade classGrade : classGradeList) {
                queryRunner.update(sql,
                        classGrade.getClassGradeId(),
                        classGrade.getClassGradeId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 用来向表中插入一条记录
     *
     * @param classGrade 要插入到表中的JavaBean对象
     * @return 返回true, 表明插入成功, 返回false, 表明插入失败
     */
    @Override
    public boolean create(ClassGrade classGrade) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "insert into class_grade values(?,?,?,?,?)";
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql,
                    classGrade.getClassGradeId(),
                    classGrade.getClassGradeName(),
                    classGrade.getClassTeacher(),
                    classGrade.getClassSize(),
                    classGrade.getWatchwords());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;

    }

    /**
     * 用来根据某列字段的值来查找到所有匹配的数据
     *
     * @param fieldName 字段的名字, 可以到{@link ClassGrade}的常量中获取
     * @param value     需要查找的值
     * @return 返回值是个集合, 如果返回值为null或者集合的长度为0, 则说明没有匹配的数据
     */
    @Override
    public List<ClassGrade> retrieve(String fieldName, String value) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from class_grade where " + fieldName + " = ? order by  convert(class_grade_name using GBK), convert(class_teacher using GBK),class_size";
        // 2. 创建QueryRunner对象
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<ClassGrade> beanListHandler = new BeanListHandler<>(ClassGrade.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<ClassGrade> query = null;
        try {
            query = getQueryRunner().query(sql, beanListHandler, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 用来根据某列字段的值来模糊查询到所有匹配的数据, where fileName like concat('%',?,'%')
     *
     * @param fieldName 字段的名字, 可以到{@link ClassGrade}的常量中获取
     * @param keyword   需要模糊查找的关键字
     * @return 返回值是个集合, 如果返回值为null, 或者集合的长度为0, 则说明没有匹配的数据
     */
    @Override
    public List<ClassGrade> retrieveFuzzily(String fieldName, String keyword) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from class_grade where " + fieldName + " like concat('%',?,'%') order by convert(class_grade_name using GBK), convert(class_teacher using GBK),class_size";
        // 2. 创建QueryRunner对象
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<ClassGrade> beanListHandler = new BeanListHandler<>(ClassGrade.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<ClassGrade> query = null;
        try {
            query = getQueryRunner().query(sql, beanListHandler, keyword);
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
    public List<ClassGrade> retrieveAll() {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from class_grade order by convert(class_grade_name using GBK), convert(class_teacher using GBK),class_size";
        // 2. 创建QueryRunner对象
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<ClassGrade> beanListHandler = new BeanListHandler<>(ClassGrade.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<ClassGrade> query = null;
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
        if (ClassGrade.CLASS_GRADE_ID.equals(fieldName)){
            return false;
        }
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update class_grade set " + fieldName + " =? where class_grade_id =?";
        // 2. 创建QueryRunner
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql,
                    value,
                    primaryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;

    }

    /**
     * 修改一条JavaBean对象中的除了主键id属性之外的其它属性, 班级人数也是不可以改的
     *
     * @param classGrade 需要修改的JavaBean对象, 注意代表这个JavaBean对象的'id'是不可以改的
     * @return 返回true表示修改成功, 返回false表示修改失败
     */
    @Override
    public boolean updateExcludePrimaryId(String primaryId, ClassGrade classGrade) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update class_grade set class_grade_name=?,class_teacher=?,watchwords=? where class_grade_id=?";
        // 2. 创建QueryRunner
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql,
                    classGrade.getClassGradeName(),
                    classGrade.getClassTeacher(),
                    classGrade.getWatchwords(),
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
        String sql = "delete from class_grade where " + fieldName + "=?";
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
