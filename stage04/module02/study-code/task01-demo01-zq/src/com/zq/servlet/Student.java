package com.zq.servlet;

/**
 * @ClassName: Student
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/23 15:46
 * @Version: V1.0
 */
public class Student{
    private String name;
    private String age;
    private String gender;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!getName().equals(student.getName())) return false;
        if (!getAge().equals(student.getAge())) return false;
        return getGender().equals(student.getGender());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAge().hashCode();
        result = 31 * result + getGender().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
