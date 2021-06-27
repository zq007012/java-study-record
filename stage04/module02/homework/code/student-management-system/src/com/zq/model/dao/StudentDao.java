package com.zq.model.dao;

import com.zq.interfaces.BaseDao;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.javabean.Student;

import javax.sql.DataSource;
import java.util.List;

/**
 * @InterfaceName: StudentDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/26 20:40
 * @Version: V1.0
 */
public abstract class StudentDao extends BaseDao<Student> {
    public StudentDao() {
    }

    public StudentDao(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * ∞对1的关系中, 在 '∞的一方的DAO里' 通过 '1的对象' 获取 '∞的集合',
     * 底层是通过 '1的主键ID' 在 '作为∞的表中' 获取 '∞的集合'
     * 作为∞的一方的类型{@link Student} ,
     * 作为1的一方的类型{@link ClassGrade},
     * 1的对象的名称 "classGrade" ,
     * @see ClassGrade#getClassGradeId()  1的对象获取1的主键ID的方法
     *
     * 本方法可以通过ClassGrade对象(1)来获取匹配的Student对象(∞)的集合
     */
    public List<Student> getStudentListByClassGrade(ClassGrade classGrade){
        return retrieveByClassGradeId(classGrade.getClassGradeId() );
    }

    /**
     * 通过外键CLASS_GRADE_ID(对应ClassGrade的主键)获取匹配的Student对象的集合
     */
    public List<Student> retrieveByClassGradeId(String classGradeId){
        return retrieve(Student.CLASS_GRADE_ID, classGradeId);
    }
}
