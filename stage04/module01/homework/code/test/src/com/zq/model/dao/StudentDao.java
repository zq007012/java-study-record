package com.zq.model.dao;

import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.zq.model.javabean.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @InterfaceName: StudentDAO
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/10 12:54
 * @Version: V1.0
 */
public interface StudentDao {

    /**
     * 用来向表中插入一条数据, 在插入之前应该先判断表中是否有相同的数据
     * @param student
     * @return 插入成功的话, 就将student对象返回, 如果返回值为null, 说明插入该数据失败
     */
    boolean create(Student student);

    /**
     * 用来根据某列字段的值来查找到所有匹配的数据
     * @param fieldName 字段的名字, 可以到{@link Student}的常量中获取
     * @param value
     * @return
     * @throws SQLException
     */
    List<Student> retrieve(String fieldName, String value);

    /**
     * 用来根据某列字段的值来模糊查询到所有匹配的数据
     * @param filedName
     * @param value
     * @return
     */
    List<Student> retrieveFuzzily(String filedName, String value);

    /**
     * 获取所有的学生信息
     * @return 返回所有的学生信息做成的集合
     * @throws SQLException
     */
    List<Student> retrieveAll();

    /**
     * 修改一条学生信息中的一个字段的值
     * @param student  需要修改的那一条信息
     * @return 返回true表示修改成功, 返回false表示修改失败
     */
    boolean update(Student student);

    /**
     * 删除学生信息
     * @param fieldName
     * @param value
     * @return 用来表示删除了几条信息, 返回值小于1, 则说明删除失败
     */
    int delete(String fieldName, String value);
}
