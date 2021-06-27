package com.zq.model.dao;

import com.zq.interfaces.BaseDao;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.javabean.Student;

import javax.sql.DataSource;
import java.util.List;

/**
 * @InterfaceName: ClassGradeDat
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/26 17:38
 * @Version: V1.0
 */
public abstract class ClassGradeDao extends BaseDao<ClassGrade> {
    public ClassGradeDao() {
    }

    public ClassGradeDao(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 1对∞的关系中, 在 1的一方的DAO里 通过 ∞的对象 获取 1的对象
     * 底层是通过 '∞的对象保存的1的主键ID', 在 '1的表中' 获取对应的 '1的对象'
     * 1的类型是{@link ClassGrade},
     * ∞的类型是{@link Student},
     * ∞的对象名称是"student",
     *
     * @see Student#getClassGradeId()  ∞的对象中获取1的主键ID的方法
     * <p>
     * 本方法可以通过Student的对象(∞)来获取对应的ClassGrade对象(1)
     */
    public ClassGrade getClassGradeByStudent(Student student) {
        return retrieveByClassGradeId(student.getClassGradeId());
    }

    /**
     * 通过主键Id的值获取匹配的ClassGrade对象
     */
    public ClassGrade retrieveByClassGradeId(String classGradeId) {
        List<ClassGrade> retrieve = retrieve(ClassGrade.CLASS_GRADE_ID, classGradeId);
        return retrieve == null ? null : retrieve.get(0);
    }

    /**
     * 更新班级的人数
     * @param fieldName 可以是任意字段
     * @param fieldValue 字段的值
     * @return
     */
    public abstract void updateClassSize(String fieldName, String fieldValue);
}
