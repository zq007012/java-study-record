package com.zq.model.service;

import com.zq.interfaces.Dao;
import com.zq.model.dao.ClassGradeDao;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.javabean.Student;
import com.zq.utils.EmptyUtils;

import java.util.List;

/**
 * @ClassName: ClassGradeManagerService
 * @Description: 为班级管理员提供服务
 * @Author: zq007
 * @Date: 2021/5/28 21:53
 * @Version: V1.0
 */
public class ClassGradeManagerService {
    private ClassGradeDao classGradeDao;

    public ClassGradeManagerService() {
    }

    public ClassGradeManagerService(ClassGradeDao classGradeDao) {
        this.classGradeDao = classGradeDao;
    }

    public ClassGradeDao getClassGradeDao() {
        return classGradeDao;
    }

    public void setClassGradeDao(ClassGradeDao classGradeDao) {
        this.classGradeDao = classGradeDao;
    }

    public boolean createClassGrade(ClassGrade classGrade){
        return classGradeDao.create(classGrade);
    }

    public List<ClassGrade> getAll(){
        return classGradeDao.retrieveAll();
    }

    /**
     * 模糊搜索关键字, 根据班级名或者班主任姓名进行模糊搜索
     * @param keyword
     * @return
     */
    public List<ClassGrade> getByNameOrTeacher(String keyword){
        List<ClassGrade> listByName = classGradeDao.retrieveFuzzily(ClassGrade.CLASS_GRADE_NAME, keyword);
        List<ClassGrade> listByTeacher = classGradeDao.retrieveFuzzily(ClassGrade.CLASS_TEACHER, keyword);
        listByName.removeAll(listByTeacher);
        listByName.addAll(listByTeacher);
        return listByName;
    }

    public ClassGrade getClassGradeByName(String classGradeName) {
        return getClassGrade(ClassGrade.CLASS_GRADE_NAME, classGradeName);
    }

    public ClassGrade getClassGrade(String fieldName, String fieldValue) {
        List<ClassGrade> retrieve = classGradeDao.retrieve(fieldName, fieldValue);
        return EmptyUtils.isEmpty(retrieve) ? null : retrieve.get(0);
    }

    public ClassGrade getClassGradeByTeacher(String teacher) {
        return getClassGrade(ClassGrade.CLASS_TEACHER,teacher);
    }


    public ClassGrade getClassGradeByClassGradeId(String classGradeId) {
        return getClassGrade(ClassGrade.CLASS_GRADE_ID,classGradeId);
    }

    public boolean deleteClassGradeByClassGradeId(String id) {
        return classGradeDao.delete(ClassGrade.CLASS_GRADE_ID,id) > 0;
    }

    public List<ClassGrade> searchFuzzily(String keyword) {
        List<ClassGrade> listByName = searchFuzzilyByName(keyword);
        List<ClassGrade> listByTeacher = searchFuzzilyByTeacher(keyword);
        if (EmptyUtils.isEmpty(listByName)){
            return listByTeacher;
        }else{
            listByName.removeAll(listByTeacher);
            listByName.addAll(listByTeacher);
            return listByName;
        }
    }

    public List<ClassGrade> searchFuzzilyByTeacher(String keyword) {
        return classGradeDao.retrieveFuzzily(ClassGrade.CLASS_TEACHER,
                keyword);
    }

    public List<ClassGrade> searchFuzzilyByName(String keyword) {
        return classGradeDao.retrieveFuzzily(ClassGrade.CLASS_GRADE_NAME,
                keyword);
    }

    public boolean modifyClassGrade(ClassGrade oldClassGrade, ClassGrade newClassGrade) {
        return classGradeDao.updateExcludePrimaryId(oldClassGrade.getClassGradeId(), newClassGrade);
    }

    public ClassGrade getClassGradeByStudent(Student student) {
        return classGradeDao.getClassGradeByStudent(student);
    }

    public void updateClassSize(String classGradeId) {
        classGradeDao.updateClassSize(ClassGrade.CLASS_GRADE_ID,classGradeId);
    }
}
