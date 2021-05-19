package com.zq.model.dao;

import com.zq.dbconnpool.DruidPool;
import com.zq.model.javabean.Student;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: StudentDAOimp
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/10 13:22
 * @Version: V1.0
 */
public class StudentDaoImp implements StudentDao {
    private static StudentDaoImp instance;
    private DataSource dataSource;

    private StudentDaoImp() throws Exception {
        setDataSource(DruidPool.getInstance().getDataSource());
    }

    public static StudentDaoImp getInstance() throws Exception {
        if (null == instance) {
            synchronized (StudentDaoImp.class) {
                if (null == instance) {
                    instance = new StudentDaoImp();
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 用来向表中插入一条数据, 在插入之前应该先判断表中是否有相同的数据
     *
     * @param student
     * @return 插入成功的话, 就将student对象返回, 如果返回值为null, 说明插入该数据失败
     */
    @Override
    public boolean create(Student student) {

        //如果student表中已经有了这条信息, 那就直接判定新增失败
        if (retrieve(Student.NUMBER, student.getNumber()).size() > 0) {
            return false;
        }

        //1. 编写SQL语句
        // language=MySQL
        String sql = "insert into student values(?,?,?,?,?,?,?)";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    student.getStudentId(),
                    student.getNumber(),
                    student.getName(),
                    student.getGender(),
                    student.getBirthday(),
                    student.getEmail(),
                    student.getNotes());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result > 0;
    }

    @Override
    public List<Student> retrieve(String fieldName, String value) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from student where " + fieldName + " =? order by number";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Student> beanListHandler = new BeanListHandler<>(Student.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Student> query = null;

        try {
            query = queryRunner.query(sql, beanListHandler, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;

    }

    /**
     * 用来根据某列字段的值来模糊查询到所有匹配的数据
     *
     * @param filedName
     * @param value
     * @return
     */
    @Override
    public List<Student> retrieveFuzzily(String filedName, String value) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from student where " + filedName + " like concat('%',?,'%') order by number";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Student> beanListHandler = new BeanListHandler<>(Student.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Student> query = null;
        try {
            query = queryRunner.query(sql, beanListHandler, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;

    }

    /**
     * 获取所有的学生信息
     *
     * @return 返回所有的学生信息做成的集合
     * @throws SQLException
     */
    @Override
    public List<Student> retrieveAll() {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from student order by number ";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Student> beanListHandler = new BeanListHandler<>(Student.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<Student> studentList = null;
        try {
            studentList = queryRunner.query(sql, beanListHandler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    /**
     * @param student   需要修改的那一条信息
     * @return 修改成功就返回修改后的学生信息, 否则返回null
     */
    @Override
    public boolean update(Student student) {
        if (null == retrieve(Student.STUDENT_ID, student.getStudentId()).get(0)) {
            return false;
        }
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update student set number=?, name=?, gender=?, birthday=?, email=?, notes=? where student_id = ?";
        // 2. 创建QueryRunner
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 执行增删改操作
        int result = 0;
        try {
            System.out.println(student.toString());
            result = queryRunner.update(sql,
                    student.getNumber(),
                    student.getName(),
                    student.getGender(),
                    student.getBirthday(),
                    student.getEmail(),
                    student.getNotes(),
                    student.getStudentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result > 0;
    }

    /**
     * 删除一条学生信息
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
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 执行增删改操作
        int result = 0;
        try {
            result = queryRunner.update(sql, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
