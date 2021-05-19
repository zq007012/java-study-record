package com.lagou.zq.homework.code.test4;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 学生信息管理器
 *
 */
public class StudentManager {
    /**
     * 用来装学生信息的集合
     */
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    /**
     * 向管理器中添加学生
     * @param student
     * @return 返回值为false说明添加失败
     */
    public boolean createStudent(Student student){
        if (null == student){
            return false;
        }
        if (hasStudent(student)){
            System.out.println("已经有" + student + "这个学生了,添加失败");
            return false;
        }
        return students.add(student);
    }

    private boolean hasStudent(Student student) {
        return students.contains(student);
    }

    /**
     * 从管理器中删除学生student, 这个方法要和searchStudent(String name)方法配合使用
     * @param student
     * @return 返回值为null,说明系统中没有叫这个名字的学生
     */
    public Student deleteStudent(Student student){
        if (null == student){
            return null;
        }
        if (students.remove(student)){
            return student;
        }else{
            return null;
        }
    }

    /**
     * 删除系统中第一个名字为name的学生
     * @param name
     * @return 返回值为null,说明系统中没有叫这个名字的学生
     */
    public Student deleteStudent(String name){
        if (null == name){
            return null;
        }

        Student student = searchStudent(name);
        if (null == student){
            return null;
        }else {
            deleteStudent(student);
            return student;
        }

    }

    /**
     * 将管理器中第一个名字为oldName的学生的名字改为newName
     * @param oldName
     * @param newName
     * @return 返回值为true说明修改成功, false说明修改失败
     */
    public boolean changeName(String oldName, String newName){
        Student student = searchStudent(oldName);

        if (null == student){
            return false;
        }

        student.setName(newName);
        return true;
    }

    /**
     * 查找管理器中第一个名字为name的学生
     * @param name
     * @return 返回值为null的话说明没有这个名字的学生
     */
    public Student searchStudent(String name){

        if (null == name){
            return null;
        }

        for (Student student :
                students) {
            if (name.equals(student.getName())){
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentManager that = (StudentManager) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }

    @Override
    public String toString() {
        return students.toString();
    }
}
