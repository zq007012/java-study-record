package com.zq.model.service;

import com.zq.model.dao.StudentDao;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.javabean.Student;
import com.zq.utils.EmptyUtils;

import java.util.IdentityHashMap;
import java.util.List;

/**
 * @ClassName: StudentMangerService
 * @Description: 为学生信息管理员提供服务
 * @Author: zq007
 * @Date: 2021/5/28 21:51
 * @Version: V1.0
 */
public class StudentMangerService{
    private StudentDao studentDao ;

    public StudentMangerService() {
    }

    public StudentMangerService(StudentDao studentDao) {
        setStudentDao(studentDao);
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getStudentListByClassGrade(ClassGrade classGrade) {
        return studentDao.getStudentListByClassGrade(classGrade);
    }

    public List<Student> getAllStudents() {
        return studentDao.retrieveAll();
    }

    public Student getStudentByName(String name) {
        List<Student> retrieve = studentDao.retrieve(Student.NAME, name);
        return EmptyUtils.isEmpty(retrieve) ? null : retrieve.get(0);
    }

    public Student getStudentByNumber(String number) {
        List<Student> retrieve = studentDao.retrieve(Student.NUMBER, number);
        return EmptyUtils.isEmpty(retrieve) ? null : retrieve.get(0);
    }

    public boolean addStudent(Student student) {
        return studentDao.create(student);
    }

    public List<Student> searchFuzzily(String keyword) {
        List<Student> listByNumber = searchFuzzilyByNumber(keyword);
        List<Student> listByName = searchFuzzilyByName(keyword);
        if (EmptyUtils.isEmpty(listByNumber)){
            return listByName;
        }else{
            listByNumber.removeAll(listByName);
            listByNumber.addAll(listByName);
            return listByNumber;
        }
     }

    public List<Student> searchFuzzilyByName(String keyword) {
        return studentDao.retrieveFuzzily(Student.NAME,keyword);
    }

    public List<Student> searchFuzzilyByNumber(String keyword) {
        return studentDao.retrieveFuzzily(Student.NUMBER,keyword);
    }

    public boolean removeStudentByStudentId(String studentId) {
        return studentDao.delete(Student.STUDENT_ID,studentId) > 0;
    }

    public Student getStudentByStudentId(String studentId) {
        List<Student> retrieve = studentDao.retrieve(Student.STUDENT_ID, studentId);
        return EmptyUtils.isEmpty(retrieve) ? null : retrieve.get(0);
    }

    public boolean modifyStudent(String studentId, Student student) {
        return studentDao.updateExcludePrimaryId(studentId,student);
    }
}
