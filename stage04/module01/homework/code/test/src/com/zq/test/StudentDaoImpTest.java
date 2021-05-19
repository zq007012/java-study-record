package com.zq.test;

import com.zq.model.dao.StudentDaoImp;
import com.zq.model.javabean.Student;
import com.zq.utils.UUIDUtils;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName: TestStudentDaoImp
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/10 13:45
 * @Version: V1.0
 */
public class StudentDaoImpTest {
    @Test
    public void testCreate() throws Exception {
        Student student = new Student();
        student.setStudentId(UUIDUtils.generateUUID());
        student.setNumber("20210001");
        student.setName("黑百合");
        student.setGender("女");
        student.setBirthday("1991-06-01");
        student.setEmail("widwomaker@163.con");
        student.setNotes("一枪一个");
        boolean addResult = StudentDaoImp.getInstance().create(student);
        if (addResult) {
            System.out.println("添加学生失败");
        } else {
            System.out.println("添加学生成功");

        }
    }

    @Test
    public void testCreate100Student() throws Exception {
        for (int i = 10; i < 110; i++) {
            Student student = new Student();
            student.setStudentId(UUIDUtils.generateUUID());
            String num = "";
            if(i < 100) {
                num = "202100" + i;
            } else {
                num = "20210" + i;
            }
            student.setNumber(num);
            student.setName("红叶" + i + "号");
            student.setGender(i%2 == 0 ? "男" : "女");
            student.setBirthday("1991-12-12");
            student.setEmail("mercy" + i + "@163.com") ;
            student.setNotes("notes" + i);

            boolean addResult = StudentDaoImp.getInstance().create(student);
            System.out.println(addResult ? student : "添加学生失败");
        }
    }

    @Test
    public void testRetrieve() throws Exception {
        List<Student> retrieve = StudentDaoImp.getInstance().retrieve(Student.NUMBER, "20210001");
        System.out.println(retrieve);
    }

    @Test
    public void testRetrieveFuzzily() throws Exception {
        List<Student> students = StudentDaoImp.getInstance().
                retrieveFuzzily(Student.NAME, "3");
        System.out.println(students);
    }
    @Test
    public void testRetrieveAll() throws Exception {
        List<Student> students = StudentDaoImp.getInstance().
                retrieveAll();
        System.out.println(students);
    }

    @Test
    public void testUpdate() throws Exception {
        Student student = StudentDaoImp.getInstance().retrieve(Student.NAME, "黑百合").get(0);
        student.setName("艾米莉");
        boolean update = StudentDaoImp.getInstance().update(student);
        System.out.println(update ? "修改成功" : "修改失败");
    }

    @Test
    public void testDelete() throws Exception {
        int delete = StudentDaoImp.getInstance().
                delete(Student.NUMBER, "20210101");
        System.out.println("删除了" + delete +"条记录");
    }
}
