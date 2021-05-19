package com.zq.model.service;

import com.zq.model.dao.StudentDao;
import com.zq.model.javabean.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/11 12:51
 * @Version: V1.0
 */
public class ManagerService {
    private StudentDao studentDao;

    public ManagerService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public ManagerService() {
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudent() throws SQLException {
        return studentDao.retrieveAll();
    }

    /**
     * 根据学号找到对应的学生
     * @param number
     * @return
     */
    public Student getStudentByname(String number){
        List<Student> studentList = studentDao.retrieve(Student.NUMBER, number);
        return studentList == null || studentList.size() <1 ? null :
                studentList.get(0);
    }

    public boolean addStudent(Student student) throws SQLException {
        return studentDao.create(student);
    }

    public Student getStudentById(String studentId) {
        List<Student> studentList = studentDao.retrieve(Student.STUDENT_ID, studentId);
        return studentList == null || studentList.size() <1 ? null :
                studentList.get(0);
    }

    public boolean modifyStudent(Student student) {
        return studentDao.update(student);
    }

    public boolean removeStudentById(String studentId){
        return studentDao.delete(Student.STUDENT_ID,studentId) > 0;
    }

    public List<Student> searchFuzzilyByNumber(String keyword) {
        return studentDao.retrieveFuzzily(Student.NUMBER, keyword);
    }

    public List<Student> searchFuzzilyByName(String keyword) {
        return studentDao.retrieveFuzzily(Student.NAME,keyword);
    }
}
