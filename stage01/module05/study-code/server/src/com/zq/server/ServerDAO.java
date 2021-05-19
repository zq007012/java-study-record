package com.zq.server;

import com.zq.client.bean.member.Student;
import com.zq.client.bean.Exam;

import java.io.*;
import java.util.*;

/**
 * @ClassName: ServerDAO
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/19 14:35
 * @Version: V1.0
 */
public class ServerDAO {
    /**
     * 学员数据库, 学员的账号为key, 学员的信息为value
     */
    private HashMap<String, Student> studentsData;
    private HashMap<Integer, Exam> examsData;

    public ServerDAO() throws IOException, ClassNotFoundException {
        initStudentData();
        initExamData();

    }

    /**
     * 初始化考题数据, 就是将保存到本地文件中的考题数据读取到examsData中
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void initExamData() throws IOException, ClassNotFoundException {
        File examsFile = new File("data/ExamsData.data");
        if (examsFile.exists()) {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(examsFile));
            examsData = (HashMap<Integer, Exam>) ois.readObject();
            ois.close();
        } else {
            examsData = new HashMap<>();
        }

        System.out.println("初始化考题数据成功");
    }

    /**
     * 初始化学员数据, 就是将保存到本地文件中的学员数据读取到studentsData中
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void initStudentData() throws IOException, ClassNotFoundException {
        File studentsFile = new File("data/StudentsData.data");
        if (studentsFile.exists()) {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(studentsFile));
            studentsData = (HashMap<String, Student>) ois.readObject();
            ois.close();
        } else {
            studentsData = new HashMap<>();
        }
        System.out.println("初始化学员数据成功");
    }

    /**
     * 添加一个考题
     */
    public Object[] createExam(Exam exam) {
        System.out.println("------------------------------------------------------");
        System.out.println("开启添加考题的功能的功能");
        if (examsData.containsKey(exam.getUid())) {
            System.out.println("考题添加失败, 系统中已有该问题的考题");
            return new Object[]{false, exam};
        } else {
            System.out.println("考题添加成功");
            examsData.put(exam.getUid(), exam);
            return new Object[]{true, exam};
        }
    }

    /**
     * 提供所有考题
     */
    public Object[] allExam() {
        Collection<Exam> exams = examsData.values();
        return exams.toArray(new Exam[exams.size()]);
    }

    /**
     * 查询一个考题
     */
    public Object[] retrieveExam(String question) {
        Exam exam = examsData.get(question.hashCode());
        return null == exam ? new Object[]{false, null} : new Object[]{true, exam};
    }

    /**
     * 修改一个考题的正确答案
     */
    public Object[] updateExamCorrectOption(String question, String correctAnswer,
                                            char correctOption) {
        Exam exam = examsData.get(question.hashCode());
        if (null == exam) {
            return new Object[]{false, null};
        } else {
            exam.setCorrectOption(correctOption);

            switch (correctOption) {
                case 'A':
                    exam.setaAnswer(correctAnswer);
                    break;
                case 'B':
                    exam.setbAnswer(correctAnswer);
                    break;
                case 'C':
                    exam.setcAnswer(correctAnswer);
                    break;
                case 'D':
                    exam.setdAnswer(correctAnswer);
                    break;

                default:
                    break;
            }

            return new Object[]{true, exam};
        }
    }

    /**
     * 删除一个考题
     */
    public Object[] deleteExam(String question) {
        Exam exam = examsData.remove(question.hashCode());
        return null == exam ? new Object[]{false, null} : new Object[]{true, exam};
    }

    /**
     * 检测管理员的账号和密码
     */
    public Object[] managerLogInCheck(String managerAccount, String managerPassword) {
        System.out.println("------------------------------------------------------");
        System.out.println("开启检测管理员账号和密码的功能");
        boolean checkResult =
                "admin".equals(managerAccount) && "123456".equals(managerPassword);
        if (checkResult) {
            System.out.println("管理员账号和密码正确, 登录成功");
        } else {
            System.out.println("管理员账号或密码错误, 登录失败");
        }
        return checkResult ? new Object[]{true, null} : new Object[]{false, null};
    }


    /**
     * 查询账号为studentAccount的学生的信息
     *
     * @param studentAcount
     * @return
     */
    public Object[] retrieveStudent(String studentAcount) {
        System.out.println("------------------------------------------------------");
        System.out.println("开启查询学员信息的功能");
        Student student = studentsData.get(studentAcount);
        if (null == student) {
            System.out.println("系统中没有账号为:" + studentAcount + "的学员");
            return new Object[]{false, null};
        } else {
            System.out.println("该学员的信息是: " + student.toString());
            return new Object[]{true, student};
        }
    }

    /**
     * 修改账号为studentAccount的学生的密码为newPassword
     *
     * @param studentAccount
     * @param newPassword
     * @return
     */
    public Object[] modifyStudentPassword(String studentAccount, String newPassword) {
        System.out.println("------------------------------------------------------");
        System.out.println("开启修改学员密码的的功能");
        Student student = studentsData.get(studentAccount);
        if (null == student) {
            System.out.println("修改学员: " + studentAccount +
                    "的密码失败,系统中没有这个账号");
            return new Object[]{false, null};
        } else {
            student.setPassword(newPassword);
            student = studentsData.get(studentAccount);
            System.out.println("修改了一个学员的密码, 现在这个学生的信息是: ");
            System.out.println(student.toString());
            return new Object[]{true, student};
        }
    }

    /**
     * 根据学员账号删除一个学员
     *
     * @param studentAccount
     * @return
     */
    public Object[] deleteStudent(String studentAccount) {
        System.out.println("------------------------------------------------------");
        System.out.println("开启移除学员的功能");
        Student removedStudent = studentsData.remove(studentAccount);
        if (null == removedStudent) {
            System.out.println("删除账号为:" + studentAccount +
                    "的学员失败,系统中没有这个账号");
            return new Object[]{false, null};
        } else {
            System.out.println("成功删除账号为:" + studentAccount + "的学员");
            return new Object[]{true, removedStudent};
        }
    }

    /**
     * 向学员数据库里添加一个学生,
     *
     * @param studentAccount
     * @return
     */
    public Object[] createStudent(String studentAccount) {
        System.out.println("------------------------------------------------------");
        System.out.println("开启添加学员的功能");
        Set<String> studentAccoutSet = studentsData.keySet();
        if (studentAccoutSet.contains(studentAccount)) {
            System.out.println("添加学员失败,系统中已有该账号");
            return new Object[]{false, null};
        } else {
            Student student = new Student(null, studentAccount, "000000",
                    new ArrayList<Integer>());
            studentsData.put(studentAccount, student);
            System.out.println("添加了一个学员, 学员数据库里的学员信息: ");
            System.out.println(studentsData.values().toString());
            return new Object[]{true, student};
        }
    }

    public void storeData() throws IOException {
        storeStudentsData();
        storeExamsData();
    }

    private void storeExamsData() throws IOException {
        File file = new File("data/ExamsData.data");
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(examsData);
        oos.flush();
        oos.close();
        System.out.println("成功保存考题数据");
    }

    private void storeStudentsData() throws IOException {
        File file = new File("data/StudentsData.data");
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(studentsData);
        oos.flush();
        oos.close();
        System.out.println("成功保存学员数据");
    }


    public Object[] allStudents() {
        System.out.println("------------------------------------------------------");
        System.out.println("开启显示所有学员信息的功能");
        Collection<Student> collection = studentsData.values();
        Student[] students = collection.toArray(new Student[collection.size()]);
        System.out.println(Arrays.toString(students));

        return students;
    }

    /**
     * 修改学生数据库中指定学生的成绩
     *
     * @param student
     */
    public void updateStudentScore(Student student) {
        studentsData.get(student.getAccount()).setScores(student.getScores());
    }

    public Object[] studentCheck(String studentAccount, String studentPassword) {
        System.out.println("开始检测学员账号和密码");
        Student student = studentsData.get(studentAccount);
        if (null == student || !student.getPassword().equals(studentPassword)) {
            System.out.println("学员登录失败, 账号或密码错误");
            return new Object[]{false, null};
        } else {
            System.out.println("学员登录成功");
            return new Object[]{true, student};
        }
    }
}
