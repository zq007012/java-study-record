package com.zq.test;

import com.zq.model.dao.ClassGradeDao;
import com.zq.model.dao.StudentDao;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.factory.StudentDaoFactory;
import com.zq.model.javabean.ClassGrade;
import com.zq.model.javabean.Student;
import com.zq.utils.UUIDUtils;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName: StudentDaoTest
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/6/8 14:16
 * @Version: V1.0
 */
public class StudentDaoTest {
    @Test
    public void testCreate() throws Exception {
        StudentDao studentDao = StudentDaoFactory.newStudentDao();
        ClassGradeDao cgDao = ClassGradeDaoFactory.newClassGradeDao();
        List<ClassGrade> cgList = cgDao.retrieveAll();
        int baseNum = 0;
        for (ClassGrade classGrade : cgList) {
            for (int i = 1; i < 31; i++) {
                Student student = new Student();
                student.setStudentId(UUIDUtils.generateUUID());
                student.setNumber(20000000 + (++baseNum) + "");
                student.setName("黑百合" + baseNum + "号");
                student.setGender(baseNum / 2 == 0 ? "男" : "女");
                student.setBirthday("2000-01-01");
                student.setEmail(baseNum + "@gmail.com");
                student.setNotes("");
                student.setClassGradeId(classGrade.getClassGradeId());

                studentDao.create(student);
            }
            cgDao.updateClassSize(ClassGrade.CLASS_GRADE_ID,classGrade.getClassGradeId());
        }


        System.out.println("创建结束");
    }
}
