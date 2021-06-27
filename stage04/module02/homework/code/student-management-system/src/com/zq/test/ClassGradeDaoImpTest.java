package com.zq.test;

import com.zq.model.dao.ClassGradeDao;
import com.zq.model.factory.ClassGradeDaoFactory;
import com.zq.model.javabean.ClassGrade;
import com.zq.utils.EmptyUtils;
import com.zq.utils.UUIDUtils;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName: ClassGradeDaoImp
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/27 19:49
 * @Version: V1.0
 */
public class ClassGradeDaoImpTest {
    @Test
    public void testCreate() throws Exception {
        ClassGrade classGrade = new ClassGrade();
        classGrade.setClassGradeId(UUIDUtils.generateUUID());
        classGrade.setClassGradeName("一年级1班");
        classGrade.setClassTeacher("Mercy");
        classGrade.setWatchwords("Hero's never die.");
        ClassGradeDao dao = ClassGradeDaoFactory.newClassGradeDao();

        boolean result = dao.create(classGrade);
        System.out.println(result ? "创建成功班级成功" : "创建班级失败");

    }

    @Test
    public void testCreate3Grae9Class() throws Exception {
        ClassGradeDao classGradeDao = ClassGradeDaoFactory.newClassGradeDao();
        for (int i = 0; i < 3; i++) {
            String grade = "年级";
            switch (i) {
                case 0:
                    grade = "一" + grade;
                    break;
                case 1:
                    grade = "二" + grade;
                    break;
                default:
                    grade = "三" + grade;
                    break;
            }
            for (int j = 1; j < 10; j++) {
                String classGradeName = grade + j + "班";
                String teacher = "红叶" + (i + 1) + j + "号";
                String watchwords = "略" + (i + 1) + j;
                ClassGrade classGrade = new ClassGrade();
                classGrade.setClassGradeId(UUIDUtils.generateUUID());
                classGrade.setClassGradeName(classGradeName);
                classGrade.setClassTeacher(teacher);
                classGrade.setClassSize(0);
                classGrade.setWatchwords(watchwords);
                classGradeDao.create(classGrade);
                System.out.println(classGradeName + "创建成功");
            }
        }
    }

    @Test
    public void testRetrieve() throws Exception {
        ClassGradeDao daoImp = ClassGradeDaoFactory.newClassGradeDao();
        List<ClassGrade> classGradeList = daoImp.retrieve(ClassGrade.CLASS_GRADE_NAME, 1 + "");
        System.out.println(classGradeList.get(0));
    }

    @Test
    public void testUpdate() throws Exception {
        ClassGradeDao dao = ClassGradeDaoFactory.newClassGradeDao();

        List<ClassGrade> classGradeList = dao.retrieve(ClassGrade.CLASS_TEACHER, "Mercy");
        if (EmptyUtils.isEmpty(classGradeList)) {
            System.out.println("没有这个班主任");
        } else {
            ClassGrade classGrade = classGradeList.get(0);
            boolean update = dao.update(classGrade.getClassGradeId(), ClassGrade.CLASS_TEACHER, "黑百合");
            System.out.println(update ? "编辑成功" : "编辑失败");
        }
    }

    @Test
    public void testDelete() throws Exception {
        ClassGradeDao dao = ClassGradeDaoFactory.newClassGradeDao();

        int delete = dao.delete(ClassGrade.CLASS_GRADE_NAME, 1 + "");
        System.out.println("删除了" + delete + "条记录");

    }
}
